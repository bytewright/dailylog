package de.bytewright.dailylog.core.api.graphql.controller;

import de.bytewright.dailylog.core.persistance.entities.HemoDynamicsMeasurement;
import de.bytewright.dailylog.core.persistance.repos.HemoDynamicsRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.StreamSupport;

@Controller
@RequiredArgsConstructor
public class MeasurementsController {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MeasurementsController.class);

    private final HemoDynamicsRepository hemoDynamicsRepository;

    @QueryMapping
    public List<HemoDynamicsMeasurement> getAll(@Argument int count, @Argument int offset) {
        Iterable<HemoDynamicsMeasurement> all = hemoDynamicsRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false)
                .skip(offset)
                .limit(count)
                .toList();
    }
}
