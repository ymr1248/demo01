<%@ page import="java.util.HashMap" %>
<%@ page import="com.monitoring.util.CyptoUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="main-content">
    <!--left-fixed -navigation-->
    <div class=" sidebar" role="navigation">
        <div class="navbar-collapse">
            <nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left"
                 id="cbp-spmenu-s1">
                <ul class="nav" id="side-menu">
                    <li><a href="userIndex" class="active"><i
                            class="fa fa-home nav_icon"></i>首页</a></li>
                    <li><a href="#"><i class="fa fa-users nav_icon"></i>监控器管理 <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="toMonitorgroup_add">添加机器组</a></li>
                            <li><a href="toMonitorgroup_list?pageNum=1">机器组</a></li>
                        </ul>
                    </li>
                    <li><a href="#"><i class="fa fa-users nav_icon"></i>视频管理 <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="toVideo_add">添加视频</a></li>
                        </ul>
                    </li>
                </ul>
                <!-- //sidebar-collapse -->
            </nav>
        </div>
    </div>
    <!--left-fixed -navigation-->
    <!-- header-starts -->
    <div class="sticky-header header-section ">
        <div class="header-left">
            <!--toggle button start-->
            <button id="showLeftPush">
                <i class="fa fa-bars"></i>
            </button>
            <!--toggle button end-->
            <!--logo -->
            <div class="logo">
                <a>
                    <h1>用户</h1> <span>管理面板</span>
                </a>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="header-right">

            <div class="profile_details">
                <ul>
                    <li class="dropdown profile_details_drop">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                            <div class="profile_img">
									<span class="prfil-img"><img src="images/mm.jpg" alt="">
									</span>
                                <div class="user-name">
                                    <p>用户</p>
                                    <span>类型：用户</span>
                                </div>
                                <i class="fa fa-angle-down lnr"></i> <i
                                    class="fa fa-angle-up lnr"></i>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                        <ul class="dropdown-menu drp-mnu">
                            <li><a href="#"><i class="fa fa-cog"></i> 设置</a></li>
                            <li><a href="#"><i class="fa fa-user"></i> 个人信息</a></li>
                            <li><a href="#"><i class="fa fa-sign-out"></i> 退出</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="clearfix"></div>
    </div>
    <!-- //header-ends -->
    <div class="copyrights">
    </div>
</div>
