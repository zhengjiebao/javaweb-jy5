package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.pojo.Products;
import com.itdr.service.ProductService;
import com.itdr.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/manage/products/*")
public class ProductsController extends HttpServlet {
    private ProductService ps = new ProductService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
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
        }
    }
    private ResponseCode listDo(HttpServletRequest request){
        ResponseCode rc = new ResponseCode();
        HttpSession session = request.getSession();
        Products products = (Products) session.getAttribute("products");
        if (products == null){
            rc.setStatus(3);
            rc.setMag("请登录后操作！");
            return rc;
        }

        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        rc = ps.selectAll(pageSize,pageNum);
        return rc;
    }
}
