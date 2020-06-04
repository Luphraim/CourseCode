package xmu.middleware.alumni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.middleware.alumni.dao.AlumniDao;
import xmu.middleware.alumni.domain.Alumni;
import xmu.middleware.alumni.domain.CountInfo;
import xmu.middleware.alumni.util.ResponseUtil;

import java.util.List;
import java.util.Map;

/**
 * @author jason
 */
@Service
public class AlumniService {

    @Autowired
    private AlumniDao alumniDao;

    public Object countByGrade() {
        List<CountInfo> countInfoList = alumniDao.countByGrade();
        return ResponseUtil.ok(countInfoList);
    }

    public Object countByDiscipline() {
        List<CountInfo> countInfoList = alumniDao.countByDiscipline();
        return ResponseUtil.ok(countInfoList);
    }

    public Object getAlumni(Map<String, String> data) {

        List<Alumni> alumniList = alumniDao.searchBy(data);

        return ResponseUtil.ok(alumniList);
    }

    public Object getAlumniById(Integer id) {
        if (id == null) {
            return ResponseUtil.badArgument();
        }

        try {
            Alumni alumni = alumniDao.searchById(id);
            return ResponseUtil.ok(alumni);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.notfound();
        }
    }

    public Object addAlumni(Alumni alumni) {
        if (null == alumni) {
            return ResponseUtil.badArgument();
        }

        if (!alumni.check()) {
            return ResponseUtil.badArgument();
        }

        Integer lines = alumniDao.addAlumni(alumni);

        if (lines.equals(0)) {
            return ResponseUtil.serious();
        }

        return ResponseUtil.ok(alumni.getId());
    }

    public Object updateAlumni(Alumni alumni) {
        if (null == alumni) {
            return ResponseUtil.badArgument();
        }

        if (!alumni.check()) {
            return ResponseUtil.badArgument();
        }

        Integer lines = alumniDao.alterAlumni(alumni);

        if (lines.equals(0)) {
            return ResponseUtil.serious();
        }

        return ResponseUtil.ok(alumni.getId());
    }

    public Object delete(Integer id) {
        if (null == id) {
            return ResponseUtil.badArgument();
        }

        Alumni alumni = alumniDao.searchById(id);

        if (null == alumni) {
            return ResponseUtil.badArgumentValue();
        }

        Integer lines = alumniDao.delete(id);

        if (lines.equals(0)) {
            return ResponseUtil.badArgumentValue();
        }

        return ResponseUtil.ok();
    }
}
