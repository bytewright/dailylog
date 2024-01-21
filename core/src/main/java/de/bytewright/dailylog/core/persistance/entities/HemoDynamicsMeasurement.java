package de.bytewright.dailylog.core.persistance.entities;

import jakarta.persistence.*;
import lombok.*;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity(name = "hemodynamic")
@Table(name = "hemodynamics")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HemoDynamicsMeasurement {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(HemoDynamicsMeasurement.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer diastole;
    @Column(nullable = false)
    private Integer systole;
    @Column(name = "heart_rate")
    private Integer heartRate;
    @Column(name = "created_at_utc", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdUtc = LocalDateTime.now(ZoneOffset.UTC);
    @Column(name = "measured_at_utc", nullable = false)
    @Builder.Default
    private LocalDateTime measurementUtc = LocalDateTime.now(ZoneOffset.UTC);
    @Column(name = "measured_at_IANA", nullable = false)
    @Builder.Default
    private String measurementTimeZone = ZoneOffset.UTC.toString();
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;
    @Column
    private String note;
}
