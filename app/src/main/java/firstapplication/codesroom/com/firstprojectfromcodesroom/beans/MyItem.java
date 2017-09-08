package firstapplication.codesroom.com.firstprojectfromcodesroom.beans;

import java.io.Serializable;

/**
 * Created by spice on 19/08/17.
 */

public class MyItem implements Serializable {

    private String id;
    private String name;
    private String desc;
    private String own;
    private String url;
    private String thum;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThum() {
        return thum;
    }

    public void setThum(String thum) {
        this.thum = thum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }
}
