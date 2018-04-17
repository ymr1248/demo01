package com.monitoring.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.monitoring.service.ManagerService;
import com.monitoring.util.MyTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.monitoring.entity.User;
import com.monitoring.service.UserGradeService;
import com.monitoring.service.UserService;

import com.monitoring.entity.UserGrade;

@SessionAttributes(value = "user")
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserGradeService userGradeService;
    @Autowired
    ManagerService managerService;

    /**
     * 用户列表
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "userList")
    public String userList(HttpServletRequest request, ModelMap modelMap) {

        if (MyTestUtils.isLogined(request) != null) {
            return "page_403";
        } else {
            int pageNum = Integer.parseInt(request.getParameter("pageNum"));
            List<User> list = userService.getUsers(pageNum);

            modelMap.addAttribute("list", list);
            modelMap.addAttribute("pageNums", userService.getPageNum());
            modelMap.addAttribute("pageNum", pageNum);
            return "user_list";
        }


    }

    @RequestMapping(value = "/toAddUser")
    public String toAddUser(HttpServletRequest request, ModelMap modelMap) {

        if (MyTestUtils.isLogined(request) != null) {
            return "page_403";
        } else {
            //获取用户等级
            List<HashMap<String, String>> myList = new ArrayList<HashMap<String, String>>();
            List<UserGrade> userGradeList = userGradeService.getUserGrades();
            modelMap.addAttribute("gradeList", userGradeList);
            return "user_add";
        }

    }

    @RequestMapping(value = "addUser")
    public String addUser(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap modelMap) {
        if (MyTestUtils.isLogined(request) != null) {
            return "page_403";
        } else {
            String fileName = "";
            if (file != null) {
                fileName = file.getOriginalFilename();// 获取上传的文件名字
                String filePath = request.getSession().getServletContext().getRealPath("/") + "resources/img/usericon/"
                        + file.getOriginalFilename();
                System.out.println("filePath==" + filePath + " " + fileName);
                File targetFile = new File(filePath, fileName); // 新建文件
                if (!targetFile.exists()) { // 判断文件的路径是否存在
                    targetFile.mkdirs(); // 如果文件不存在 在目录中创建文件夹 这里要注意mkdir()和mkdirs()的区别
                }
                // 保存
                try {
                    file.transferTo(targetFile); // 传送 失败就抛异常
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 执行更新图片在服务器的地址
            }
            String avatar = fileName;
            String userName = request.getParameter("inputName");
            String userAccount = request.getParameter("inputAccount");
            String password = request.getParameter("inputPassword");
            String userAddress = request.getParameter("inputAddress");
            int userGradeId = Integer.parseInt(request.getParameter("inputUserGradeId"));
            System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQ" + userGradeId + "QQQQQQQQQQQQQQQQQQ");
            String userPhone = request.getParameter("inputPhone");
            String userEmail = request.getParameter("inputEmail");

            int userIsAdded = userService.isOrNotAdded(avatar, userName, userAccount, password, userAddress, userPhone, userEmail, userGradeId);
            if (userIsAdded == 1) {
                List<HashMap<String, String>> myList = new ArrayList<HashMap<String, String>>();
                //获取用户类型
                List<UserGrade> userGradeList = userGradeService.getUserGrades();

                modelMap.addAttribute("gradeList", userGradeList);
                return "index";
            } else if (userIsAdded == -1) {
                modelMap.put("reason", "账号已存在请重新输入！");
                return "page_400";
            } else if (userIsAdded == 0) {
                modelMap.put("reason", "手机号已被使用，请重新输入！");
                return "page_400";
            } else {
                modelMap.put("reason", "未知错误，请重新输入！");
                return "page_400";
            }
        }

    }

    /**
     * 删除用户
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "userDel")
    public String userDel(HttpServletRequest request, ModelMap modelMap) {
        if (MyTestUtils.isLogined(request) != null) {
            return "page_403";
        } else {
            String id = request.getParameter("id");
            int pageNum = Integer.parseInt(request.getParameter("pageNum"));
            System.out.println("iddddddddddddd" + id + "page" + pageNum);
            userService.deleteUserById(Integer.parseInt(id));
            List<User> list = userService.getUsers(pageNum);
            List<UserGrade> userGradeList = userGradeService.getUserGrades();
            System.out.println("user分页list=" + list.toString());
            modelMap.addAttribute("list", list);
            modelMap.addAttribute("gradeList", userGradeList);
            modelMap.addAttribute("pageNums", userService.getPageNum());
            modelMap.addAttribute("pageNum", pageNum);
            return "user_list";
        }
    }

    /**
     * 转到更新用户信息界面
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "toUpdateUser")
    public String toUpdateUser(HttpServletRequest request, ModelMap modelMap) {
        if (MyTestUtils.isLogined(request) != null) {
            return "page_403";
        } else {
            String id = request.getParameter("id");
            List<UserGrade> userGradeList = userGradeService.getUserGrades();
            int pageNum = Integer.parseInt(request.getParameter("pageNum"));

            HashMap<String, String> map = new HashMap<String, String>();
            if (id != null) {
                User user = userService.getUserById(Integer.parseInt(id));
                map.put("id", String.valueOf(user.getId()));
                map.put("userName", user.getUserName());
                map.put("userAddress", user.getUserAddress());
                map.put("userEMail", user.getUserEMail());
                map.put("avatar", user.getAvatar());
                map.put("userGradeId", String.valueOf(user.getUserGrade().getId()));
                map.put("userGrade", String.valueOf(user.getUserGrade().getUserGrade()));
                map.put("userGradeName", user.getUserGrade().getUserGradeName());

            } else {
                map.put("id", "0");
                map.put("userAddress", "地址");
                map.put("userName", "姓名");
                map.put("userEMail", "邮箱");
                map.put("avatar", "头像");
                map.put("userGradeId", "等级ID");
                map.put("userGrade", "");
                map.put("userGradeName", "等级名称");
            }

            modelMap.addAttribute("map1", map);
            modelMap.addAttribute("gradeList", userGradeList);
            modelMap.addAttribute("pageNum", pageNum);
            return "user_update";
        }
    }

    /**
     * 更新用户数据
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/userUpdate")
    public String updateUser(@RequestParam(value = "file", required = false) MultipartFile file,
                             HttpServletRequest request, ModelMap modelMap) {
        if (MyTestUtils.isLogined(request) != null) {
            return "page_403";
        } else {
            User user = new User();
            String fileName = "";
            if (file != null) {
                String picture = file.getOriginalFilename();
                String filePath = request.getSession().getServletContext().getRealPath("/") + "resources/img/avatar/";
                //					+ UUID.randomUUID() + picture.substring(picture
                //	                        .lastIndexOf("."));
                fileName = UUID.randomUUID() + picture.substring(picture.lastIndexOf("."));
                System.out.println("**********" + filePath);
                System.out.println("**********" + fileName);
                File targetFile = new File(filePath, fileName); // 新建文件
                if (!targetFile.exists()) { // 判断文件的路径是否存在
                    targetFile.mkdirs(); // 如果文件不存在 在目录中创建文件夹 这里要注意mkdir()和mkdirs()的区别
                }
                // 保存
                try {
                    file.transferTo(targetFile); // 传送 失败就抛异常
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 执行更新图片在服务器的地址
            }
            System.out.println("头像图片名字==" + fileName);
            String id = request.getParameter("id");
            String userName = request.getParameter("userName");
            String userEMail = request.getParameter("userEMail");
            String userAddress = request.getParameter("userAddress");
            String userGradeId = request.getParameter("userGradeId");
            // String userPhone = request.getParameter("userPhone");

            user.setUserAddress(userAddress);
            user.setUserName(userName);
            user.setAvatar(fileName);
            user.setUserEMail(userEMail);
            // user.setUserPhone(userPhone);
            user.setId(Integer.parseInt(id));
            user.setUserGrade(userGradeService.getUserGradeById(Integer.parseInt(userGradeId)));
            System.out.println(user.toString());
            int pageNum = Integer.parseInt(request.getParameter("pageNum"));
            if (userService.update(user)) {
                //成功
                List<User> list = userService.getUsers(pageNum);

                System.out.println("user分页list=" + list.toString());
                modelMap.addAttribute("list", list);
                modelMap.addAttribute("pageNums", userService.getPageNum());
                modelMap.addAttribute("pageNum", pageNum);
                return "user_list";
            }
            return "update_error";
        }
    }

    /**
     * 重置用户密码
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/updateUserPSW")
    public String updateUserPSW(HttpServletRequest request, ModelMap modelMap) {
        if (MyTestUtils.isLogined(request) != null) {
            return "page_403";
        } else {
            String id = request.getParameter("id");
            int pageNum = Integer.parseInt(request.getParameter("pageNum"));

            if (userService.updateUserPsw(Integer.parseInt(id), "123456")) {
                //密码修改成功
                List<User> list = userService.getUsers(pageNum);


                System.out.println("user分页list=" + list.toString());
                modelMap.addAttribute("list", list);
                modelMap.addAttribute("pageNums", userService.getPageNum());
                modelMap.addAttribute("pageNum", pageNum);
                return "user_list";

            }
            return "update_error";
        }
    }


    @RequestMapping(value = "/userLogin")
    public String userLogin(HttpServletRequest request,ModelMap modelMap){
        String path = request.getContextPath();
        String account = request.getParameter("userAccount");
        String password = request.getParameter("password");
        int isLogin = userService.loginUser(account,password);
        if(isLogin == 1){
            User user = userService.getUserByAccount(account);
            request.getSession().setAttribute("USER", user);
            return "userviews/user_index";
        }else if(isLogin == 0){
            //密码错误
            modelMap.put("reason", "密码错误，请检查您的密码是否正确？");
            return "page_400";
        }else{
            //账号错误
            modelMap.put("reason", "账号错误，请检查您的账号是否正确？");
            return "page_400";
        }

    }


}
