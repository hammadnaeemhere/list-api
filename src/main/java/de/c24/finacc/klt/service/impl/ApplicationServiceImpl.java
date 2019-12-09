package de.c24.finacc.klt.service.impl;

import de.c24.finacc.klt.domain.entity.ApplicationEntity;
import de.c24.finacc.klt.domain.repository.ApplicationRepository;
import de.c24.finacc.klt.dto.Application;
import de.c24.finacc.klt.mappers.ApplicationMapper;
import de.c24.finacc.klt.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepository;


    @Autowired
    public ApplicationServiceImpl(
            @Autowired ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }


    @Override
    public Application save(Application application) {
        ApplicationEntity app = new ApplicationEntity(application.getFirstName(), application.getLastName(), application.getAge());
        app = applicationRepository.save(app);

        application.setId(app.getId());
        return application;
    }

    @Override
    public List<Application> findAll() {
        return ApplicationMapper.applicationEntityToApplicationMapper(applicationRepository.findAll());
    }

    @Override
    public void deleteApplication(Long applicationId) {
        applicationRepository.delete(applicationId);
    }
}
