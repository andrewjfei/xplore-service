package dev.andrewjfei.xploreservice.transaction.request;

public record LoginRequest(
        String username,
        String password
) {
    @Override
    public String username() {
        return username;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
