package com.yongxing.activity.demo01.Bean;

/**
 * Created by Administrator on 2018-01-29.
 */

public class SpaceBean {

    /**
     * id : 1
     * spaceSize : 12
     * useSize : 12
     * extraSize : 141
     * spaceName : 福的云空间
     * user : {"id":19,"userAccount":"wnf","userAddress":"吴乃福的座","userPhone":"1234567890","userEMail":"15465@1544","avatar":"D:\\Say\\apache-tomcat-8.5.6\\webapps\\monitoring\\resources/img/avatar/6a272c6b-5b60-4c90-ab0d-5e4ccda0e6b5.jpg","password":"123456","userName":"吴乃福","UserGrade":{"id":10,"userGradeName":"VIP8","userGrade":10}}
     */

    private int id;
    private String spaceSize;
    private String useSize;
    private String extraSize;
    private String spaceName;
    private UserBean user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpaceSize() {
        return spaceSize;
    }

    public void setSpaceSize(String spaceSize) {
        this.spaceSize = spaceSize;
    }

    public String getUseSize() {
        return useSize;
    }

    public void setUseSize(String useSize) {
        this.useSize = useSize;
    }

    public String getExtraSize() {
        return extraSize;
    }

    public void setExtraSize(String extraSize) {
        this.extraSize = extraSize;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SpaceBean{" +
                "id=" + id +
                ", spaceSize='" + spaceSize + '\'' +
                ", useSize='" + useSize + '\'' +
                ", extraSize='" + extraSize + '\'' +
                ", spaceName='" + spaceName + '\'' +
                ", user=" + user.toString() +
                '}';
    }
}
