package dev.andrewjfei.xploreservice.controller;

import dev.andrewjfei.xploreservice.dao.UserDao;
import dev.andrewjfei.xploreservice.exception.AuthenticationException;
import dev.andrewjfei.xploreservice.repository.UserRepository;
import dev.andrewjfei.xploreservice.transaction.request.LoginRequest;
import dev.andrewjfei.xploreservice.transaction.request.NewUserRequest;
import dev.andrewjfei.xploreservice.transaction.response.UserResponse;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static dev.andrewjfei.xploreservice.util.ObjectMapper.toDao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

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

    private UserDao savedUserDao;

    @BeforeEach
    public void setUp() {
        NewUserRequest newUserRequest = new NewUserRequest(
                USERNAME,
                FIRST_NAME,
                LAST_NAME,
                EMAIL,
                MOBILE,
                PASSWORD,
                PASSWORD
        );

        savedUserDao = userRepository.save(toDao(newUserRequest));
    }

    @AfterEach
    public void tearDown() {
        userRepository.delete(savedUserDao);
    }

    @Test
    public void testLoginUser_success_returnsUserResponse() {
        // Given
        LoginRequest loginRequest = new LoginRequest(USERNAME, PASSWORD);

        // When
        ResponseEntity<UserResponse> responseEntity = userController.loginUser(loginRequest);

        // Then
        UserResponse userResponse = responseEntity.getBody();

        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(USERNAME, userResponse.username());
        assertEquals(FIRST_NAME, userResponse.firstName());
        assertEquals(LAST_NAME, userResponse.lastName());
        assertEquals(EMAIL, userResponse.email());
        assertEquals(MOBILE, userResponse.mobile());
    }

    @Test
    public void testLoginUser_incorrectCredentials_returnsErrorResponse() {
        // Given
        String incorrectPassword = "incorrect-password";
        LoginRequest loginRequest = new LoginRequest(USERNAME, incorrectPassword);

        // When
        // Then
        AuthenticationException authenticationException =
                assertThrows(AuthenticationException.class, () ->  userController.loginUser(loginRequest));

        assertEquals(BAD_REQUEST, authenticationException.getHttpStatus());
    }

    @Test
    public void testRegisterNewUser_success_newUserStoredInDatabase() {
        // Given
        userRepository.delete(savedUserDao);

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
        assertEquals(CREATED, responseEntity.getStatusCode());

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
