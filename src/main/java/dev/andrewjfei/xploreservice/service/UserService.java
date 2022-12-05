package dev.andrewjfei.xploreservice.service;

import dev.andrewjfei.xploreservice.dao.UserDao;
import dev.andrewjfei.xploreservice.repository.UserRepository;
import dev.andrewjfei.xploreservice.transaction.request.NewUserRequest;
import dev.andrewjfei.xploreservice.transaction.response.UserListResponse;
import dev.andrewjfei.xploreservice.transaction.response.UserResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static dev.andrewjfei.xploreservice.util.ObjectMapper.toDao;
import static dev.andrewjfei.xploreservice.util.ObjectMapper.toResponse;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse createUser(NewUserRequest newUserRequest) {
        UserDao userDao = userRepository.save(toDao(newUserRequest));
        return toResponse(userDao);
    }

    public UserListResponse retrieveAllUsers() {
        List<UserDao> userDaoList = userRepository.retrieveAllUsers();
        return toResponse(userDaoList);
    }
}
