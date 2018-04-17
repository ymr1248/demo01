package base;

import android.app.Application;

import com.yongxing.activity.demo01.Bean.UserBean;

/**
 * Created by Administrator on 2018-01-31.
 */

public class GlobalVaries extends Application{
    private int id;
    private String userAccount;
    private String userAddress;
    private String userPhone;
    private String userEMail;
    private String avatar;
    private String password;
    private String userName;
    private UserBean.UserGradeBean UserGrade;

    @Override
    public void onCreate() {
        super.onCreate();
    }

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

    public UserBean.UserGradeBean getUserGrade() {
        return UserGrade;
    }

    public void setUserGrade(UserBean.UserGradeBean userGrade) {
        UserGrade = userGrade;
    }
}
