package dev.andrewjfei.xploreservice.util;

import dev.andrewjfei.xploreservice.transaction.request.NewUserRequest;
import org.jeasy.random.EasyRandom;

public class RandomUtil {

    private static final EasyRandom easyRandom = new EasyRandom();

    public static <T> T createRandomObject(Class<T> type) {
        return easyRandom.nextObject(type);
    }

    public static NewUserRequest createRandomNewUserRequest() {
        return new NewUserRequest(
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class)
        );
    }
}
