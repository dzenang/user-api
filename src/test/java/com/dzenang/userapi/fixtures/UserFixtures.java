package com.dzenang.userapi.fixtures;

import com.dzenang.userapi.model.User;
import com.dzenang.userapi.model.entity.UserEntity;

public class UserFixtures {
    public static final String userName1 = "testName1";
    public static final String userName2 = "testName2";
    public static final String userEmail1 = "testEmail1";
    public static final String userEmail2 = "testEmail2";

    public static UserEntity createUserEntity1(boolean includeId) {
        UserEntity userEntity = new UserEntity();
        if(includeId){
            userEntity.setId(1L);
        }
        userEntity.setName(userName1);
        userEntity.setEmail(userEmail1);
        return userEntity;
    }

    public static UserEntity createUserEntity2(boolean includeId) {
        UserEntity userEntity = new UserEntity();
        if(includeId){
            userEntity.setId(2L);
        }
        userEntity.setName(userName2);
        userEntity.setEmail(userEmail2);
        return userEntity;
    }

    public static final User user1 = new User(1L, userName1, userEmail1);
    public static final User user2 = new User(2L, userName2, userEmail2);
}
