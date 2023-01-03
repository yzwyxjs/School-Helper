package cn.yuzhiwei.schoolhelper.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Score {
    private String term;
    private String courseId;
    private String courseName;
    private String courseType;
    private String courseAttr;
    private double score;
    private int grade;
    private String studyType;
}
