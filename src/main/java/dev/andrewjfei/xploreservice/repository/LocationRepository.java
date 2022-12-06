package dev.andrewjfei.xploreservice.repository;

import dev.andrewjfei.xploreservice.dao.LocationDao;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<LocationDao, UUID> {
}
