package wanli.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wanli.dao.UserDao;
import wanli.pojo.User;
import wanli.service.UserService;
import wanli.vo.BasicVo;
import wanli.vo.ServerResponse;

import java.util.List;

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
	public ServerResponse<User> getUserByUsername(String username) {
		User user = userDao.getUserByUsername(username);
		if (user == null) {
			return ServerResponse.createByErrorMessage("查无此人");
		}
		return ServerResponse.createBySuccess(user);
	}

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

	@Override
	public ServerResponse updateUserMsg(User usermsg) {
		usermsg.setPassword(null);
		usermsg.setIdentity(null);
		usermsg.setId(null);
		return userDao.updateUser(usermsg) > 0 ?
				ServerResponse.createBySuccess() : ServerResponse.createByErrorMessage("修改失败");
	}

	@Override
	public ServerResponse<PageInfo> getAllUserByIdentity(int identify, int pageNum, int pageSize) {
		System.out.println(identify);
		PageHelper.startPage(pageNum, pageSize);
		List<BasicVo> userlist = userDao.getUserByIdentity(identify);
		PageInfo page = new PageInfo(userlist);
		return ServerResponse.createBySuccess(page);
	}


}
