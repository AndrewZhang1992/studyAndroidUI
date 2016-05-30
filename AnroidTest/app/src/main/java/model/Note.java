package model;

import java.io.Serializable;

/**
 * Created by zhanglingyun on 16/5/30.
 */
public class Note implements Serializable {
    private int id;
    private String title;
    private String content;

    public Note (int nid,String ntitle,String ncontent)
    {
        // 直接访问成员变量

        id=nid;
        title=ntitle;
        content=ncontent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
