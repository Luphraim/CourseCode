package xmu.middleware.alumni.mapper;

import org.apache.ibatis.annotations.Mapper;
import xmu.middleware.alumni.domain.Alumni;
import xmu.middleware.alumni.domain.CountInfo;

import java.util.List;
import java.util.Map;

/**
 * @author Jason
 */
@Mapper
public interface AlumniMapper {

    /**
     * 按年级和性别统计
     * @return countInfo List
     */
    public List<CountInfo> countByGrade();

    /**
     * 按专业和性别统计
     * @return countInfo List
     */
    public List<CountInfo> countByDiscipline();

    /**
     * Insert
     *
     * @param alumni alumni
     * @return int
     */
    public Integer addAlumni(Alumni alumni);

    /**
     * search by id
     *
     * @param id id
     * @return alumni
     */
    public Alumni searchById(Integer id);


    /**
     * search by grade
     *
     * @param data search data
     * @return alumni list
     */
    public List<Alumni> searchBy(Map<String, String> data);

    /**
     * update one's info
     *
     * @param alumni alumni
     * @return int
     */
    public Integer alterAlumni(Alumni alumni);

    /**
     * delete an alumni
     *
     * @param id id
     * @return int
     */
    public Integer delete(Integer id);

}
