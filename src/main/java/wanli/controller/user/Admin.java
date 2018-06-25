package wanli.controller.user;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import wanli.enu.IdentifyCode;
import wanli.pojo.User;
import wanli.service.MatchService;
import wanli.service.UserService;
import wanli.vo.ServerResponse;

import javax.servlet.http.HttpSession;

import static wanli.Util.IdentityJudge.identification;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/22
 */
@RestController
@RequestMapping("/admin")
public class Admin {

	@Autowired
	MatchService matchService;

	@Autowired
	UserService userService;

	private static int ADMIN_CODE = IdentifyCode.ADMIN.getCode();


	/*
	 * 加入比赛类型
	 * @author zxh
	 * @date 2018/6/22
	 * @param [typename, session]
	 * @return wanli.vo.ServerResponse
	 */
	@PostMapping("/add_matchtype")
	public ServerResponse addType(String typename, HttpSession session) {
		typename = typename.trim();
		String result = identification(session, ADMIN_CODE);
		if (result != null) {
			return ServerResponse.createByErrorMessage(result);
		}

		if (StringUtils.isEmpty(typename)) {
			return ServerResponse.createByErrorMessage("参数错误");
		}
		if (matchService.selectMatchTypeByName(typename)) {
			return ServerResponse.createByErrorMessage("类型已存在");
		}

		if (matchService.addMatchType(typename)) {
			return ServerResponse.createBySuccess();
		}
		return ServerResponse.createByErrorMessage("添加失败");
	}

	/*
	 * 删除比赛类型
	 * @author zxh
	 * @date 2018/6/22
	 * @param [session, id]
	 * @return wanli.vo.ServerResponse
	 */
	@RequestMapping("/del_matchtype/{id}")
	public ServerResponse delMatchType(HttpSession session,
									   @PathVariable("id") Integer id) {

		String result = identification(session, ADMIN_CODE);
		if (result != null) {
			return ServerResponse.createByErrorMessage(result);
		}
		if (id == null) {
			return ServerResponse.createByErrorMessage("参数错误");
		}

		if (matchService.delMatchTypeById(id)) {
			return ServerResponse.createBySuccess();
		}
		return ServerResponse.createBySuccessMessage("删除失败");

	}


	/*
	 * 获取 学生/老师 列表
	 * @author zxh
	 * @date 2018/6/22
	 * @param []
	 * @return wanli.vo.ServerResponse<wanli.vo.BasicVo>
	 */
	@RequestMapping("/list/{identity}")
	public ServerResponse<PageInfo> getStudentList(@PathVariable("identity") String identity, HttpSession session,
												   @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
												   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

		String result = identification(session, ADMIN_CODE);
		if (result != null) {
			return ServerResponse.createByErrorMessage(result);
		}

		return userService.getAllUserByIdentity(IdentifyCode.valueOf(identity.toUpperCase()).getCode(), pageNum, pageSize);
	}

	/*
	 * 获取用户信息
	 * @author zxh
	 * @date 2018/6/22
	 * @param [username, session]
	 * @return wanli.vo.ServerResponse<wanli.vo.UserVo>
	 */
	@RequestMapping("/get_msg/{username}")
	public ServerResponse<User> getUserMsg(@PathVariable("username") String username, HttpSession session) {
		String result = identification(session, ADMIN_CODE);
		if (result != null) {
			return ServerResponse.createByErrorMessage(result);
		}
		return userService.getUserByUsername(username);
	}

}
