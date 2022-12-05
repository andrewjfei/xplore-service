package dev.andrewjfei.xploreservice.repository;

import dev.andrewjfei.xploreservice.dao.UserDao;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDao, UUID> {

    @Query(
            value = "SELECT * FROM \"user\"" ,
            nativeQuery = true
    )
    List<UserDao> retrieveAllUsers();
}
