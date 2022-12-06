package dev.andrewjfei.xploreservice.service;

import dev.andrewjfei.xploreservice.dao.UserDao;
import dev.andrewjfei.xploreservice.exception.AuthenticationException;
import dev.andrewjfei.xploreservice.repository.UserRepository;
import dev.andrewjfei.xploreservice.transaction.request.LoginRequest;
import dev.andrewjfei.xploreservice.transaction.request.NewUserRequest;
import dev.andrewjfei.xploreservice.transaction.response.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.andrewjfei.xploreservice.enumeration.Error.INCORRECT_PASSWORD;
import static dev.andrewjfei.xploreservice.enumeration.Error.USERNAME_NOT_FOUND;
import static dev.andrewjfei.xploreservice.util.RandomUtil.createRandomLoginRequest;
import static dev.andrewjfei.xploreservice.util.RandomUtil.createRandomNewUserRequest;
import static dev.andrewjfei.xploreservice.util.RandomUtil.createRandomObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testAuthenticateUser_success_returnsUserResponse() {
        // Given
        LoginRequest loginRequest = createRandomLoginRequest();

        UserDao userDao = createRandomObject(UserDao.class);
        userDao.setPassword(loginRequest.password());

        when(userRepository.retrieveUserByUsername(any(String.class))).thenReturn(userDao);

        // When
        UserResponse userResponse = userService.authenticateUser(loginRequest);

        // Then
        assertEquals(userDao.getId(), userResponse.id());
        assertEquals(userDao.getUsername(), userResponse.username());
        assertEquals(userDao.getFirstName(), userResponse.firstName());
        assertEquals(userDao.getLastName(), userResponse.lastName());
        assertEquals(userDao.getEmail(), userResponse.email());
        assertEquals(userDao.getMobile(), userResponse.mobile());
        assertEquals(userDao.isVerified(), userResponse.isVerified());
    }

    @Test
    public void testAuthenticateUser_usernameNotFound_throwsAuthenticationException() {
        // Given
        LoginRequest loginRequest = createRandomLoginRequest();

        when(userRepository.retrieveUserByUsername(any(String.class))).thenReturn(null);

        // When
        // Then
        AuthenticationException authenticationException =
                assertThrows(AuthenticationException.class, () ->  userService.authenticateUser(loginRequest));

        assertEquals(USERNAME_NOT_FOUND, authenticationException.getError());
        assertEquals(BAD_REQUEST, authenticationException.getHttpStatus());
    }

    @Test
    public void testAuthenticateUser_incorrectPassword_throwsAuthenticationException() {
        // Given
        LoginRequest loginRequest = createRandomLoginRequest();

        UserDao userDao = createRandomObject(UserDao.class);

        when(userRepository.retrieveUserByUsername(any(String.class))).thenReturn(userDao);

        // When
        // Then
        AuthenticationException authenticationException =
                assertThrows(AuthenticationException.class, () ->  userService.authenticateUser(loginRequest));

        assertEquals(INCORRECT_PASSWORD, authenticationException.getError());
        assertEquals(BAD_REQUEST, authenticationException.getHttpStatus());
    }

    @Test
    public void testCreateUser_success_returnsUserResponse() {
        // Given
        NewUserRequest newUserRequest = createRandomNewUserRequest();

        UserDao userDao = createRandomObject(UserDao.class);

        when(userRepository.save(any(UserDao.class))).thenReturn(userDao);

        // When
        UserResponse userResponse = userService.createUser(newUserRequest);

        // Then
        assertEquals(userDao.getId(), userResponse.id());
        assertEquals(userDao.getUsername(), userResponse.username());
        assertEquals(userDao.getFirstName(), userResponse.firstName());
        assertEquals(userDao.getLastName(), userResponse.lastName());
        assertEquals(userDao.getEmail(), userResponse.email());
        assertEquals(userDao.getMobile(), userResponse.mobile());
        assertEquals(userDao.isVerified(), userResponse.isVerified());
    }
}
