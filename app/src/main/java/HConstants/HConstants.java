package HConstants;

public interface HConstants {


    String url = "http://192.168.0.101:8080/monitoring/";
    String imgUrl = "http://192.168.0.105:8080/monitoring/resources/img/avatar/";

    /**
     * Eventbus 常量
     *
     */
    interface EVENT {
        int LOGINSTATE = 1;
        int HOMEREFRESH = 0;
        int LOGINACTIVITY_CLOSE = 1;
        int NAME_REFRESH = 2;
        int BIND_PHONE = 3;
        int SEX = 4;
        int LOGIN_WEIXIN = 5;
        int BIND_EMAIL = 6;
        int VERSON = 2;
    }

    interface URL {
        //登录
        String LOGIN = url + "appUserLogin";
        //用户修改密码
        String AppUpdateMyPsw = url + "appUpdateMyPsw";
        //修改用户信息
        String AppUserSelfUpdate = url + "appUserSelfUpdate";
        //用户注册
        String appRegisterUser = url + "appRegisterUser";
        //监控器组列表
        String MonitorGroupList = url + "appMonitorGroupList";
        //添加监控器组
        String AppAddMonitorGroup = url + "appAddMonitorGroup";
        //更新监控器组
        String AppUpdateMonitorGroup = url + "appUpdateMonitorGroup";
        //删除监控器组
        String AppMonitorGroupDeleteBoth = url + "appMonitorGroupDeleteBoth";
        //删除监控器组和组下的监控器
        String AppMonitorGroupDeleteOnly = url + "appMonitorGroupDeleteOnly";
        //监控器列表
        String AppMonitorList = url + "appMonitorList";
        //监控器详情
        String AppMonitor = url + "appMonitor";
        //删除监控器
        String AppDeleteMonitor = url + "appDeleteMonitor";
        //添加监控器
        String AppAddMonitor = url + "appAddMonitor";
        //查询监控器  userId   monitorName
        String AppGetMonitorByName = url + "appGetMonitorByName ";
        //更新监控器信息
        String AppUpdateMonitor = url + "appUpdateMonitor";
        //删除多个监控器
        String AppDeleteMonitors = url + "appDeleteMonitors";
        //移动多个监控器
        String AppRemoveMonitors = url + "appRemoveMonitors";
        //视频列表
        String AppVideoList = url + "appVideoList";
        //查看视频详情
        String AppVideo = url + "appVideo";

        //添加截图
        String AppAddShots = url + "appAddShots";
        //返回截图列表
        String AppToUserScreenShotsListJsp = url + "appToUserScreenShotsListJsp";
        //修改截图信息
        String AppUpdateScreenShots = url + "appUpdateScreenShots";
        //删除截图
        String AppDeleteScreenShots = url + "appDeleteScreenShots";
        //获取个人空间
        String AppGetSpace = url + "appGetSpace";


    }

    interface KEY {
       /* String noNetWork = "NONetWork";
        String userCode = "userCode";
        String soundSex = "soundSex";
        String RegId = "regId";

        String nickName = "QQnickName";
        String QQName = "QQName";
        String weixinNickName = "weixinNickName";
        String figureurl = "QQfigureurl";
        String city = "QQcity";
        String openid = "QQopenid";
        String unionId = "QQunionid";
        //QQ登录就为1 手机登录2 微信为3
        String LoginType = "LoginType";

        String Email = "email";

        String phone = "Phone";
        String pwd = "pwd";

        String netWork = "netWork";
        //1为登录  0为未登录
        String loginStatus = "loginStatus";
        //
        String userID = "userID";

        String signName = "signName";
        //背景图片
        String bgImg = "bgImg";
        //背景图片
        String remindImage = "remindImage";

        String sharePdData = "sharePdData";
        String timeVersion=" timeVersion";
        //设置软件的版本
        int TimeVersion = 1;

        String userCity = "userCity";

        String urlKey = "urlKey";

        String urlTitle = "urlTitle";

        String MessageId = "MessageId";

        String sex = "sex";
        //0是选择首页背景图片 1是共享
        String selectPhoneType = "selectPhoneType";

        String historyDetail = "historyDetail";

        String WeiXinCode = "WeiXinCode";
        //
        String OppUserCode = "OppUserCode";

        String OppclassCode = "OppclassCode";

        String TwoUrl = "TwoUrl";

        String TwoImg = "TwoImg";

        String TwoName = "TwoName";

        String TwoAddress = "TwoAddress";

        String TwoUse = "TwoUse";

        String friendImg = "friendImg";
        String friendName = "friendName";
        String friendId = "friendId";
        String friendSign = "friendSign";
        String friendCity = "friendCity";
        String friendSex = "friendSex";
        String isRepeat = "isRepeat";
        String friendUserCode = "friendUserCode";
        //加好友的名字
        String PfriendName = "PfriendName";

        //推送类型
        String PushType = "PushType";

        String TwoOnReceiver = "TwoOnReceiver";

        String TwoResult = "TwoResult";

        //0是登录 1//是分享
        String WXType = "WXType";
        //标记只能接受一次好友
        String HReceiverFriend = "HReceiverFriend";
        String birthday = "birthday";

        String onceLogin = "onceLogin";


        String ClockTag = "ClockTag";
        String ClockNum = "ClockNum";
        String ClockBroad = "ClockBroad";
        String onceWeixin = "onceWeixin";
        String threeLoginType = "threeLoginType";

String getRemindForService="getRemindForService";
        //标记是从看一看进去  0不是  1是
        String Look = "LookLook";*/

    }
}
