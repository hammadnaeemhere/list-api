package de.c24.finacc.klt.service;

import de.c24.finacc.klt.dto.Application;

import java.util.List;

public interface ApplicationService {
    Application save(Application application);

    List<Application> findAll();

    void deleteApplication(Long applicationId);
}
