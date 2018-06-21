package wanli.service;

import wanli.pojo.User;
import wanli.vo.ServerResponse;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/9
 */
public interface UserService {
	ServerResponse<User> getUserByUsernameAndPassword(String username, String password);

	ServerResponse updatePassword(String username, String newPassword);

	int userExist(String username);

	int addUser(User user);
}
