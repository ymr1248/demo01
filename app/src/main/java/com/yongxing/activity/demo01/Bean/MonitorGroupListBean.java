package com.yongxing.activity.demo01.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018-01-31.
 */

public class MonitorGroupListBean {


    /**
     * pageSize : 10
     * currentPage : 1
     * totalRecord : 7
     * totalPage : 1
     * dataList : [{"groupId":1,"groupName":"刘德华的组","groupType":"默认类型","user":{"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}},{"groupId":4,"groupName":"12","groupType":"张学友的组","user":{"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}},{"groupId":5,"groupName":"1","groupType":"2","user":{"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}},{"groupId":7,"groupName":"1","groupType":"4","user":{"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}},{"groupId":49,"groupName":"我的天","groupType":"打算噶女","user":{"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}},{"groupId":50,"groupName":"测试1","groupType":"测试1","user":{"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}},{"groupId":51,"groupName":"测试2","groupType":"测试2","user":{"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}}]
     */

    private int pageSize;
    private int currentPage;
    private int totalRecord;
    private int totalPage;
    private List<MonitorGroupBean> dataList;

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

    public List<MonitorGroupBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<MonitorGroupBean> dataList) {
        this.dataList = dataList;
    }



}
