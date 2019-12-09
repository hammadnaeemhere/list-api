package de.c24.finacc.klt.domain.repository;

import de.c24.finacc.klt.domain.entity.ApplicationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Long> {
}
