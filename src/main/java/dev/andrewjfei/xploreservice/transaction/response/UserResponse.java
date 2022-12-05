package dev.andrewjfei.xploreservice.transaction.response;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        String firstName,
        String lastName,
        String email,
        String mobile,
        boolean isVerified
) {

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public String lastName() {
        return lastName;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String mobile() {
        return mobile;
    }

    @Override
    public boolean isVerified() {
        return isVerified;
    }

    @Override
    public String toString() {
        return "NewUserResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", isVerified=" + isVerified +
                '}';
    }
}
