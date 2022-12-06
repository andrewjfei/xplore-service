package dev.andrewjfei.xploreservice.transaction.response;

public record ErrorResponse(
        String message,
        int code
) {

    @Override
    public String message() {
        return message;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
