package de.bytewright.dailylog.core.persistance.repos;

import de.bytewright.dailylog.core.persistance.entities.AppUser;
import de.bytewright.dailylog.core.persistance.entities.HemoDynamicsMeasurement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface HemoDynamicsRepository extends CrudRepository<HemoDynamicsMeasurement, Long> {
    Set<HemoDynamicsMeasurement> findByAppUser(AppUser appUser);

}
