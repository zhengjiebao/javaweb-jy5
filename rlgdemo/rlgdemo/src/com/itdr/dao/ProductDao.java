package com.itdr.dao;

import com.itdr.pojo.Products;
import com.itdr.pojo.Users;
import com.itdr.utils.PoolUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {

    public List<Products> selectAll(String pageSize,String pageNum){
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from products";
        List<Products> li = null;
        try {
            li = qr.query(sql,new BeanListHandler<Products>(Products.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }

    public Products selectOne(Integer pid) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from products where id = ?";
        Products p = null;
        try {
            p = qr.query(sql,new BeanHandler<Products>(Products.class),pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
}
