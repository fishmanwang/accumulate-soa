/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.accumulate;

import com.accumulate.resp.Response;
import com.accumulate.resp.UserModel;
import com.accumulate.utils.ObjectMapperUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tjwang
 * @version $Id: UserConsumer.java, v 0.1 2017/9/30 0030 17:00 tjwang Exp $
 */
public class UserConsumer {

    public static void main(String[] args) {
        //com.alibaba.dubbo.container.Main.main(args);
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext(new String[]{"classpath*:META-INF/*-reference.xml"});
        app.start();

        UserService us = (UserService) app.getBean(UserService.class);
        Response<UserModel> resp = us.create("aaa", "bbb");
        resp = us.create("aaa", "bbb");
        resp = us.create("aaa", "bbb");
        System.out.println(ObjectMapperUtil.writePretty(resp));
    }

}
