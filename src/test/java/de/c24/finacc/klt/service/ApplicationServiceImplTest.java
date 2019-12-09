package de.c24.finacc.klt.service;

import de.c24.finacc.klt.domain.entity.ApplicationEntity;
import de.c24.finacc.klt.domain.repository.ApplicationRepository;
import de.c24.finacc.klt.dto.Application;
import de.c24.finacc.klt.service.impl.ApplicationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationServiceImplTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @Test
    public void testFindAll() {
        ApplicationEntity applicationEntity = new ApplicationEntity("A", "B", 21);
        ApplicationEntity applicationEntity2 = new ApplicationEntity("A", "B", 22);

        Mockito.when(applicationRepository.findAll()).thenReturn(Arrays.asList(applicationEntity, applicationEntity2));

        List<Application> applications = applicationService.findAll();

        assertEquals("Expecting 2 applications", applications.size(), 2);
        Mockito.verify(applicationRepository, Mockito.times(1)).findAll();
    }


    @Test
    public void testSave() {
        Application application = new Application();
        application.setFirstName("FN");
        application.setLastName("LN");
        application.setAge(22);

        ApplicationEntity saved = new ApplicationEntity();
        saved.setId(1L);
        saved.setFirstName("FN");
        saved.setLastName("LN");
        saved.setAge(22);

        Mockito.when(applicationRepository.save(Mockito.any(ApplicationEntity.class))).thenReturn(saved);

        Application app = applicationService.save(application);

        assertEquals("Expecting 1 app id", app.getId(), Long.valueOf(1));
        Mockito.verify(applicationRepository, Mockito.times(1)).save(Mockito.any(ApplicationEntity.class));
    }

    @Test
    public void testDelete() {
        applicationService.deleteApplication(12L);
        Mockito.verify(applicationRepository, Mockito.times(1)).delete(12L);
    }
}
