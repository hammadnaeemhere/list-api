package de.c24.finacc.klt.web.controller;

import de.c24.finacc.klt.service.ApplicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * ApplicationController
 *
 * @author Hammad Naeem
 */
@Controller
@RequestMapping("/applications")
public class ApplicationController {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationController.class);

    @Autowired
    ApplicationService applicationService;

    /**
     * the applications index page
     *
     * @return ModelAndView for applications index page.
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("applications", applicationService.findAll());
        return "applications";
    }

}
