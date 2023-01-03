package cn.yuzhiwei.schoolhelper.service.impl;

import cn.yuzhiwei.schoolhelper.domain.Result;
import cn.yuzhiwei.schoolhelper.domain.Score;
import cn.yuzhiwei.schoolhelper.service.CourseService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {


    @Override
    public Result<Object> getScoreList(Map<String, String> cookieMap) throws IOException {
        Connection idsCon = Jsoup.connect("http://ssfw1.hlju.edu.cn/ssfw/j_spring_ids_security_check");
        Connection.Response idsExecute = idsCon.method(Connection.Method.GET).cookies(cookieMap)
                .followRedirects(true).ignoreContentType(true)
                .execute();
        cookieMap.putAll(idsExecute.cookies());


        Connection.Response scoreExe = Jsoup.connect("http://ssfw1.hlju.edu.cn/ssfw/zhcx/cjxx.do")
                .ignoreContentType(true).cookies(cookieMap).execute();
        String body = scoreExe.body();
        Document parse = Jsoup.parse(body);
        Elements tables = parse.getElementsByClass("ui_table ui_table_hover ui_table_striped ui_table_style02");
        Element table = tables.get(0);
        List<Score> scores = new ArrayList<>();
        Elements scoreTrs = table.getElementsByClass("t_con");
        for (Element scoreTr : scoreTrs) {
            Elements tds = scoreTr.getElementsByTag("td");
            Score score = new Score(tds.get(1).text(), tds.get(2).text(), tds.get(3).text(),
                    tds.get(4).text(), tds.get(5).text(),
                    Double.parseDouble(tds.get(6).text()), Integer.parseInt(tds.get(7).text()), tds.get(8).text());
            scores.add(score);
        }
        System.out.println(scores);
        return new Result<>(0,"成功",scores);
    }
}
