package de.c24.finacc.klt.service;

import de.c24.finacc.klt.constant.AgeStatus;
import de.c24.finacc.klt.service.impl.AgeServiceImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AgeServiceImplTest {

    @Test
    public void testValidateAge() {
        AgeServiceImpl ageService = new AgeServiceImpl(18, 67);

        //YOUNG
        AgeStatus ageStatus = ageService.validateAge(12);
        assertEquals("Expecting YOUNG Age Status when age is less than youngLimit", AgeStatus.YOUNG, ageStatus);
        ageStatus = ageService.validateAge(17);
        assertEquals("Expecting YOUNG Age Status when age is less than youngLimit", AgeStatus.YOUNG, ageStatus);

        //OLD
        ageStatus = ageService.validateAge(72);
        assertEquals("Expecting OLD Age Status when age is greater than youngLimit", AgeStatus.OLD, ageStatus);

        //OK
        ageStatus = ageService.validateAge(30);
        assertEquals("Expecting OK Age Status when age is between the limits", AgeStatus.OK, ageStatus);
        ageStatus = ageService.validateAge(60);
        assertEquals("Expecting OK Age Status when age is between the limits", AgeStatus.OK, ageStatus);

        //FUNNY
        ageStatus = ageService.validateAge(19);
        assertEquals("Expecting FUNNY Age Status when age is prime number", AgeStatus.FUNNY, ageStatus);

        ageStatus = ageService.validateAge(23);
        assertEquals("Expecting FUNNY Age Status when age is prime number", AgeStatus.FUNNY, ageStatus);

        ageStatus = ageService.validateAge(29);
        assertEquals("Expecting FUNNY Age Status when age is prime number", AgeStatus.FUNNY, ageStatus);

        ageStatus = ageService.validateAge(59);
        assertEquals("Expecting FUNNY Age Status when age is prime number", AgeStatus.FUNNY, ageStatus);

        ageStatus = ageService.validateAge(61);
        assertEquals("Expecting FUNNY Age Status when age is prime number", AgeStatus.FUNNY, ageStatus);


        //Change limit to verify
        ageService = new AgeServiceImpl(22, 50);
        ageStatus = ageService.validateAge(21);
        assertEquals("Expecting YOUNG Age Status when age is less than youngLimit", AgeStatus.YOUNG, ageStatus);

        ageStatus = ageService.validateAge(51);
        assertEquals("Expecting OLD Age Status when age is less than youngLimit", AgeStatus.OLD, ageStatus);
    }
}
