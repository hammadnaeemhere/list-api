package de.c24.finacc.klt.controller;

import de.c24.finacc.klt.domain.entity.ApplicationEntity;
import de.c24.finacc.klt.domain.repository.ApplicationRepository;
import de.c24.finacc.klt.dto.Application;
import de.c24.finacc.klt.service.ApplicationService;
import de.c24.finacc.klt.service.impl.ApplicationServiceImpl;
import de.c24.finacc.klt.web.controller.ApplicationController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationControllerTest {

    @Mock
    private ApplicationService applicationService;

    @InjectMocks
    private ApplicationController applicationController;

    @Test
    public void testFindAll() {
        Application app1 = new Application(1L , "FN", "LN", 22);
        Application app2 = new Application(2L , "FN", "LN", 44);

        Model model = new ExtendedModelMap();

        Mockito.when(applicationService.findAll()).thenReturn(Arrays.asList(app1, app2));
        String view = applicationController.index(model);

        assertEquals("expecting  applications", view, "applications");
        assertTrue("contains applications", model.containsAttribute("applications"));
        Mockito.verify(applicationService, Mockito.times(1)).findAll();
    }
}
