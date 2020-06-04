package xmu.middleware.alumni.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.middleware.alumni.domain.Alumni;
import xmu.middleware.alumni.domain.CountInfo;
import xmu.middleware.alumni.mapper.AlumniMapper;

import java.util.List;
import java.util.Map;

/**
 * @author Jason
 */
@Repository
public class AlumniDao {


    @Autowired
    private AlumniMapper alumniMapper;

    public List<CountInfo> countByGrade() {
        return alumniMapper.countByGrade();
    }

    public List<CountInfo> countByDiscipline(){
        return alumniMapper.countByDiscipline();
    }

    public Integer addAlumni(Alumni alumni) {
        return alumniMapper.addAlumni(alumni);
    }

    public Alumni searchById(Integer id) {
        return alumniMapper.searchById(id);
    }

    public List<Alumni> searchBy(Map<String, String> data) {
        return alumniMapper.searchBy(data);
    }

    public Integer alterAlumni(Alumni alumni) {
        return alumniMapper.alterAlumni(alumni);
    }

    public Integer delete(Integer id) {
        return alumniMapper.delete(id);
    }

}
