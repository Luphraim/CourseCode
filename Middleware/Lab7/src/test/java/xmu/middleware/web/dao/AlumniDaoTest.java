//package xmu.middleware.web.dao;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import xmu.middleware.web.WebApplication;
//import xmu.middleware.web.entities.Alumni;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(classes = WebApplication.class)
//class AlumniDaoTest {
//
//    @Autowired
//    private AlumniDao alumniDao;
//
//    @Test
//    void addAlumni() {
//        Alumni alumni = new Alumni(100, "test", 1, 2017, "文学院", "中文系", "122222222", "test@test.com");
//        System.out.println(alumni);
//        System.out.println(alumniDao.addAlumni(alumni));
//        System.out.println(alumniDao.searchById(100));
//    }
//
//    @Test
//    void searchById() {
//        System.out.println(alumniDao.searchById(20200529));
//    }
//
//    @Test
//    void searchBy() {
//        Map<String, String> data = new HashMap<>(4);
//        data.put("name", null);
//        data.put("text", null);
//        System.out.println(alumniDao.searchBy(data));
//
//    }
//
//    @Test
//    void alterAlumni() {
//    }
//
//    @Test
//    void delete() {
//        System.out.println(alumniDao.delete(100));
//    }
//}