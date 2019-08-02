package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.pojo.Users;
import com.itdr.service.UserService;
import com.itdr.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage/user/*")
public class UsersController extends HttpServlet {
    private UserService uc = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取请求路径信息
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);

        ResponseCode rc = null;

        //判断是什么样的请求
        switch (path){
            case "list":
                rc = listDo(request);
                break;
            case "login":
                rc = loginDo(request);
                break;
            case "disableuser":
                rc = disableuserDo(request);
                break;
        }

        //返回响应数据
        response.getWriter().write(rc.toString());
    }
    //获取所有用户列表的请求
    private ResponseCode listDo(HttpServletRequest request){
        ResponseCode rc = new ResponseCode();

        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user == null){
            rc.setStatus(3);
            rc.setMag("请登录后操作！");
            return rc;
        }
        if (user.getType() != 1){
            rc.setStatus(3);
            rc.setMag("没有操作权限！");
            return rc;
        }

        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        rc = uc.selectAll(pageSize, pageNum);

        //调用业务层处理业务
        return rc;
    }

    //用户登录请求
    private ResponseCode loginDo(HttpServletRequest request){
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ResponseCode rc = uc.selectOne(username, password);

        //获取session对象
        HttpSession session = request.getSession();
        session.setAttribute("user",rc.getData());

        //调用业务层处理业务
        return rc;
    }

    private ResponseCode disableuserDo(HttpServletRequest request){
        //获取参数
        String uid = request.getParameter("uid");

        ResponseCode rc = uc.selectOne(uid);

        //调用业务层处理业务
        return rc;
    }
}
