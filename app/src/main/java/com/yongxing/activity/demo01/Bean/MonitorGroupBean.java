package com.yongxing.activity.demo01.Bean;

/**
 * Created by Administrator on 2018-01-31.
 */

public class MonitorGroupBean {

    /**
     * groupId : 1
     * groupName : 刘德华的组
     * groupType : 默认类型
     * user : {"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}
     */

    private int groupId;
    private String groupName;
    private String groupType;
    private UserBean user;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MonitorGroupBean{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", groupType='" + groupType + '\'' +
                ", user=" + user.toString() +
                '}';
    }
}

