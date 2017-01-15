package com.accumulate.assembler;

import com.accumulate.entity.User;
import com.accumulate.resp.UserModel;
import org.springframework.beans.BeanUtils;

/**
 * 转换User的dto、entity等，用于不同层的交互
 * Created by tjwang on 2017/1/12.
 */
public class UserAssembler {

    public static UserModel transfer(User user) {
        UserModel model = new UserModel();
        BeanUtils.copyProperties(user, model);
        return model;
    }

}
