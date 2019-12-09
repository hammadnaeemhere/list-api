package de.c24.finacc.klt.rest;

import de.c24.finacc.klt.constant.AgeStatus;
import de.c24.finacc.klt.dto.Application;
import de.c24.finacc.klt.service.AgeService;
import de.c24.finacc.klt.service.ApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * tests a little bit RestService
 *
 * @author Jörn Schricker
 */
@RunWith(MockitoJUnitRunner.class)
public class RestServiceTest {

    @Mock
    private ApplicationService applicationService;

    @Mock
    private AgeService ageService;

    @InjectMocks
    private RestService restService;

    @Test
    public void testValidateAge() {
        Mockito.when(ageService.validateAge(33)).thenReturn(AgeStatus.FUNNY);
        Map<String, AgeStatus> map = restService.validateAge(33);

        assertEquals("expecting funny age service", map.get("status"), AgeStatus.FUNNY);
        Mockito.verify(ageService, Mockito.times(1)).validateAge(33);
    }

    @Test
    public void testDelete() {
        Map<String, String> map = restService.deleteApplication(12L);
        assertEquals("expecting success status", map.get("status"), "success");
        Mockito.verify(applicationService, Mockito.times(1)).deleteApplication(12L);
    }

    @Test
    public void testFindAllApplications() {
        Application app1 = new Application(1L , "FN", "LN", 22);
        Application app2 = new Application(2L , "FN", "LN", 44);

        Mockito.when(applicationService.findAll()).thenReturn(Arrays.asList(app1, app2));
        List<Application> applications = restService.findAllApplications();

        assertEquals("expecting 2 applications", applications.size(), 2);
        Mockito.verify(applicationService, Mockito.times(1)).findAll();
    }
}
