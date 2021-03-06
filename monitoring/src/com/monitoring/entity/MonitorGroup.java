package com.monitoring.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;

import javax.persistence.*;

@Entity
@Table(name = "monitorgroup")
public class MonitorGroup {
   @Id
   @Column(name = "groupId",nullable = false,unique = true)
   @GenericGenerator(name = "generator", strategy = "uuid")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;
   //分组名称
   @Column(name = "groupName",nullable = false,length = 32)
    private String groupName;
   //分组类型
   @Column(name = "groupType",nullable = false,length = 32)
    private String groupType;

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

    @Override
    public String toString() {
        return "MonitorGroup{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", groupType='" + groupType + '\'' +
                '}';
    }
}
