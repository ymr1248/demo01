package com.yongxing.activity.demo01.Bean;

/**
 * Created by Administrator on 2018-01-29.
 */

public class UserGradeBean {
    /**
     * id : 10
     * userGradeName : VIP8
     * userGrade : 10
     */

    private int id;
    private String userGradeName;
    private int userGrade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserGradeName() {
        return userGradeName;
    }

    public void setUserGradeName(String userGradeName) {
        this.userGradeName = userGradeName;
    }

    public int getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(int userGrade) {
        this.userGrade = userGrade;
    }

    @Override
    public String toString() {
        return "UserGradeBean{" +
                "id=" + id +
                ", userGradeName='" + userGradeName + '\'' +
                ", userGrade=" + userGrade +
                '}';
    }
}
