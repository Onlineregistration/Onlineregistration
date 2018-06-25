package wanli.dao;

import org.apache.ibatis.annotations.Param;
import wanli.pojo.User;
import wanli.vo.BasicVo;

import java.util.List;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/9
 */
public interface UserDao {

	User getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	int updateUser(User user);

	int getCountByUsername(String username);

	int addUser(User user);

	List<BasicVo> getUserByIdentity(int identify);

	User getUserByUsername(String username);
}
