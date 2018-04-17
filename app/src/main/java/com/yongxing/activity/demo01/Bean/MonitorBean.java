package com.yongxing.activity.demo01.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018-01-31.
 */

public class MonitorBean {
    /**
     * id : 6
     * monitorName : fdasf
     * monitorIp : fae
     * monitorType : fae
     * monitorInfo : dadf
     * monitorLocation : fae
     * locatedTime : Jan 25, 2018 2:33:22 PM
     * monitorStatus : 5
     * storageLocation : eafe
     * monitorGroup : {"groupId":1,"groupName":"刘德华的组","groupType":"默认类型","user":{"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}}
     * user : {"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}
     */

    private int id;
    private String monitorName;
    private String monitorIp;
    private String monitorType;
    private String monitorInfo;
    private String monitorLocation;
    private String locatedTime;
    private int monitorStatus;
    private String storageLocation;
    private MonitorGroupBean monitorGroup;
    private UserBean user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    public String getMonitorIp() {
        return monitorIp;
    }

    public void setMonitorIp(String monitorIp) {
        this.monitorIp = monitorIp;
    }

    public String getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    public String getMonitorInfo() {
        return monitorInfo;
    }

    public void setMonitorInfo(String monitorInfo) {
        this.monitorInfo = monitorInfo;
    }

    public String getMonitorLocation() {
        return monitorLocation;
    }

    public void setMonitorLocation(String monitorLocation) {
        this.monitorLocation = monitorLocation;
    }

    public String getLocatedTime() {
        return locatedTime;
    }

    public void setLocatedTime(String locatedTime) {
        this.locatedTime = locatedTime;
    }

    public int getMonitorStatus() {
        return monitorStatus;
    }

    public void setMonitorStatus(int monitorStatus) {
        this.monitorStatus = monitorStatus;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public MonitorGroupBean getMonitorGroup() {
        return monitorGroup;
    }

    public void setMonitorGroup(MonitorGroupBean monitorGroup) {
        this.monitorGroup = monitorGroup;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MonitorBean{" +
                "id=" + id +
                ", monitorName='" + monitorName + '\'' +
                ", monitorIp='" + monitorIp + '\'' +
                ", monitorType='" + monitorType + '\'' +
                ", monitorInfo='" + monitorInfo + '\'' +
                ", monitorLocation='" + monitorLocation + '\'' +
                ", locatedTime='" + locatedTime + '\'' +
                ", monitorStatus=" + monitorStatus +
                ", storageLocation='" + storageLocation + '\'' +
                ", monitorGroup=" + monitorGroup.toString() +
                ", user=" + user.toString() +
                '}';
    }
}
