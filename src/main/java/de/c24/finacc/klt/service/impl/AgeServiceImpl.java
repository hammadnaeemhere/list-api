package de.c24.finacc.klt.service.impl;

import de.c24.finacc.klt.constant.AgeStatus;
import de.c24.finacc.klt.service.AgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class AgeServiceImpl implements AgeService {

    private Integer youngLimit;
    private Integer oldLimit;


    @Autowired
    public AgeServiceImpl(
            @Value("${age.validation.young.limit:18}") Integer youngLimit,
            @Value("${age.validation.old.limit:67}") Integer oldLimit) {
        this.youngLimit = youngLimit;
        this.oldLimit = oldLimit;
    }

    @Override
    public AgeStatus validateAge(@NotNull Integer age) {
        AgeStatus status;

        if (age < youngLimit) {
            status = AgeStatus.YOUNG;
        } else if (age > oldLimit) {
            status = AgeStatus.OLD;
        } else if (isPrime(age)) {
            status = AgeStatus.FUNNY;
        } else {
            status = AgeStatus.OK;
        }


        return status;
    }


    /**
     * @param number
     * @return
     */
    private boolean isPrime(int number) {
        int sqrt = (int) Math.sqrt(number) + 1;
        for (int i = 2; i < sqrt; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
