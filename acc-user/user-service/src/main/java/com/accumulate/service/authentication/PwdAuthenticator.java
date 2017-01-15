package com.accumulate.service.authentication;

import com.accumulate.entity.User;
import com.accumulate.utils.ValidatorHelper;
import org.springframework.stereotype.Component;

@Component
public class PwdAuthenticator extends AbstractAuthenticator<UserLoginPwdCredential> {

//    @Resource
//    private UserBizService userBizService;

    @Override
    protected User exchangeByCredential(UserLoginPwdCredential credential) {
        ValidatorHelper.validate(credential);

        String username = credential.getSubject();

        //userBizService.findByUsername(username);

        return null;
    }

}
