package com.dzenang.userapi.util;

import com.dzenang.userapi.fixtures.UserFixtures;
import com.dzenang.userapi.model.User;
import com.dzenang.userapi.model.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.dzenang.userapi.fixtures.UserFixtures.user1;

class UserMapperTest {

    @Test
    void toUser() {
        UserEntity userEntity1 = UserFixtures.createUserEntity1(true);
        User mapperUser = UserMapper.toUser(userEntity1);
        Assertions.assertEquals(user1, mapperUser);
    }

    @Test
    void toUserEntity() {
        UserEntity userEntity1 = UserFixtures.createUserEntity1(true);
        UserEntity mapperUserEntity = UserMapper.toUserEntity(user1);
        Assertions.assertEquals(userEntity1, mapperUserEntity);
    }
}