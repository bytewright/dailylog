package de.bytewright.dailylog.core.persistance.repos;

import de.bytewright.dailylog.CoreApplication;
import de.bytewright.dailylog.core.persistance.entities.AppUser;
import de.bytewright.dailylog.core.persistance.entities.HemoDynamicsMeasurement;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CoreApplication.class)
@ActiveProfiles("test")
class HemoDynamicsRepositoryTest {

    @Autowired
    private HemoDynamicsRepository hemoDynamicsRepository;
    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    void findByUserWorks() {
        //GIVEN
        AppUser testUser = AppUser.builder().name("testUser").build();
        testUser = appUserRepository.save(testUser);

        //WHEN
        HemoDynamicsMeasurement testMeasurement = HemoDynamicsMeasurement.builder()
                .appUser(testUser)
                .systole(120)
                .diastole(80)
                .heartRate(80)
                .note("Test measurement")
                .build();
        hemoDynamicsRepository.saveAll(Set.of(testMeasurement));
        //THEN
        Set<HemoDynamicsMeasurement> byUser = hemoDynamicsRepository.findByAppUser(testUser);
        assertThat(byUser).hasSize(1);
        HemoDynamicsMeasurement measurement = byUser.stream().findFirst().orElseThrow();
        assertThat(measurement)
                .isNotSameAs(testMeasurement)
                .has(new Condition<>(m -> m.getSystole() == 120, ""))
                .has(new Condition<>(m -> m.getDiastole() == 80, ""))
                .has(new Condition<>(m -> m.getHeartRate() == 80, ""))
                .has(new Condition<>(m -> m.getNote().equals(testMeasurement.getNote()), ""));
    }

    @Test
    void deleteUserCascades() {
        //GIVEN
        AppUser testUser = AppUser.builder().name("testUser").build();
        testUser = appUserRepository.save(testUser);

        //WHEN
        HemoDynamicsMeasurement testMeasurement = HemoDynamicsMeasurement.builder()
                .appUser(testUser)
                .systole(120)
                .diastole(80)
                .heartRate(80)
                .note("Test measurement")
                .build();
        hemoDynamicsRepository.saveAll(Set.of(testMeasurement));
        assertThat(hemoDynamicsRepository.findByAppUser(testUser)).hasSize(1);
        appUserRepository.delete(testUser);
        //THEN
        assertThat(hemoDynamicsRepository.findByAppUser(testUser)).hasSize(0);
    }
}