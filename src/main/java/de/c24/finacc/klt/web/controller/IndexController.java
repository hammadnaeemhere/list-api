package de.c24.finacc.klt.web.controller;

import de.c24.finacc.klt.constant.AgeStatus;
import de.c24.finacc.klt.dto.Application;
import de.c24.finacc.klt.service.AgeService;
import de.c24.finacc.klt.service.ApplicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

/**
 * IndexController
 *
 * @author Jörn Schricker
 */
@Controller
public class IndexController {
    private static final Logger LOGGER = LogManager.getLogger(IndexController.class);

    @Value("${form.title}")
    private String formTitle;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    AgeService ageService;

    /**
     * the index page
     *
     * @return ModelAndView for index page.
     */
    @GetMapping({"/", "/index"})
    public String index(Map<String, Object> model) {
        model.put("formName", formTitle);
        model.put("application", new Application());
        return "index";
    }


    /**
     * the index page
     *
     * @return ModelAndView for index page.
     */
    @PostMapping({"/", "/index"})
    public String submitForm(@Valid @ModelAttribute("application") Application application, BindingResult bindingResult, ModelMap model) {
        LOGGER.debug("submit first name {} last name {}", application.getFirstName(), application.getLastName());
        model.put("formName", formTitle);
        if (!bindingResult.hasErrors()) {
            AgeStatus ageStatus = ageService.validateAge(application.getAge());
            if (AgeStatus.FUNNY.equals(ageStatus) || AgeStatus.OK.equals(ageStatus)) {
                applicationService.save(application);
                return "success";
            }

            model.addAttribute("ageError", true);
            model.addAttribute("ageStatus", ageStatus.getStatus());
        }
        return "index";

    }
}
