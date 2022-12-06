package dev.andrewjfei.xploreservice.util;

import dev.andrewjfei.xploreservice.transaction.request.LoginRequest;
import dev.andrewjfei.xploreservice.transaction.request.NewUserRequest;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.postgresql.geometric.PGpoint;

import static java.lang.Math.random;

public class RandomUtil {

    private static final EasyRandomParameters easyRandomParameters = new EasyRandomParameters()
            .randomize(PGpoint.class, () -> new PGpoint(random(), random()));

    private static final EasyRandom easyRandom = new EasyRandom(easyRandomParameters);

    public static <T> T createRandomObject(Class<T> type) {
        return easyRandom.nextObject(type);
    }

    public static LoginRequest createRandomLoginRequest() {
        return new LoginRequest(
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class)
        );
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
