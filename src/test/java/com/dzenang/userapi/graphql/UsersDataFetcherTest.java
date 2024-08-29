package com.dzenang.userapi.graphql;

import com.dzenang.userapi.fixtures.UserFixtures;
import com.dzenang.userapi.repository.UserEntityRepository;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UsersDataFetcherTest {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private DgsQueryExecutor dgsQueryExecutor;

    @BeforeEach
    void setUp() {
        userEntityRepository.saveAll(List.of(UserFixtures.createUserEntity1(false),
                UserFixtures.createUserEntity2(false)));
    }

    @Test
    void users() {
        List<String> userNames = dgsQueryExecutor.executeAndExtractJsonPath(
                """
                        {
                            users{
                                name
                            }
                        }
                       """, "data.users[*].name");
        Assertions.assertEquals(2, userNames.size());
        Assertions.assertTrue(userNames.containsAll(List.of(UserFixtures.userName1, UserFixtures.userName2)));
    }

    @Test
    void user() {
        String actualName = dgsQueryExecutor.executeAndExtractJsonPath(
                """
                        {
                             user(id: 1){
                                 name
                             }
                         }
                       """, "data.user.name");
        Assertions.assertEquals(UserFixtures.userName1, actualName);
    }

    @AfterEach
    void tearDown() {
        userEntityRepository.deleteAll();
    }
}