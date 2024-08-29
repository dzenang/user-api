package com.dzenang.userapi.util;

import com.dzenang.userapi.model.User;
import com.dzenang.userapi.model.entity.UserEntity;

public class UserMapper {
    private UserMapper() {}

    public static User toUser(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getEmail());
    }

    public static UserEntity toUserEntity(User user) {
        return new UserEntity(user.getId(), user.getName(), user.getEmail());
    }
}
