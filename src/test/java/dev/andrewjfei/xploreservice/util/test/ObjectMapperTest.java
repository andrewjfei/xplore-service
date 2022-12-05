package dev.andrewjfei.xploreservice.util.test;

import dev.andrewjfei.xploreservice.dao.UserDao;
import dev.andrewjfei.xploreservice.transaction.request.NewUserRequest;
import dev.andrewjfei.xploreservice.transaction.response.UserResponse;
import org.junit.jupiter.api.Test;

import static dev.andrewjfei.xploreservice.util.ObjectMapper.toDao;
import static dev.andrewjfei.xploreservice.util.ObjectMapper.toResponse;
import static dev.andrewjfei.xploreservice.util.RandomUtil.createRandomNewUserRequest;
import static dev.andrewjfei.xploreservice.util.RandomUtil.createRandomObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectMapperTest {

    @Test
    public void testToDto_fromNewUserRequest() {
        NewUserRequest newUserRequest = createRandomNewUserRequest();

        UserDao userDao = toDao(newUserRequest);

        assertEquals(newUserRequest.username(), userDao.getUsername());
        assertEquals(newUserRequest.firstName(), userDao.getFirstName());
        assertEquals(newUserRequest.lastName(), userDao.getLastName());
        assertEquals(newUserRequest.email(), userDao.getEmail());
        assertEquals(newUserRequest.mobile(), userDao.getMobile());
        assertEquals(newUserRequest.password(), userDao.getPassword());
    }

    @Test
    public void testToResponse_fromUserDao() {
        UserDao userDao = createRandomObject(UserDao.class);

        UserResponse userResponse = toResponse(userDao);

        assertEquals(userDao.getUsername(), userResponse.username());
        assertEquals(userDao.getFirstName(), userResponse.firstName());
        assertEquals(userDao.getLastName(), userResponse.lastName());
        assertEquals(userDao.getEmail(), userResponse.email());
        assertEquals(userDao.getMobile(), userResponse.mobile());
        assertEquals(userDao.isVerified(), userResponse.isVerified());
    }
}
