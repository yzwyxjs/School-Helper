package cn.yuzhiwei.schoolhelper.controller;

import cn.yuzhiwei.schoolhelper.domain.Result;
import cn.yuzhiwei.schoolhelper.domain.User;
import cn.yuzhiwei.schoolhelper.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public Result<Object> login(@RequestBody User user) throws IOException {
        return loginService.login(user);
    }
}
