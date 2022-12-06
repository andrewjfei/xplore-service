package dev.andrewjfei.xploreservice.service;

import dev.andrewjfei.xploreservice.dao.LocationDao;
import dev.andrewjfei.xploreservice.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public LocationDao createLocation(LocationDao locationDao) {
        return locationRepository.save(locationDao);
    }
}
