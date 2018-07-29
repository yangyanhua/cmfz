package com.yangyh.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

//@Data// get set tostring equals
//@NoArgsConstructor//无参构造
/*@AllArgsConstructor//全参构造
@Accessors(chain = true) //链式调用*/
public class Essay  implements Serializable {
    private String id;
    private String title;
    private String content;
    @JSONField(format = "yyyy-MM-dd ")
    private Date issuetime;
    @JSONField(format = "yyyy-MM-dd ")
    private Date time;
    //关系属性
    private Guru gurn;

    public Essay(){
        super();
    }

    @Override
    public String toString() {
        return "Essay{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", issuetime=" + issuetime +
                ", time=" + time +
                ", gurn=" + gurn +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getIssuetime() {
        return issuetime;
    }

    public void setIssuetime(Date issuetime) {
        this.issuetime = issuetime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Guru getGurn() {
        return gurn;
    }

    public void setGurn(Guru gurn) {
        this.gurn = gurn;
    }
}
