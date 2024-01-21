package de.bytewright.dailylog.core.persistance.repos;

import de.bytewright.dailylog.core.persistance.entities.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
}
