package wanli.dao;

import org.apache.ibatis.annotations.Param;
import wanli.pojo.User;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/9
 */
public interface UserDao {
	User getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	int updateUser(User tempUser);

	int getCountByUsername(String username);

	int addUser(User user);
}
