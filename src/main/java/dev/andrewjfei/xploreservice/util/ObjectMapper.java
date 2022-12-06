package dev.andrewjfei.xploreservice.util;

import dev.andrewjfei.xploreservice.dao.UserDao;
import dev.andrewjfei.xploreservice.enumeration.Error;
import dev.andrewjfei.xploreservice.transaction.request.NewUserRequest;
import dev.andrewjfei.xploreservice.transaction.response.ErrorResponse;
import dev.andrewjfei.xploreservice.transaction.response.UserListResponse;
import dev.andrewjfei.xploreservice.transaction.response.UserResponse;
import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    public static UserDao toDao(NewUserRequest newUserRequest) {
        return new UserDao(
                newUserRequest.username(),
                newUserRequest.firstName(),
                newUserRequest.lastName(),
                newUserRequest.email(),
                newUserRequest.mobile(),
                newUserRequest.password()
        );
    }

    public static UserResponse toResponse(UserDao userDao) {
        return new UserResponse(
                userDao.getId(),
                userDao.getUsername(),
                userDao.getFirstName(),
                userDao.getLastName(),
                userDao.getEmail(),
                userDao.getMobile(),
                userDao.isVerified()
        );
    }

    public static UserListResponse toResponse(List<UserDao> userDaoList) {
        List<UserResponse> userResponseList = new ArrayList<>();

        for (UserDao userDao : userDaoList) {
            userResponseList.add(toResponse(userDao));
        }

        return new UserListResponse(userResponseList);
    }

    public static ErrorResponse toResponse(Error error) {
        return new ErrorResponse(error.message, error.code);
    }
}
