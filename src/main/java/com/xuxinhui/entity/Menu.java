package com.xuxinhui.entity;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {

    private String id;
    private String content;
    private String url;
    private String iconcls;
    //关系属性
    private List<Menu> menu;

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", iconcls='" + iconcls + '\'' +
                ", menu=" + menu +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public Menu(){
        super();
    }
}
