package cn.yuzhiwei.schoolhelper.service.impl;

import cn.yuzhiwei.schoolhelper.domain.Result;
import cn.yuzhiwei.schoolhelper.domain.Score;
import cn.yuzhiwei.schoolhelper.domain.User;
import cn.yuzhiwei.schoolhelper.service.CourseService;
import cn.yuzhiwei.schoolhelper.service.LoginService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private CourseService courseService;
    @Override
    public Result<Object> login(User user) throws IOException {

        Connection connect = Jsoup.connect("http://authserver.hlju.edu.cn/authserver/login");
        Connection.Response dayu = connect.userAgent("Dayu").followRedirects(false).ignoreContentType(true)
                .method(Connection.Method.GET).execute();
        Map<String,String> cookie = new HashMap<>();
        cookie.putAll(dayu.cookies());
        String body = dayu.body();
        Document bodyDocument = Jsoup.parse(body);
        Element casLoginForm = bodyDocument.getElementById("casLoginForm");
        Elements inputs = casLoginForm.getElementsByTag("input");
        Map<String, String> postForm = new HashMap<>();
        for (Element input : inputs) {
            if ("username".equals(input.attr("name"))) {
                postForm.put(input.attr("name"), user.getUsername());
            } else if ("password".equals(input.attr("name"))) {
                postForm.put(input.attr("name"), user.getPassword());
            } else if (input.attr("name").length() > 0) {
                postForm.put(input.attr("name"), input.attr("value"));
            }
        }
        Connection.Response action = Jsoup.connect("http://authserver.hlju.edu.cn"+casLoginForm.attr("action")).data(postForm).method(Connection.Method.POST)
                .ignoreContentType(true).followRedirects(false).cookies(cookie).execute();
        cookie.putAll(action.cookies());
        System.out.println(cookie);
        int statusCode = action.statusCode();
        if(statusCode==200) {
            return new Result<>(1,"登录失败","");
        } else if (statusCode == 302) {
            return courseService.getScoreList(cookie);
        }
        return new Result<>(-1,"未知错误","");
    }
}
