package com.yongxing.activity.demo01.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018-01-31.
 */

public class MonitorListBean {


    /**
     * pageSize : 10
     * currentPage : 1
     * totalRecord : 2
     * totalPage : 1
     * dataList : [{"id":6,"monitorName":"fdasf","monitorIp":"fae","monitorType":"fae","monitorInfo":"dadf","monitorLocation":"fae","locatedTime":"Jan 25, 2018 2:33:22 PM","monitorStatus":5,"storageLocation":"eafe","monitorGroup":{"groupId":1,"groupName":"刘德华的组","groupType":"默认类型","user":{"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}},"user":{"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}},{"id":16,"monitorName":"测试1","monitorIp":"测试1","monitorType":"测试1","monitorInfo":"测试1","monitorLocation":"测试1","locatedTime":"Jan 31, 2018 2:58:56 PM","monitorStatus":1,"storageLocation":"测试1","monitorGroup":{"groupId":1,"groupName":"刘德华的组","groupType":"默认类型","user":{"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}},"user":{"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}}]
     */

    private int pageSize;
    private int currentPage;
    private int totalRecord;
    private int totalPage;
    private List<MonitorBean> dataList;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<MonitorBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<MonitorBean> dataList) {
        this.dataList = dataList;
    }


}
