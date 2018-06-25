package wanli.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wanli.enu.IdentifyCode;
import wanli.pojo.Apply;
import wanli.pojo.MyMatch;
import wanli.service.StudentService;
import wanli.vo.ServerResponse;

import javax.servlet.http.HttpSession;
import java.util.List;

import static wanli.Util.IdentityJudge.identification;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/9
 */
@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentService studentService;

	private static int STUDENT_CODE = IdentifyCode.STUDENT.getCode();


	@RequestMapping("/apply")
	@ResponseBody
	public ServerResponse apply(Apply apply, HttpSession session) {

		String result = identification(session, STUDENT_CODE);
		if (result != null) {
			return ServerResponse.createByErrorMessage(result);
		}

		String[] stuid = apply.getStuid();
		String[] name = apply.getName();
		for (int i = 0; i < stuid.length; i++) {
			if (stuid[i] == "" || name[i] == "") {
				return ServerResponse.createByErrorMessage("参赛人员信息不完整");
			}
		}
		String groupname = apply.getGroupname();
		if (!studentService.addGroupInformation(groupname, apply.getMatchid()) && groupname != null) {
			return ServerResponse.createByErrorMessage("队名已经存在");
		}
		//获取组id
		Integer groupId = studentService.selectGroupByName(groupname, apply.getMatchid()).getGroupId();
		//插入学生与比赛的相关信息
		for (int i = 0; i < stuid.length; i++) {
			if (i == 0) {
				studentService.addStuMatch(stuid[i], name[i], apply.getMatchid(), groupId, 1);
			} else {
				studentService.addStuMatch(stuid[i], name[i], apply.getMatchid(), groupId, 2);
			}
		}
		return ServerResponse.createBySuccessMessage("报名成功");
	}

	@RequestMapping("/viewMyMatch")
	@ResponseBody
	public ServerResponse<List<MyMatch>> viewMyMatch(String stuid, HttpSession session) {
		String result = identification(session, STUDENT_CODE);
		if (result != null) {
			return ServerResponse.createByErrorMessage(result);
		}

		if (stuid == null) {
			return ServerResponse.createByError();
		}
		return studentService.listMyMatchByStuid(stuid);
	}


	@RequestMapping("/cancle")
	@ResponseBody
	public ServerResponse cancle(Integer groupid, HttpSession session) {

		String result = identification(session, STUDENT_CODE);
		if (result != null) {
			return ServerResponse.createByErrorMessage(result);
		}

		if (groupid == null) {
			return ServerResponse.createByError();
		}
		if (studentService.deleteMyMatch(groupid)) {
			return ServerResponse.createBySuccessMessage("取消成功");
		}
		return ServerResponse.createByErrorMessage("取消失败");
	}

}
