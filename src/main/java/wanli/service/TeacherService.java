package wanli.service;

import com.github.pagehelper.PageInfo;
import wanli.pojo.StuMatch;
import wanli.pojo.WanliMatch;
import wanli.vo.ServerResponse;

import java.util.List;

public interface TeacherService {
	ServerResponse addMatcheInformation(WanliMatch wanliMatch);

	boolean matchExist(String matchName, String place, Integer matchType);

	WanliMatch selectMatch(String matchName, String place, Integer matchType);

	boolean deleteMatch(Integer id);

	boolean updateMatchInformation(WanliMatch wanliMatch);

	void insertTeacherMatch(String teacherId, Integer matchId);

	ServerResponse<PageInfo> getGroupList(Integer matchId, Integer pageNum, Integer pageSize);

	boolean deleteGroupAllInformation(Integer groupId);

	ServerResponse<List<StuMatch>> selectMemberByGroupId(Integer groupId);

	ServerResponse deleteGroupMember(Integer id);

	ServerResponse updateGroupMember(Integer id, String StuId, String StuName);

}
