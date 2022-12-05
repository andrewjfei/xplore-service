package dev.andrewjfei.xploreservice.service;

import dev.andrewjfei.xploreservice.dao.UserDao;
import dev.andrewjfei.xploreservice.repository.UserRepository;
import dev.andrewjfei.xploreservice.transaction.request.NewUserRequest;
import dev.andrewjfei.xploreservice.transaction.response.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.andrewjfei.xploreservice.util.RandomUtil.createRandomNewUserRequest;
import static dev.andrewjfei.xploreservice.util.RandomUtil.createRandomObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser_returnsNewUserResponse() {
        NewUserRequest newUserRequest = createRandomNewUserRequest();
        UserDao userDao = createRandomObject(UserDao.class);

        when(userRepository.save(any(UserDao.class))).thenReturn(userDao);

        UserResponse userResponse = userService.createUser(newUserRequest);

        assertEquals(userDao.getId(), userResponse.id());
        assertEquals(userDao.getUsername(), userResponse.username());
        assertEquals(userDao.getFirstName(), userResponse.firstName());
        assertEquals(userDao.getLastName(), userResponse.lastName());
        assertEquals(userDao.getEmail(), userResponse.email());
        assertEquals(userDao.getMobile(), userResponse.mobile());
        assertEquals(userDao.isVerified(), userResponse.isVerified());
    }
}
