package de.bytewright.dailylog.core.persistance.entities;

import jakarta.persistence.*;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;


@Entity
@Table(name = "app_users")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppUser.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name = "sequence-generator", sequenceName = "app_users_SEQ", allocationSize = 1)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "role")
    private String role;
    @Column(name = "created_at_utc", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdUtc = LocalDateTime.now(ZoneOffset.UTC);
    @Column(name = "updated_at_utc", nullable = false)
    @Builder.Default
    private LocalDateTime updatedUtc = LocalDateTime.now(ZoneOffset.UTC);
    @OneToMany(mappedBy = "appUser", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private Collection<HemoDynamicsMeasurement> hemoDynamics;

}
