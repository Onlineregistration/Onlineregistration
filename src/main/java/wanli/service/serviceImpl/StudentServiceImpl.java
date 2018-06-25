package wanli.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wanli.dao.GroupDao;
import wanli.dao.MatchDao;
import wanli.dao.StuMatchDao;
import wanli.dao.StudentDao;
import wanli.pojo.Group;
import wanli.pojo.MyMatch;
import wanli.pojo.StuMatch;
import wanli.pojo.WanliMatch;
import wanli.service.StudentService;
import wanli.vo.ServerResponse;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StuMatchDao stuMatchDao;

	@Autowired
	GroupDao groupDao;

	@Autowired
	MatchDao matchDao;

	@Autowired
	StudentDao studentDao;

	/**
	 * 添加参加人员与比赛的关系信息
	 *
	 * @param stuId
	 * @param matchId
	 * @param groupId
	 * @param groupPosition
	 * @return
	 */
	public boolean addStuMatch(String stuId, String stuName, Integer matchId, Integer groupId, Integer groupPosition) {
		StuMatch stuMatch = null;
		stuMatch = stuMatchDao.selectStuMatchByStuIdAndMatchId(stuId, matchId);
		if (stuMatch != null) {
			WanliMatch wanliMatch = matchDao.selectMatchById(stuMatch.getMatchId());
			if (wanliMatch.getMatchType() == 1) {
				return false;
			}
		}
		stuMatchDao.insertStuMatch(stuId, stuName, matchId, groupId, groupPosition);
		return true;
	}

	/**
	 * 添加组信息
	 *
	 * @param groupName
	 * @return
	 */
	public boolean addGroupInformation(String groupName, Integer matchId) {
		Group group = null;
		group = groupDao.selectGroupByGroupName(groupName, matchId);
		if (group != null) {
			return false;
		}
		groupDao.insertGroup(groupName, matchId);
		return true;
	}

	/**
	 * 通过name查找小组是否存在
	 *
	 * @param groupName
	 * @return
	 */
	public Group selectGroupByName(String groupName, Integer matchId) {

		return groupDao.selectGroupByGroupName(groupName, matchId);
	}

	/**
	 * 获取用户的所有参赛比赛
	 *
	 * @param stuId
	 * @return
	 */
	public ServerResponse<List<MyMatch>> listMyMatchByStuid(String stuId) {
		return ServerResponse.createBySuccess(studentDao.selectMatch(stuId));
	}

	/**
	 * 取消比赛
	 *
	 * @param groupId
	 * @return
	 */
	public boolean deleteMyMatch(Integer groupId) {
		stuMatchDao.deleteStuMatchByGroupId(groupId);
		Integer num = groupDao.deleteGroupByGroupId(groupId);
		if (num > 0) {
			return true;
		}
		return false;
	}

}
