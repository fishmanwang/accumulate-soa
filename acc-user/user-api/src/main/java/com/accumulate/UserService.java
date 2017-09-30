package com.accumulate;

import com.accumulate.resp.Response;
import com.accumulate.resp.UserModel;

/**
 * 用户服务
 * Created by tjwang on 2017/1/13.
 */
public interface UserService {

    /**
     * 通过用户民密码创建用户
     *
     * @param username
     * @param password
     * @return
     */
    Response<UserModel> create(String username, String password);

    Response<Void> hello();

}
