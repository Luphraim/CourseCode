package xmu.middleware.c3p0.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xmu.middleware.c3p0.C3p0Application;
import xmu.middleware.c3p0.entity.Ad;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest(classes = C3p0Application.class)
class AdDaoTest {

    @Autowired
    private AdDao adDao;

    private Ad ad;

    @BeforeEach
    void setUp() {
        this.ad = new Ad();
        this.ad.setId(100);
        this.ad.setName("test");
        this.ad.setLink("test");
        this.ad.setUrl("http://xmu.middleware.cn/test");
        this.ad.setPosition(true);
        this.ad.setContent("This is test info");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        this.ad.setStartTime(localDateTime);
        this.ad.setEndTime(localDateTime);
        this.ad.setEnabled(false);
        this.ad.setAddTime(localDateTime);
        this.ad.setUpdateTime(localDateTime);
        this.ad.setDeleted(false);
    }

    @Test
    void select() {
        List<Ad> adList = adDao.select();
        System.out.println(adList);
    }

    @Test
    void insert() {
        System.out.println(adDao.insert(ad));
    }

    @Test
    void update() {
        ad.setUrl("https://xmu.middleware.com/update");
        System.out.println(adDao.update(ad));
    }

    @Test
    void delete() {
        System.out.println(adDao.delete(100));
    }
}