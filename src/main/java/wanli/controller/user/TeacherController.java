package wanli.controller.user;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wanli.enu.IdentifyCode;
import wanli.pojo.StuMatch;
import wanli.pojo.WanliMatch;
import wanli.service.TeacherService;
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
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;

	private static int TEACHER_CODE = IdentifyCode.TEACHER.getCode();

	/**
	 * 添加比赛信息
	 *
	 * @param wanliMatch
	 * @return
	 */
	@RequestMapping("/addMatch")
	@ResponseBody
	public ServerResponse addMatch(WanliMatch wanliMatch, HttpSession session) {

		String result = identification(session, TEACHER_CODE);
		if (result != null) {
			return ServerResponse.createByErrorMessage(result);
		}

		if (teacherService.matchExist(wanliMatch.getMatchName(), wanliMatch.getPlace(), wanliMatch.getMatchType())) {
			return ServerResponse.createByErrorMessage("比赛已存在");
		}
		ServerResponse serverResponse = teacherService.addMatcheInformation(wanliMatch);
		WanliMatch wanliMatch1 = teacherService.selectMatch(wanliMatch.getMatchName(), wanliMatch.getPlace(), wanliMatch.getMatchType());
		teacherService.insertTeacherMatch(wanliMatch1.getTeacherId(), wanliMatch1.getId());
		return serverResponse;
	}

	/**
	 * 根据id删除比赛
	 *
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/deleteMatch")
	@ResponseBody
	public ServerResponse deleteMatch(Integer id, HttpSession session) {

		String result = identification(session, TEACHER_CODE);
		if (result != null) {
			return ServerResponse.createByErrorMessage(result);
		}

		if (teacherService.deleteMatch(id)) {
			return ServerResponse.createBySuccessMessage("删除成功");
		}
		return ServerResponse.createByErrorMessage("删除失败");
	}

	/**
	 * 更新比赛信息
	 *
	 * @param wanliMatch
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateMatch")
	@ResponseBody
	public ServerResponse updateMatch(WanliMatch wanliMatch, HttpSession session) {

		String result = identification(session, TEACHER_CODE);
		if (result != null) {
			return ServerResponse.createByErrorMessage(result);
		}

		if (teacherService.updateMatchInformation(wanliMatch)) {
			return ServerResponse.createBySuccessMessage("修改成功");
		}
		return ServerResponse.createByErrorMessage("修改失败");
	}

	/**
	 * 查看一个比赛的报名情况
	 *
	 * @param matchId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/ViewGroup")
	@ResponseBody
	public ServerResponse<PageInfo> ViewGroup(@RequestParam("matchId") Integer matchId,
											  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
											  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpSession session) {

		String result = identification(session, TEACHER_CODE);
		if (result != null) {
			return ServerResponse.createByErrorMessage(result);
		}

		if (matchId == null) {
			return ServerResponse.createByError();
		}
		return teacherService.getGroupList(matchId, pageNum, pageSize);
	}

	/**
	 * 查看一组的成员
	 *
	 * @param groupId
	 * @param session
	 * @return
	 */
	@RequestMapping("/ViewMember")
	@ResponseBody
	public ServerResponse<List<StuMatch>> ViewMember(Integer groupId, HttpSession session) {

		String result = identification(session, TEACHER_CODE);
		if (result != null) {
			return ServerResponse.createByErrorMessage(result);
		}

		if (groupId == null) {
			return ServerResponse.createByError();
		}
		return teacherService.selectMemberByGroupId(groupId);
	}

	/**
	 * 删除小组
	 *
	 * @param groupId
	 * @param session
	 * @return
	 */
	@RequestMapping("/deleteGroup")
	@ResponseBody
	public ServerResponse deleteGroup(Integer groupId, HttpSession session) {

		String result = identification(session, TEACHER_CODE);
		if (result != null) {
			return ServerResponse.createByErrorMessage(result);
		}

		if (teacherService.deleteGroupAllInformation(groupId)) {
			return ServerResponse.createBySuccessMessage("删除成功");
		}
		return ServerResponse.createByErrorMessage("删除失败");
	}

	/**
	 * 删除个别成员
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteGroupMember")
	@ResponseBody
	public ServerResponse deleteGroupMember(Integer id, HttpSession session) {
		String result = identification(session, TEACHER_CODE);
		if (result != null) {
			return ServerResponse.createByErrorMessage(result);
		}
		if (id == null) {
			return ServerResponse.createByError();
		}
		return teacherService.deleteGroupMember(id);
	}

	@RequestMapping("/updateGroupMember")
	@ResponseBody
	public ServerResponse updateGroupMember(StuMatch stuMatch, HttpSession session) {
		String result = identification(session, TEACHER_CODE);
		if (result != null) {
			return ServerResponse.createByErrorMessage(result);
		}
		if (stuMatch == null) {
			return ServerResponse.createByError();
		}
		return teacherService.updateGroupMember(stuMatch.getId(), stuMatch.getStuId(), stuMatch.getStuName());
	}

}
