package xmu.middleware.c3p0.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0数据库工具类
 *
 * @author Jason
 */
public class C3P0Util {

    static ComboPooledDataSource cpds = null;

    static {
        // mysql数据库
        cpds = new ComboPooledDataSource();
    }

    /**
     * 获得数据库连接
     *
     * @return Connection
     */
    public static Connection getConnection() {
        try {
            System.out.println("数据库连接成功");
            return cpds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接失败");
            return null;
        }
    }

    /**
     * 放回连接对象，close方法并不是关闭，而是更改该连接对象的状态为可用。
     *
     * @param conn connection
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}