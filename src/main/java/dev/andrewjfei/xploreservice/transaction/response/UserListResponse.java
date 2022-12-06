package dev.andrewjfei.xploreservice.transaction.response;

import java.util.List;

public record UserListResponse(
        List<UserResponse> userList
) {

    @Override
    public List<UserResponse> userList() {
        return userList;
    }

    @Override
    public String toString() {
        return "UserListResponse{" +
                "userList=" + userList +
                '}';
    }
}
