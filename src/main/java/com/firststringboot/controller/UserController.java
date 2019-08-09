package com.firststringboot.controller;

import com.firststringboot.common.rabbit.MsgProducer;
import com.firststringboot.common.utils.FTPUtil;
import com.firststringboot.common.utils.auth;
import com.firststringboot.repository.domain.User;
import com.firststringboot.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MsgProducer msgProducer;


    @auth(permissions = "1234,admin")
    @RequestMapping("getUser")
    public User getUser() {
        return userService.getUser();
    }

    @RequestMapping("addUser")
    public String addUser() throws Exception {
        return userService.addUser();
    }

    @RequestMapping("testToken")
    public String testToken() {
        return userService.testToken();
    }

    @RequestMapping("testRabbit")
    public String testRabbit() {

        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setNickName("i" + i);
            msgProducer.send(user);
        }

        return "success";
    }

    @RequestMapping("loginFtp")
    public String loginFtp() throws Exception {
        FTPUtil ftpUtil = new FTPUtil();
        ftpUtil.openConnect();
        return "success";
    }

    @RequestMapping("testUploadFile")
    public String testUploadFile() throws Exception {
        FTPUtil ftpUtil = new FTPUtil();
        ftpUtil.download("test/file", "测试提成.txt", "firststringboot/src/main/");
        return "success";
    }

    @RequestMapping("getPath")
    public String getPath() {
        return System.getProperty("java.io.tmpdir");
    }

}
