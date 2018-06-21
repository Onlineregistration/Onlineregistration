package wanli.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wanli.dao.UserDao;
import wanli.pojo.User;
import wanli.service.UserService;
import wanli.vo.ServerResponse;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/9
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public ServerResponse<User> getUserByUsernameAndPassword(String username, String password) {
		User user = userDao.getUserByUsernameAndPassword(username, password);
		if (user == null) {
			return ServerResponse.createByErrorMessage("账号或密码错误");
		}
		return ServerResponse.createBySuccess(user);
	}

	@Override
	public ServerResponse updatePassword(String username, String newPassword) {
		User tempUser = new User();
		tempUser.setUsername(username);
		tempUser.setPassword(newPassword);
		int result = userDao.updateUser(tempUser);
		if (result == 0) {
			return ServerResponse.createByErrorMessage("更新密码失败");
		}
		return ServerResponse.createBySuccess();
	}

	@Override
	public int userExist(String username) {
		return userDao.getCountByUsername(username);
	}

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}
}
