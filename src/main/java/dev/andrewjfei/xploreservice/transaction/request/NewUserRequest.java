package dev.andrewjfei.xploreservice.transaction.request;

public record NewUserRequest(
        String username,
        String firstName,
        String lastName,
        String email,
        String mobile,
        String password,
        String confirmedPassword
) {

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
    public String password() {
        return password;
    }

    @Override
    public String confirmedPassword() {
        return confirmedPassword;
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
