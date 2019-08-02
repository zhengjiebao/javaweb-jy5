package testdemo;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.security.PermissionCollection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestDemo {
    @Test
    public void test1() throws Exception {
        ComboPooledDataSource cp = new ComboPooledDataSource();
        Connection connection = cp.getConnection();
        String sql = "select * from users";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString(2));
        }
    }

    @Test
    public void test2(){

    }
}
