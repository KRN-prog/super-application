package com.application.superapplication.sample;

import com.application.superapplication.repository.model.UserEntity;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserSample {
    public static UserEntity oneUserSample() {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setName("jean");
        user.setAge(5);
        user.setHeight(200);

        return user;
    }
    public static UserEntity oneOtherUserSample() {
        UserEntity user = new UserEntity();
        user.setId(2);
        user.setName("jeanne");
        user.setAge(15);
        user.setHeight(220);

        return user;
    }

    public static UserEntity oneModifiedUserSample() {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setName("julien");
        user.setAge(26);
        user.setHeight(165);

        return user;
    }
}
