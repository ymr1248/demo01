package com.yongxing.activity.demo01.Bean;

/**
 * Created by Administrator on 2018-01-29.
 */

public class UserBean {


    /**
     * id : 19
     * userAccount : wnf
     * userAddress : 吴乃福的座
     * userPhone : 1234567890
     * userEMail : 15465@1544
     * avatar : a9ec5586-71c2-4425-91f6-bb32cbd4af4f.jpg
     * password : 123456
     * userName : 吴乃福
     * UserGrade : {"id":10,"userGradeName":"VIP8","userGrade":10}
     */

    private int id;
    private String userAccount;
    private String userAddress;
    private String userPhone;
    private String userEMail;
    private String avatar;
    private String password;
    private String userName;
    private UserGradeBean UserGrade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEMail() {
        return userEMail;
    }

    public void setUserEMail(String userEMail) {
        this.userEMail = userEMail;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserGradeBean getUserGrade() {
        return UserGrade;
    }

    public void setUserGrade(UserGradeBean UserGrade) {
        this.UserGrade = UserGrade;
    }

    public static class UserGradeBean {
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
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", userAccount='" + userAccount + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEMail='" + userEMail + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", UserGrade=" + UserGrade.toString() +
                '}';
    }
}
