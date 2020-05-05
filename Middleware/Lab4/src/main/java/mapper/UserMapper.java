package mapper;

import model.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 根据username和password进行登录
     * @param username username
     * @param password password
     * @return userDTO
     */
    UserDTO getUser(String username, String password);

}
