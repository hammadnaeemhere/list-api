package de.c24.finacc.klt.service;

import de.c24.finacc.klt.constant.AgeStatus;
import de.c24.finacc.klt.dto.Application;

public interface AgeService {
    AgeStatus validateAge(Integer age);
}
