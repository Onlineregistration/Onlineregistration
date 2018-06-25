package wanli.service;

import com.github.pagehelper.PageInfo;
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

	ServerResponse updateUserMsg(User usermsg);

	ServerResponse<PageInfo> getAllUserByIdentity(int identify, int pageNum, int pageSize);

	ServerResponse<User> getUserByUsername(String username);
}
