package wanli.dao;

import org.apache.ibatis.annotations.Param;
import wanli.pojo.StuMatch;

import java.util.List;

public interface StuMatchDao {
    Integer insertStuMatch(@Param("stuId")String stuId,
                           @Param("stuName")String stuName,
                           @Param("matchId")Integer matchId,
                           @Param("groupId")Integer groupId,
                           @Param("groupPosition")Integer groupPosition);
    StuMatch selectStuMatchByStuIdAndMatchId(@Param("stuId") String stuId,
                                             @Param("matchId") Integer matchId);

    Integer deleteStuMatch(Integer matchId);

    Integer deleteStuMatchByGroupId(Integer groupId);

    List<StuMatch> selectStuMatchByGroupId(Integer groupId);
}
