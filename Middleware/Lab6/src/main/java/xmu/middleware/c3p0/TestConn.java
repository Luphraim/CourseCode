package xmu.middleware.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import xmu.middleware.c3p0.entity.Ad;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 连接测试
 *
 * @author Jason
 */
public class TestConn {

    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public static void main(String[] args) {

//        dataSource.setUser("test");
//        dataSource.setPassword("test");
//        dataSource.setJdbcUrl("jdbc:mysql://47.100.32.48:3306/litemall");
//        try {
//            dataSource.setDriverClass("com.mysql.jdbc.Driver");
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//            System.out.println("setDriverClass 失败！");
//            return;
//        }
//        dataSource.setInitialPoolSize(5);
//        dataSource.setMinPoolSize(1);
//        dataSource.setMaxPoolSize(10);
//        dataSource.setMaxStatements(50);
//        dataSource.setMaxIdleTime(60);
//        System.out.println(dataSource);

        try {
            Connection connection = dataSource.getConnection();
            System.out.println("连接数据库成功！");

            // 执行查询语句
            String sql = "select * from litemall_ad limit 10";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(4));
            }
            connection.close();
            System.out.println("数据库连接已关闭！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败！");
        }

    }
}
