package dev.andrewjfei.xploreservice.service;

import dev.andrewjfei.xploreservice.dao.LocationDao;
import dev.andrewjfei.xploreservice.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.andrewjfei.xploreservice.util.RandomUtil.createRandomObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    private final Logger logger = LoggerFactory.getLogger(LocationServiceTest.class);

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    @Test
    public void testCreateLocation_success_returns() {
        // Given
        LocationDao locationDao = createRandomObject(LocationDao.class);

        when(locationRepository.save(any(LocationDao.class))).thenReturn(locationDao);

        // When
        LocationDao savedLocationDao = locationService.createLocation(locationDao);

        // Then
        verify(locationRepository).save(any(LocationDao.class));

        assertEquals(locationDao.getName(), savedLocationDao.getName());
        assertEquals(locationDao.getDescription(), savedLocationDao.getDescription());
        assertEquals(locationDao.getCoordinate(), savedLocationDao.getCoordinate());
        assertEquals(locationDao.getType(), savedLocationDao.getType());
    }
}
