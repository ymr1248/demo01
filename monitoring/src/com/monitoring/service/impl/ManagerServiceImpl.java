package com.monitoring.service.impl;

import com.monitoring.dao.ManagerDao;
import com.monitoring.entity.Manager;
import com.monitoring.service.ManagerService;
import com.monitoring.util.CyptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;


@Service
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    ManagerDao managerDao;

    @Override
    public Manager loginManager(String account) {
        return managerDao.loginManager(account);
    }

    @Override
    public Manager getManagerByAccount(String account) {
        return managerDao.getManagerByAccount(account);
    }

    @Override
    public int managerLogin(String account, String password,String path) {
        Manager manager = managerDao.loginManager(account);
        if (manager != null) {
            //账号正确
            if (manager.getPassword().equals(password)) {
                return 1;
            }else {
                return 0;
            }
        }else {
            //账号错误
            return -1;
        }
    }

    @Override
    public Manager getManagerById(int managerId) {
        return managerDao.getManagerById(managerId);
    }

    @Override
    public HashMap<String, String> getManagerInfo(int managerId) {
        Manager manager = getManagerById(managerId);
        HashMap<String,String> managerInfo = new HashMap<String, String>();
        managerInfo.put("managerId",String.valueOf(manager.getId()));
        managerInfo.put("managerName",manager.getManagerName());
        managerInfo.put("managerAccount",manager.getManagerAccount());

        return managerInfo;
    }

    @Override
    public ModelMap getManagerInfoByRequest(HttpServletRequest request, ModelMap modelMap) {
        int managerId = 0;
        try {
            managerId = Integer.parseInt(CyptoUtils.decode(request.getParameter("managerId")));
            HashMap<String,String> managerInfo = getManagerInfo(managerId);
            modelMap.addAttribute("managerInfo",managerInfo);
        } catch (NumberFormatException e) {
            modelMap.addAttribute("reason", "对不起，您的跳转链接有错，请检查！");
        }
        return modelMap;
    }
}
