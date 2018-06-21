package wanli.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wanli.common.Const;
import wanli.pojo.User;
import wanli.service.UserService;
import wanli.vo.ServerResponse;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

/**
 * @Description: 用户
 * @Author: zxh
 * @Date: Created in 2018/6/9
 */
@Controller
@RequestMapping("/user")
public class userController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login")
	@ResponseBody
	public ServerResponse<User> login(String username, String password, HttpSession session) {
		if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
			return ServerResponse.createByErrorMessage("账号密不能为空");
		}
		ServerResponse<User> response = userService.getUserByUsernameAndPassword(username, password);
		if (response.isSuccess()) {
			session.setAttribute(Const.CURRENT_USER, response.getData());
		} else {
			return ServerResponse.createByErrorMessage("账号或密码错误");
		}
		return response;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse register(String username, String password, String repeat, String name, String inviteCode) {

		if (username == null || username.trim().isEmpty()) {
			return ServerResponse.createByErrorMessage("用户名不合法");
		}

		if (userService.userExist(username) == 1) {
			return ServerResponse.createByErrorMessage("用户名已存在");
		}

		if (password == null || repeat == null || password.trim().isEmpty() || repeat.trim().isEmpty()) {
			return ServerResponse.createByErrorMessage("密码不合法");
		}

		if (!password.trim().equals(repeat.trim())) {
			return ServerResponse.createByErrorMessage("密码不一致");
		}

		if (name == null || name.trim().isEmpty()) {
			return ServerResponse.createByErrorMessage("名字不合法");
		}

		if (!"nb_wlxy".equals(inviteCode.trim())) {
			return ServerResponse.createByErrorMessage("邀请码错误");
		}

		User tempUser = new User();
		tempUser.setUsername(username);
		tempUser.setPassword(password);
		tempUser.setName(name);
		int result = userService.addUser(tempUser);
		if (result != 1) {
			return ServerResponse.createByError();
		}
		return ServerResponse.createBySuccess();
	}

	@RequestMapping("/myMsg")
	@ResponseBody
	public ServerResponse<User> myMsg(HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		System.out.println(user);
		return ServerResponse.createBySuccess(user);
	}

	@RequestMapping("/resetPassword")
	@ResponseBody
	public ServerResponse resetPassword(String oldPassword, String newPassword, String repeat, HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		ServerResponse<User> response = userService.getUserByUsernameAndPassword(user.getUsername(), oldPassword);
		if (!response.isSuccess()) {
			return ServerResponse.createByErrorMessage("原始密码错误");
		}
		if (newPassword == null || repeat == null || !newPassword.trim().equals(repeat.trim())) {
			return ServerResponse.createByErrorMessage("新密码错误");
		}
		return userService.updatePassword(user.getUsername(), newPassword);
	}

	@RequestMapping("/quit")
	@ResponseBody
	public ServerResponse quit(HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		session.removeAttribute(Const.CURRENT_USER);
		return ServerResponse.createBySuccess();
	}
}
