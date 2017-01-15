package com.accumulate.user;

import com.accumulate.UserService;
import com.accumulate.base.BaseTest;
import com.accumulate.resp.Response;
import com.accumulate.resp.UserModel;
import com.accumulate.utils.ObjectUtils;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by tjwang on 2017/1/15.
 */
public class UserConsumerTest extends BaseTest {

    @Resource
    private UserService userService;

    @Test
    public void testUserService() {
        Response<UserModel> resp = userService.create("tian", "Aa123.");
        System.out.println("success : " + resp.isSuccess());
        UserModel user = resp.getData();
        System.out.println(ObjectUtils.toString(user));
    }

}
