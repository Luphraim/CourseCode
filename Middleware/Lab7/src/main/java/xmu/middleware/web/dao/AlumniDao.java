package xmu.middleware.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import xmu.middleware.web.entities.Alumni;
import xmu.middleware.web.mapper.AlumniMapper;

import java.util.List;
import java.util.Map;

/**
 * @author Jason
 */
public class AlumniDao {

    @Autowired
    private AlumniMapper alumniMapper;

    public Integer addAlumni(Alumni alumni) {
        return alumniMapper.addAlumni(alumni);
    }

    public Alumni searchById(Integer id){
        return alumniMapper.searchById(id);
    }

    public List<Alumni> searchByGrade(Map<String,Integer> data){
        return alumniMapper.searchByGrade(data);
    }

    public Integer alterAlumni(Alumni alumni) {
        return alumniMapper.alterAlumni(alumni);
    }

    public Integer delete(Integer id) {
        return alumniMapper.delete(id);
    }

}
