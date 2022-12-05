package dev.andrewjfei.xploreservice.controller;

import dev.andrewjfei.xploreservice.dao.UserDao;
import dev.andrewjfei.xploreservice.repository.UserRepository;
import dev.andrewjfei.xploreservice.transaction.request.NewUserRequest;
import dev.andrewjfei.xploreservice.transaction.response.UserResponse;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class UserControllerComponentTest extends BaseComponentTest {

    private final Logger logger = LoggerFactory.getLogger(UserControllerComponentTest.class);

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    private final String USERNAME = "bradkim";

    private final String FIRST_NAME = "Brad";

    private final String LAST_NAME = "Kim";

    private final String EMAIL = "bradkim@xplore.com";

    private final String MOBILE = "0000000000";

    private final String PASSWORD = "password";

    @Test
    public void testRegisterNewUser_success() {
        // Given
        NewUserRequest newUserRequest = new NewUserRequest(
                USERNAME,
                FIRST_NAME,
                LAST_NAME,
                EMAIL,
                MOBILE,
                PASSWORD,
                PASSWORD
        );

        // When
        ResponseEntity<UserResponse> responseEntity = userController.registerNewUser(newUserRequest);

        // Then
        UserResponse userResponse = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        Optional<UserDao> optionalUserDao = userRepository.findById(userResponse.id());

        if (!optionalUserDao.isPresent()) {
            fail("Could not find new user in database.");
        }

        UserDao userDao = optionalUserDao.get();

        assertEquals(USERNAME, userDao.getUsername());
        assertEquals(FIRST_NAME, userDao.getFirstName());
        assertEquals(LAST_NAME, userDao.getLastName());
        assertEquals(EMAIL, userDao.getEmail());
        assertEquals(MOBILE, userDao.getMobile());
        assertEquals(PASSWORD, userDao.getPassword());
        assertFalse(userDao.isVerified());
    }
}
