package com.yongxing.activity.demo01.Bean;

/**
 * Created by Administrator on 2018-01-31.
 */

public class ResultBean {


    /**
     * resource : 1
     * jsonString : {"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}
     */

    private int resource;
    private String jsonString;

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "resource=" + resource +
                ", jsonString='" + jsonString + '\'' +
                '}';
    }
}
