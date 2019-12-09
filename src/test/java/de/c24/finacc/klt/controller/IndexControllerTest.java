package de.c24.finacc.klt.controller;

import de.c24.finacc.klt.constant.AgeStatus;
import de.c24.finacc.klt.dto.Application;
import de.c24.finacc.klt.service.AgeService;
import de.c24.finacc.klt.service.ApplicationService;
import de.c24.finacc.klt.web.controller.IndexController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    @Mock
    private ApplicationService applicationService;

    @Mock
    private AgeService ageService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private IndexController indexController;

    @Test
    public void testSubmitFormSuccess() {
        Application app = new Application(1L, "FN", "LN", 22);
        app.setAge(33);
        ModelMap model = new ExtendedModelMap();

        Mockito.when(ageService.validateAge(33)).thenReturn(AgeStatus.FUNNY);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        String view = indexController.submitForm(app, bindingResult, model);

        assertEquals("expecting  success", view, "success");
        Mockito.verify(applicationService, Mockito.times(1)).save(app);
        Mockito.verify(ageService, Mockito.times(1)).validateAge(33);
    }

    @Test
    public void testSubmitFormAgeError() {
        Application app = new Application(1L, "FN", "LN", 22);
        app.setAge(33);
        ModelMap model = new ExtendedModelMap();

        Mockito.when(ageService.validateAge(33)).thenReturn(AgeStatus.OLD);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        String view = indexController.submitForm(app, bindingResult, model);

        assertEquals("expecting  index", view, "index");
        assertTrue("contains ageError", model.containsAttribute("ageError"));
        assertTrue("contains ageStatus", model.containsAttribute("ageStatus"));
        Mockito.verify(applicationService, Mockito.never()).save(app);
        Mockito.verify(ageService, Mockito.times(1)).validateAge(33);
    }
}
