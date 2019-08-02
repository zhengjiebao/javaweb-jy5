package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.dao.UserDao;
import com.itdr.pojo.Users;

import java.util.List;

public class UserService {
    private UserDao ud = new UserDao();

    public ResponseCode selectAll(String pageSize, String pageNum){
        if (pageSize == null || pageSize.equals("")){
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")){
            pageNum = "1";
        }

        List<Users> li = ud.selectAll(pageSize,pageNum);

        ResponseCode rc = new ResponseCode();
        rc.setStatus(0);
        rc.setData(li);

        return rc;
    }

    //用户登录
    public ResponseCode selectOne(String username, String password) {
        ResponseCode rc = new ResponseCode();
        if (username == null || username.equals("") || password == null || password.equals("")){
            rc.setStatus(1);
            rc.setMag("账号或密码错误");
            return rc;
        }

        //查找是否有这样一个用户
        Users u = ud.selectOne(username,password);

        //如果用户不存在
        if (u == null){
            rc.setStatus(1);
            rc.setMag("账号或密码错误");
            return rc;
        }

        //用户权限
        if (u.getType() != 1){
            rc.setStatus(2);
            rc.setMag("没有操作权限！");
            return rc;
        }

        rc.setStatus(0);
        rc.setData(u);

        return rc;
    }

    //用户禁用
    public ResponseCode selectOne(String uids) {
        ResponseCode rc = new ResponseCode();
        if (uids == null || uids.equals("")){
            rc.setStatus(Const.USER_PARAMETER_CODE);
            rc.setMag(Const.USER_PARAMETER_MSG);
            return rc;
        }
        //字符串转数值
        Integer uid = null;
        try {
            uid = Integer.parseInt(uids);
        }catch (Exception e){
            rc.setStatus(105);
            rc.setMag("输入非法参数!");
            return rc;
        }


        //查找是否有这样一个用户
        Users u = ud.selectOne(uid);

        //如果用户不存在
        if (u == null){
            rc.setStatus(Const.USER_NO_CODE);
            rc.setMag(Const.USER_NO_MSG);
            return rc;
        }

        //用户禁用情况
        if (u.getStats() == 1){
            rc.setStatus(Const.USER_DISABLE_CODE);
            rc.setMag(Const.USER_DISABLE_MSG);
            return rc;
        }

        //禁用用户
        int row = ud.updateByUid(uid);

        if (row <= 0){
            rc.setStatus(106);
            rc.setMag("用户禁用失败!");
            return rc;
        }

        rc.setStatus(0);
        rc.setData(row);
        return rc;
    }

}
