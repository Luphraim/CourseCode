package mapper;

import model.AlumniDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlumniMapper {

    List<AlumniDTO> search();

    Integer update(Integer id,String info);

    Integer aggregate();
}
