package com.github.SemenovAlex888.VotingRestaurant.util;

import com.github.SemenovAlex888.VotingRestaurant.model.AbstractBaseEntity;
import com.github.SemenovAlex888.VotingRestaurant.util.exception.IllegalRequestDataException;
import com.github.SemenovAlex888.VotingRestaurant.util.exception.NotFoundException;

import java.time.LocalTime;

public class ValidationUtil {

    public static final LocalTime LIMIT_TIME_FOR_VOTING = LocalTime.of(11, 0, 0);

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalRequestDataException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
//      conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.id() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }
}
