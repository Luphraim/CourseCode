package xmu.middleware.c3p0.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.middleware.c3p0.entity.Ad;
import xmu.middleware.c3p0.util.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * litemall_ad表的增删查改操作
 *
 * @author Jason
 */
@Repository
public class AdDao {

    public List<Ad> select() {
        try {
            Connection connection = C3P0Util.getConnection();
            String sql = "select * from litemall_ad limit 10";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Ad> adList = new LinkedList<>();
            while (rs.next()) {
                Ad ad = new Ad(rs);
                adList.add(ad);
            }
            connection.close();
            return adList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer insert(Ad ad) {
        try {
            Connection connection = C3P0Util.getConnection();
            Integer position = ad.isPosition() ? 1 : 0;
            Integer enabled = ad.isEnabled() ? 1 : 0;
            Integer deleted = ad.isDeleted() ? 1 : 0;
//            String sql = "insert into litemall_ad values(" + ad.getId() + "," +
//                    "'" + ad.getName() + "','" + ad.getLink() + "'," +
//                    "'" + ad.getUrl() + "'," + position + "," +
//                    "'" + ad.getContent() + "','" + ad.getStartTime() + "'," +
//                    "'" + ad.getEndTime() + "'," + enabled + "," +
//                    "'" + ad.getAddTime() + "','" + ad.getUpdateTime() + "'," +
//                    "" + deleted + ")";
            String sql = "insert into litemall_ad values(?,?,?,?,?,?,?,?,?,?,?,?)";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ad.getId());
            ps.setString(2, ad.getName());
            ps.setString(3, ad.getLink());
            ps.setString(4, ad.getUrl());
            ps.setInt(5, position);
            ps.setString(6, ad.getContent());
            ps.setString(7, ad.getStartTime().toString());
            ps.setString(8, ad.getEndTime().toString());
            ps.setInt(9, enabled);
            ps.setString(10, ad.getAddTime().toString());
            ps.setString(11, ad.getUpdateTime().toString());
            ps.setInt(12, deleted);
            Integer success = ps.executeUpdate();
            connection.close();
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Integer update(Ad ad) {
        try {
            Connection connection = C3P0Util.getConnection();
            String sql = "update litemall_ad set name=?,link=?,url=?,content=? where id = ?";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ad.getName());
            ps.setString(2, ad.getLink());
            ps.setString(3, ad.getUrl());
            ps.setString(4, ad.getContent());
            ps.setInt(5, ad.getId());
            Integer success = ps.executeUpdate();
            connection.close();
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Integer delete(Integer id) {
        try {
            Connection connection = C3P0Util.getConnection();
            String sql = "delete from litemall_ad where id = ?";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            Integer success = ps.executeUpdate();
            connection.close();
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
