package de.c24.finacc.klt.rest;

import de.c24.finacc.klt.constant.AgeStatus;
import de.c24.finacc.klt.dto.Application;
import de.c24.finacc.klt.service.AgeService;
import de.c24.finacc.klt.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Jörn Schricker
 */
@Component
@Path("/")
public class RestService {

    private AgeService ageService;
    private ApplicationService applicationService;


    @Autowired
    public RestService(AgeService ageService, ApplicationService applicationService) {
        this.ageService = ageService;
        this.applicationService = applicationService;
    }

    /**
     * testmap
     *
     * @return map containing fisch and suppe
     */
    @GET
    @Path("/testit")
    @Produces(APPLICATION_JSON_VALUE)
    public Map<String, String> getTestMap() {
        Map<String, String> returnMap = new HashMap<>();

        returnMap.put("fisch", "suppe");

        return returnMap;
    }


    /**
     * Checks if the age is valid or not.
     *
     * @param age Age
     * @return status of age
     */
    @GET
    @Path("/age/{age}")
    @Produces(APPLICATION_JSON_VALUE)
    public Map<String, AgeStatus> validateAge(@PathParam("age") Integer age) {
        Map<String, AgeStatus> map = new HashMap<>();
        map.put("status", ageService.validateAge(age));
        return map;
    }

    /**
     * Returns the list of applications created.
     *
     * @return List of applications
     */
    @GET
    @Path("/applications")
    @Produces(APPLICATION_JSON_VALUE)
    public List<Application> findAllApplications() {
        return applicationService.findAll();
    }


    /**
     * Deletes an application with the given id
     * @param applicationId Application ID
     * @return success if application deletes successfullyF
     */
    @DELETE
    @Path("/applications/{applicationId}")
    @Produces(APPLICATION_JSON_VALUE)
    public Map<String, String> deleteApplication(@PathParam("applicationId") Long applicationId) {
        applicationService.deleteApplication(applicationId);
        Map<String, String> map = new HashMap<>();
        map.put("status", "success");
        return map;
    }
}
