package cn.yuzhiwei.schoolhelper.service;

import cn.yuzhiwei.schoolhelper.domain.Result;
import cn.yuzhiwei.schoolhelper.domain.Score;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CourseService {
    Result<Object> getScoreList(Map<String,String> cookieMap) throws IOException;
}
