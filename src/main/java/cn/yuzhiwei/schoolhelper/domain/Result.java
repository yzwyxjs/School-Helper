package cn.yuzhiwei.schoolhelper.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;
}
