package com.accumulate.service.impl;

import com.accumulate.UserService;
import com.accumulate.assembler.UserAssembler;
import com.accumulate.entity.User;
import com.accumulate.exception.ApplicationException;
import com.accumulate.exception.CommonErrorCode;
import com.accumulate.resp.Response;
import com.accumulate.resp.UserModel;
import com.accumulate.service.UserBizService;

import javax.annotation.Resource;

/**
 * 用户门面服务
 * Created by tjwang on 2017/1/13.
 */
public class UserDubboServiceImpl implements UserService {

    @Resource
    private UserBizService userBizService;

    @Override
    public Response<UserModel> create(String username, String password) {
        Response resp;
        try {
            User user = userBizService.register(username, password);
            UserModel model = UserAssembler.transfer(user);
            resp = Response.ok(model);
        } catch (ApplicationException ae) {
            resp = Response.fail(ae.getErrorCode().getCode(), ae.getMessage());
        } catch (Exception e) {
            resp = Response.fail(CommonErrorCode.SYSTEM);
        }

        return resp;
    }

}
