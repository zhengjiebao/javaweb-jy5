package com.itdr.service;

import com.itdr.common.ResponseCode;
import com.itdr.dao.ProductDao;
import com.itdr.pojo.Products;

import java.util.List;

public class ProductService {
    private ProductDao pd = new ProductDao();

    public ResponseCode selectAll(String pageSize, String pageNum){
        if (pageSize == null || pageSize.equals("")){
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")){
            pageNum = "1";
        }

        List<Products> li = pd.selectAll(pageSize,pageNum);

        ResponseCode rc = new ResponseCode();
        rc.setStatus(0);
        rc.setData(li);

        return rc;
    }
}
