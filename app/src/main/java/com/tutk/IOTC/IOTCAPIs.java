/******************************************************************************
 *                                                                            *
 * Copyright (c) 2011 by TUTK Co.LTD. All Rights Reserved.                    *
 * Class: IOTCAVApis.java                                                     *
 *                                                                            *
 * Author: joshua ju                                                          *
 * Date: 2011-05-14                                                           *

 Revisions of IOTCAVApis
 IOTCApis Version joined		Name		Date
 -------						----		----------
 0.5.0.0						Joshua		2011-06-30
 1.1.0.0						Joshua		2011-08-01
 1.2.0.0						Joshua      2011-08-30

 ******************************************************************************/

package com.tutk.IOTC;

public class IOTCAPIs {

    public static final int API_ER_ANDROID_NULL = -10000;
    //IOTCApis error code===================================================================================
    /**
     * 该函数成功执行。
     */
    public static final int IOTC_ER_NoERROR = 0;

    /**
     * IOTC服务器没有响应，可能是由许多类型的互联网连接问题引起的。
     * 看到(故障排除)(. . \故障诊断\你# IOTC_ER_SERVER_NOT_RESPONSE)
     */
    public static final int IOTC_ER_SERVER_NOT_RESPONSE = -1;

    /**
     * IOTC大师无法解决他们的域名，可能是由于
     * 通过网络连接或DNS设置问题。
     * 看到(故障排除)(. . \故障诊断\你# IOTC_ER_FAIL_RESOLVE_HOSTNAME)
     */
    public static final int IOTC_ER_FAIL_RESOLVE_HOSTNAME = -2;

    /**
     * IOTC模块已经初始化。没有必要重新初始化。
     */
    public static final int IOTC_ER_ALREADY_INITIALIZED = -3;

    /**
     * IOTC模块在进行初始化时不能创建互斥锁。请
     * 检查操作系统是否有足够的Mutexs用于IOTC平台。
     */
    public static final int IOTC_ER_FAIL_CREATE_MUTEX = -4;

    /**
     * IOTC模块无法创建线程。 请检查操作系统是否有能力
           为IOTC模块创建线程。
     */
    public static final int IOTC_ER_FAIL_CREATE_THREAD = -5;

    /**
     * IOTC模块无法创建套接字。 请检查OS是否支持套接字服务
     */
    public static final int IOTC_ER_FAIL_CREATE_SOCKET = -6;

    /**
     * IOTC模块未能设置套接字选项。
     */
    public static final int IOTC_ER_FAIL_SOCKET_OPT = -7;

    /**
     * IOTC模块未能绑定套接字
     */
    public static final int IOTC_ER_FAIL_SOCKET_BIND = -8;

    /**
     * 指定的UID没有许可证。
     * See [Troubleshooting](..\Troubleshooting\index.htm#IOTC_ER_UNLICENSE)
     */
    public static final int IOTC_ER_UNLICENSE = -10;

    /**
     * 该设备已成功登录
     */
    public static final int IOTC_ER_LOGIN_ALREADY_CALLED = -11;

    /**
     * IOTC模块尚未初始化。 请使用IOTC_Initialize（）或
           IOTC_Initialize2（）进行初始化。
     */
    public static final int IOTC_ER_NOT_INITIALIZED = -12;

    /**
     * 在执行某些IOTC期间，指定的超时已过期
           *模块服务。 对于大多数情况，这是由于远程响应缓慢造成的
           *网站或网络连接问题
     */
    public static final int IOTC_ER_TIMEOUT = -13;

    /**
     * 指定的IOTC会话ID无效
     */
    public static final int IOTC_ER_INVALID_SID = -14;

    /**
     * IOTC服务器不知道指定设备的名称
     */
    public static final int IOTC_ER_UNKNOWN_DEVICE = -15;

    /**
     * IOTC模块无法获得本地IP地址
     * See [Troubleshooting](..\Troubleshooting\index.htm#IOTC_ER_FAIL_GET_LOCAL_IP)
     */
    public static final int IOTC_ER_FAIL_GET_LOCAL_IP = -16;

    /**
     * 该设备已开始侦听来自客户端的连接。 它是
           *不必再听。
     */
    public static final int IOTC_ER_LISTEN_ALREADY_CALLED = -17;

    /**
     * OTC会议的数量已达到最大。
           *请使用IOTC_Set_Max_Session_Number（）设置IOTC会话的最大数量
     */
    public static final int IOTC_ER_EXCEED_MAX_SESSION = -18;

    /**
     *IOTC服务器无法找到指定的设备，可能是由于
           *与设备断开连接或该设备尚未登录。
     */
    public static final int IOTC_ER_CAN_NOT_FIND_DEVICE = -19;

    /**
     * 客户端正在连接到设备。 禁止再次连接。
     */
    public static final int IOTC_ER_CONNECT_IS_CALLING = -20;

    /**
     * 远程站点已经关闭了这个IOTC会话。
           *请致电IOTC_Session_Close（）在定位站点释放IOTC会话资源。
     */
    public static final int IOTC_ER_SESSION_CLOSE_BY_REMOTE = -22;

    /**
     * 此IOTC会话被断开，因为远程站点没有任何响应
           *指定的超时后到期。
     */
    public static final int IOTC_ER_REMOTE_TIMEOUT_DISCONNECT = -23;

    /**
     * 客户端无法连接到设备，因为设备没有监听连接。
     * See [Troubleshooting](..\Troubleshooting\index.htm#IOTC_ER_DEVICE_NOT_LISTENING)
     */
    public static final int IOTC_ER_DEVICE_NOT_LISTENING = -24;

    /**
     * 传输数据前，指定通道ID的IOTC通道未打开。
     */
    public static final int IOTC_ER_CH_NOT_ON = -26;

    /**
     * 客户端通过调用IOTC_Connect_Stop（）来停止连接到设备
     */
    public static final int IOTC_ER_FAIL_CONNECT_SEARCH = -27;

    /**
     * 当初始化IOTC模块时，指定的主设备太少。
           *最少需要两个主设备进行初始化。
     */
    public static final int IOTC_ER_MASTER_TOO_FEW = -28;

    /**
     *由于密钥不正确，客户端无法通过设备认证。
     */
    public static final int IOTC_ER_AES_CERTIFY_FAIL = -29;

    /**
     * IOTC会话的IOTC通道数已达到最大值，例如MAX_CHANNEL_NUMBER。
     */
    public static final int IOTC_ER_SESSION_NO_FREE_CHANNEL = -31;

    /**
     * ???所有TCP端口80,443,8000,8080无法使用
     */
    public static final int IOTC_ER_TCP_TRAVEL_FAILED = -32;

    /**
     * 无法连接到TCP中的IOTC服务器
     * See [Troubleshooting](..\Troubleshooting\index.htm#IOTC_ER_TCP_CONNECT_TO_SERVER_FAILED)
     */
    public static final int IOTC_ER_TCP_CONNECT_TO_SERVER_FAILED = -33;

    /**
     * 客户希望在该设备处于非安全模式下连接到设备
           *仅支持安全模式。
     */
    public static final int IOTC_ER_CLIENT_NOT_SECURE_MODE = -34;

    /**
     * 客户希望在设备处于安全模式时连接到设备
           *不支持安全模式。
     */
    public static final int IOTC_ER_CLIENT_SECURE_MODE = -35;

    /**
     * 在安全模式下，设备不支持连接
     */
    public static final int IOTC_ER_DEVICE_NOT_SECURE_MODE = -36;

    /**
     * 设备不支持非安全模式下的连接
     */
    public static final int IOTC_ER_DEVICE_SECURE_MODE = -37;

    /**
     * 在IOTC_Listen2（），IOTC_Connect_ByUID2（）中指定的IOTC会话模式
           *或IOTC_Connect_ByName2（）无效。
           *请参阅IOTCConnectionMode了解可能的模式。
     */
    public static final int IOTC_ER_INVALID_MODE = -38;

    /**
     * 一个设备停止监听客户的连接.
     */
    public static final int IOTC_ER_EXIT_LISTEN = -39;

    /**
     * 指定的设备不支持高级功能
           *（TCP中继和P2PTunnel模块）
     */
    public static final int IOTC_ER_NO_PERMISSION = -40;

    /**
     * 网络是不可访问的，请检查网络设置
     */
    public static final int IOTC_ER_NETWORK_UNREACHABLE = -41;

    /**
     * 客户端无法通过中继模式连接到设备
     */
    public static final int IOTC_ER_FAIL_SETUP_RELAY = -42;

    /**
     * 客户端无法使用UDP中继模式连接到设备
            因为IOTC服务器不支持该设备的UDP中继模式
     */
    public static final int IOTC_ER_NOT_SUPPORT_RELAY = -43;

    /**
     * 设备登录或客户端连接时无IOTC服务器信息
           *因为没有IOTC服务器正在运行或者没有添加IOTC服务器列表
     */
    public static final int IOTC_ER_NO_SERVER_LIST = -44;

    /**
     * 连接设备复制了日志记录和可能无法连接的设备。
     */
    public static final int IOTC_ER_DEVICE_MULTI_LOGIN = -45;

    /**
     * 传递给函数的参数是无效的。
     */
    public static final int IOTC_ER_INVALID_ARG = -46;

    /**
     * 远程设备不支持部分编码
     */
    public static final int IOTC_ER_NOT_SUPPORT_PE = -47;

    /**
     * 远程设备不能再连接空闲会话。
     */
    public static final int IOTC_ER_DEVICE_EXCEED_MAX_SESSION = -48;

    /**
     * 函数调用是一个阻塞调用，并被其他线程调用。
     */
    public static final int IOTC_ER_BLOCKED_CALL = -49;

    /**
     * The session was closed.
     */
    public static final int IOTC_ER_SESSION_CLOSED = -50;

    /**
     * 远程不支持此功能。
     */
    public static final int IOTC_ER_REMOTE_NOT_SUPPORTED = -51;

    /**
     * 该功能被相关功能中止。
     */
    public static final int IOTC_ER_ABORTED = -52;

    /**
     * 缓冲区大小超过最大数据包大小。
     */
    public static final int IOTC_ER_EXCEED_MAX_PACKET_SIZE = -53;

    /**
     * 服务器不支持此功能。
     */
    public static final int IOTC_ER_SERVER_NOT_SUPPORT = -54;

    /**
     * 无法找到写入数据的路径
     */
    public static final int IOTC_ER_NO_PATH_TO_WRITE_DATA = -55;

    /**
     * 启动功能未被调用
     */
    public static final int IOTC_ER_SERVICE_IS_NOT_STARTED = -56;

    /**
     * 已经在处理中
     */
    public static final int IOTC_ER_STILL_IN_PROCESSING = -57;

    /**
     * 内存不足
     */
    public static final int IOTC_ER_NOT_ENOUGH_MEMORY = -58;

    /**
     * 该设备被禁止并锁定
     */
    public static final int IOTC_ER_DEVICE_IS_BANNED = -59;

    /**
     * IOTC主服务器没有响应，可能是由许多类型的Internet连接问题引起的。
     */
    public static final int IOTC_ER_MASTER_NOT_RESPONSE = -60;

    /**
     * IOTC模块有一些资源分配问题。
     */
    public static final int IOTC_ER_RESOURCE_ERROR = -61;

    /**
     * IOTC写可靠的发送队列已满。
     */
    public static final int IOTC_ER_QUEUE_FULL = -62;

    /**
     * 该功能不受支持。
     */
    public static final int IOTC_ER_NOT_SUPPORT = -63;

    /**
     * 设备处于睡眠模式。
     */
    public static final int IOTC_ER_DEVICE_IS_SLEEP = -64;

    /**
     * 设备不支持TCP模式下的此功能。
     */
    public static final int IOTC_ER_TCP_NOT_SUPPORT = -65;

    /**
     * IOTC_WakeUp_Init未被调用
     */
    public static final int IOTC_ER_WAKEUP_NOT_INITIALIZED = -66;

    /**
     * 所有服务器响应都找不到设备
     */
    public static final int IOTC_ER_DEVICE_OFFLINE = -90;

    /**
     * IOTC主服务器无效
     */
    public static final int IOTC_ER_MASTER_INVALID = -91;

    //IOTCApis接口
    public native static int IOTC_Get_Login_Info(int[] LoginInfo);

    public native static void IOTC_Get_Version(int[] Version);

    public native static void IOTC_Set_Max_Session_Number(int num);

    public native static int IOTC_Initialize(int UDPPort, String P2PHostNamePrimary,
                                             String P2PHostNameSecondary,
                                             String P2PHostNameThird,
                                             String P2PHostNameFourth);

    public native static int IOTC_Initialize2(int UDPPort);

    public native static int IOTC_DeInitialize();

    public native static int IOTC_Device_Login(String UID, String DeviceName, String DevicePWD);

    public native static int IOTC_Listen(int Timeout_ms);

    public native static int IOTC_Listen2(int Timeout_ms, String AesKey, int mode);     // mode-> 0: equal IOTC_Listen(), 1: only accept secure connection, 2: accept non-secure and secure then use IOTC_Session_Check() to know which mode

    public native static int IOTC_Connect_ByUID(String UID);

    public native static int IOTC_Connect_ByUID2(String UID, String AesKey, int mode); // mode-> 0: equal IOTC_Listen(), 1: only accept secure connection, 2: accept non-secure and secure then use IOTC_Session_Check() to know which mode

    public native static void IOTC_Connect_Stop();

    public native static int IOTC_Get_SessionID();

    public native static int IOTC_Connect_ByUID_Parallel(String UID, int SID);

    public native static int IOTC_Connect_Stop_BySID(int SID);

    public native static int IOTC_Session_Check(int SID, St_SInfo s_Info);

    public native static int IOTC_Session_Check_Ex(int SID, St_SInfoEx s_Info);

    public native static int IOTC_Session_Read(int SID, byte[] Buf, int Max_size, int Timeout_ms, int ChID);

    public native static int IOTC_Session_Write(int SID, byte[] Buf, int Size, int ChID);

    public native static void IOTC_Session_Close(int SID);

    public native static int IOTC_Session_Get_Free_Channel(int SID);

    public native static int IOTC_Session_Channel_ON(int SID, int ChID);

    public native static int IOTC_Session_Channel_OFF(int SID, int ChID);

    public native static int IOTC_Get_Nat_Type();

    public native static int IOTC_IsDeviceSecureMode(int SID);

    public native static st_LanSearchInfo[] IOTC_Lan_Search(int[] arrNum, int nWaitTimeMs);

    public native static st_LanSearchInfo2[] IOTC_Lan_Search2(int[] arrNum, int nWaitTimeMs);

    public native static st_LanSearchInfo2[] IOTC_Lan_Search2_Ex(int[] arrNum, int nWaitTimeMs, int nSendIntervalMs);

    public native static int IOTC_Search_Device_Start(int nWaitTimeMs, int nSendIntervalMs);

    public native static int IOTC_Search_Device_Stop();

    public native static st_SearchDeviceInfo[] IOTC_Search_Device_Result(int[] arrNum, int nGetAll);

    public native static void IOTC_Set_Log_Path(String path, int maxSize);

    public native static void IOTC_Listen_Exit();

    public native static void IOTC_Get_Login_Info_ByCallBackFn(Object obj);            //Callback method, pass object itself.

    public native static int IOTC_Session_Check_ByCallBackFn(int SID, Object obj);    //Callback method, pass object itself.

    public native static int IOTC_Set_Partial_Encryption(int SID, int bPartialEncryption);

    public native static int IOTC_Session_Read_Check_Lost(int SID, byte[] Buf, int Max_size, int Timeout_ms, int[] PacketSN, int[] FlagLost, int ChID);

    public native static void IOTC_Set_Device_Name(String DeviceName);

    public native static void IOTC_TCPRelayOnly_TurnOn();

    public native static void IOTC_Setup_LANConnection_Timeout(int Timeout_ms);

    public native static void IOTC_Setup_P2PConnection_Timeout(int Timeout_ms);

    public native static void IOTC_Setup_DetectNetwork_Timeout(int Timeout_ms);

    public native static void IOTC_Setup_Session_Alive_Timeout(int Timeout_sec);

    public native static void IOTC_Setup_ErrorUpload(int bEnable);

    public native static int IOTC_Session_Write_Reliable(int SID, byte[] Buf, int Size, int ChID, int nTimeout);

    public native static int IOTC_Session_Write_Reliable_Abort(int SID, int ChID);

    /* WakeUp API for client to wake device up*/
    public native static int IOTC_WakeUp_WakeDevice(String sUID);

    public native static int IOTC_Set_LanSearchPort(int nPort);

    /* Callback function. When connection mode is changed, callback will be invoked. */
    public native static void IOTC_ConnModeChange_CallBack(Object obj);

    public native static int IOTC_Device_LoginNB(String cszUID, String cszDeviceName, String cszDevicePWD, byte[] userData, Object obj);

    public native static int IOTC_Get_Device_Status(st_DeviceStInfo pDevStInfo);

    public native static int IOTC_Accept(int[] SID);

    public native static int IOTC_Connect_ByUIDNB(String cszUID, byte[] userData, Object obj);

    public native static int IOTC_Connect_ByUID_ParallelNB(String cszUID, int SID, byte[] userData, Object obj);

    public native static int IOTC_Sessioin_Channel_Check_ON_OFF(int nIOTCSessionID, int nIOTCChannelID);

    public native static int IOTC_Set_Connection_Option(st_ConnectOption S_ConnectOption);

    public native static int IOTC_ReInitSocket(int UDPPort);

    /*set Callback function. onLineResultCB */
    public native static int IOTC_Check_Device_On_Line(String UID, int timeOut, byte[] userData, Object obj);

    public native static int IOTC_Add_MasterServer(String cszMasterHostName1, String cszMasterHostName2,
                                                   String cszMasterHostName3, String cszMasterHostName4);

    public void onLineResultCB(int result, byte[] userData) {
        System.out.println("[parent] onLineResult Callback, result=" + result);
    }

    ;

    public void loginInfoCB(int nLonInfo) {
        //This is callback method, we can modify it.
        System.out.println("[parent] LoginInfo Callback, nLogInfo=" + nLonInfo);
    }

    ;

    public void sessionStatusCB(int nSID, int nErrCode) {
        //This is callback method, we can modify it.
        System.out.println("[parent] SessionStatus Callback, nSID=" + nSID + ", nErrCode=" + nErrCode);
    }

    ;

    public void ConnectModeChangeCB(int nSID, int nConnMode) {
        System.out.println("[parent] ConnectModeChangeCB Callback, nSID = " + nSID + ", nConnMode = " + nConnMode);
    }

    ;

    static {
        try {
            System.loadLibrary("IOTCAPIs");
        } catch (UnsatisfiedLinkError ule) {
            System.out.println("loadLibrary(IOTCAPIsT)," + ule.getMessage());
        }
    }
}
