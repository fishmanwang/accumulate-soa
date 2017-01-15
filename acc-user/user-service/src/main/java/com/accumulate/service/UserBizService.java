package com.accumulate.service;

import com.accumulate.entity.User;
import com.accumulate.entity.UserExample;
import com.accumulate.exception.ApplicationException;
import com.accumulate.exception.UserErrorCode;
import com.accumulate.mapper.UserMapper;
import com.accumulate.service.password.PasswordPolicy;
import com.accumulate.service.password.PolicyTip;
import com.accumulate.service.password.encrypt.PwdHashPolicy;
import com.accumulate.service.password.encrypt.PwdHashPolicyLookup;
import com.accumulate.service.factory.PasswordPolicyFactory;
import com.accumulate.utils.StringUtils;
import com.google.common.base.Preconditions;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户内部服务逻辑
 * Created by tjwang on 2017/1/13.
 */
@Service
public class UserBizService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public int create(User user) {
        logger.debug("Execute UserService.add");
        return userMapper.insert(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public User register(String username, String password) {
        Preconditions.checkArgument(StringUtils.isNotBlank(username), "注册用户名不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(password), "注册密码不能为空");

        boolean exists = checkExists(username);
        if (exists) {
            throw ApplicationException.buildWithPlaceholder(UserErrorCode.USER_NAME_NOT_EXISITS, username);
        }

        PasswordPolicy pp = PasswordPolicyFactory.getDefault();
        PolicyTip tip = pp.check(password);
        if (!tip.isPass()) {
            throw ApplicationException.build(UserErrorCode.USER_PASSWORD_VIOLATE, tip.getTip());
        }

        Date now = DateTime.now().toDate();

        User user = new User();
        user.setUsername(username);
        //user.setDisplayName("王");
        user.setCreateTime(now);
        user.setUpdateTime(now);
        userMapper.insertSelective(user);

        PwdHashPolicy pwdHashPolicy = PwdHashPolicyLookup.getMD5();
        String encryptPwd = pwdHashPolicy.encryptPassword(password, String.valueOf(user.getId()));

        user.setPassword(encryptPwd);
        userMapper.updateByPrimaryKeySelective(user);

        return user;
    }

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    public User findUser(String username) {
        UserExample example = new UserExample();
        UserExample.Criteria c = example.createCriteria();
        c.andUsernameEqualTo(username);

        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 0) {
            throw ApplicationException.buildWithPlaceholder(UserErrorCode.USER_NAME_NOT_EXISITS, username);
        }
        if (users.size() > 1) {
            throw ApplicationException.build(UserErrorCode.USER_NAME_DUPLICATE);
        }
        return users.get(0);
    }

    private boolean checkExists(String username) {
        UserExample example = new UserExample();
        UserExample.Criteria c = example.createCriteria();
        c.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() > 0) {
            return true;
        }
        return false;
    }

    public void delete(int id) {
        Preconditions.checkArgument(id != 0, "非法参数");
        userMapper.deleteByPrimaryKey(id);
    }

    public void deleteByUsername(String username) {
        Preconditions.checkArgument(StringUtils.isNotBlank(username), "待删除用户名不能为空");

        UserExample example = new UserExample();
        UserExample.Criteria c = example.createCriteria();
        c.andUsernameEqualTo(username);
        userMapper.deleteByExample(example);
    }

}
