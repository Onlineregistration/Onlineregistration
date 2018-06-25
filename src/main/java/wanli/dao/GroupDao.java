package wanli.dao;

import org.apache.ibatis.annotations.Param;
import wanli.pojo.Group;
import wanli.pojo.GroupExt;

import java.util.List;

public interface GroupDao {

    Integer insertGroup(@Param("groupName") String groupName,@Param("matchId") Integer matchId);

    Group selectGroupByGroupName(@Param("groupName") String groupName,@Param("matchId") Integer matchId);

    Integer deleteGroup(Integer matchId);

    List<GroupExt> selectGroupExt(Integer matchId);

    Integer deleteGroupByGroupId(Integer groupId);
}
