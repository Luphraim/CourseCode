package xmu.middleware.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import xmu.middleware.web.entities.Alumni;

import java.util.List;
import java.util.Map;

/**
 * @author Jason
 */
@Mapper
public interface AlumniMapper {

    /**
     * Insert
     * @param alumni alumni
     * @return int
     */
    public Integer addAlumni(Alumni alumni);

    /**
     * search by id
     * @param id id
     * @return alumni
     */
    public Alumni searchById(Integer id);


    /**
     * search by grade
     * @param data search data
     * @return alumni list
     */
    public List<Alumni> searchByGrade(Map<String,Integer> data);

    /**
     * update one's info
     * @param alumni alumni
     * @return int
     */
    public Integer alterAlumni(Alumni alumni);

    /**
     * delete an alumni
     * @param id id
     * @return int
     */
    public Integer delete(Integer id);

}
