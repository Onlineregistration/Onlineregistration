package wanli.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wanli.dao.GroupDao;
import wanli.dao.MatchDao;
import wanli.dao.StuMatchDao;
import wanli.dao.TeacherMatchDao;
import wanli.pojo.GroupExt;
import wanli.pojo.StuMatch;
import wanli.pojo.TeacherMatch;
import wanli.pojo.WanliMatch;
import wanli.service.TeacherService;
import wanli.vo.ServerResponse;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    MatchDao matchDao;
    @Autowired
    GroupDao groupDao;
    @Autowired
    TeacherMatchDao teacherMatchDao;
    @Autowired
    StuMatchDao stuMatchDao;

    /**
     * 添加新比赛信息
     * @param wanliMatch
     * @return
     */
    public ServerResponse addMatcheInformation(WanliMatch wanliMatch){
        wanliMatch.setGroupNum(0);
        Integer num= matchDao.insertMatch(wanliMatch);
        if(num==0){
            return ServerResponse.createByErrorMessage("添加失败");
        }
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    /**
     *
     * 根据相关条件查询比赛是否存在
     * @param matchName
     * @param place
     * @param matchType
     * @return
     */
    public boolean matchExist(String matchName, String place, Integer matchType) {
        List<WanliMatch> list =null;
        list=matchDao.selectMatch(matchName,place,matchType);

        if(list.size()==0){
            return false;
        }
        return true;
    }

    /**
     * 根据相关信息获取比赛
     * @param matchName
     * @param place
     * @param matchType
     * @return
     */
    public WanliMatch selectMatch(String matchName, String place, Integer matchType){
        List<WanliMatch> list =null;
        list=matchDao.selectMatch(matchName,place,matchType);
        return list.get(0);
    }
    /**
     * 根据比赛id删除比赛，以及比赛所有相关信息
     * @param id
     * @return
     */
    public boolean deleteMatch(Integer id) {
        groupDao.deleteGroup(id);
        teacherMatchDao.deleteTeacherMatch(id);
        stuMatchDao.deleteStuMatch(id);
        Integer num =matchDao.deleteMatch(id);
        if(num==1){
           return true;
        }
        return false;
    }

    /**
     * 根据id更新比赛信息
     * @param wanliMatch
     * @return
     */
    public boolean updateMatchInformation(WanliMatch wanliMatch) {
        Integer num =matchDao.updateMatch(wanliMatch);
        if(num==0){
            return  false;
        }
        return true;
    }

    /**
     * 添加老师和比赛的相关信息
     * @param teacherId
     * @param matchId
     */
    public void insertTeacherMatch(String teacherId,Integer matchId){
        TeacherMatch teacherMatch =null;
        teacherMatch =teacherMatchDao.selectTeacherMatch(matchId,teacherId);
        if(teacherMatch==null){
            teacherMatchDao.insertTeacherMatch(teacherId,matchId);
        }
    }


    /**
     *
     * @return
     */
    public ServerResponse<PageInfo> getGroupList(Integer matchId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<GroupExt> list = groupDao.selectGroupExt(matchId);
        if(list.size() == 0) {
            return ServerResponse.createByErrorMessage("暂时无小组参与比赛");
        }
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * 删除一个group相关的信息
     * @param groupId
     * @return
     */
    public boolean deleteGroupAllInformation(Integer groupId) {
        int num=groupDao.deleteGroupByGroupId(groupId);
        stuMatchDao.deleteStuMatchByGroupId(groupId);
        if(num>0){
            return true;
        }
        return false;
    }

    /**
     * 查看小组成员
     * @param groupId
     * @return
     */
    public ServerResponse<List<StuMatch>> selectMemberByGroupId(Integer groupId) {
        List<StuMatch> list =stuMatchDao.selectStuMatchByGroupId(groupId);
        return ServerResponse.createBySuccess(list);
    }
}
