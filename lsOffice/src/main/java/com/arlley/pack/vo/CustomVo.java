package com.arlley.pack.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CustomVo implements Serializable {

    private int id;

    private String name;

    private String tel;

    private String region;

    private String assign;

    private String type;

    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;
}
