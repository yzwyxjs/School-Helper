package cn.yuzhiwei.schoolhelper.service;

import cn.yuzhiwei.schoolhelper.domain.Result;
import cn.yuzhiwei.schoolhelper.domain.User;

import java.io.IOException;

public interface LoginService {
    Result<Object> login(User user) throws IOException;
}
