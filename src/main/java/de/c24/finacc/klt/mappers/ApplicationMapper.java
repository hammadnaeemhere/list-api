package de.c24.finacc.klt.mappers;

import de.c24.finacc.klt.domain.entity.ApplicationEntity;
import de.c24.finacc.klt.dto.Application;

import java.util.ArrayList;
import java.util.List;

public class ApplicationMapper {

    public static List<Application> applicationEntityToApplicationMapper(Iterable<ApplicationEntity> applications) {
        List<Application> applicationList = new ArrayList<>();
        for (ApplicationEntity app : applications) {
            applicationList.add(applicationEntityToApplicationMapper(app));
        }

        return applicationList;
    }


    public static Application applicationEntityToApplicationMapper(ApplicationEntity application) {
        return new Application(application.getId(), application.getFirstName(), application.getLastName(), application.getAge());
    }

}
