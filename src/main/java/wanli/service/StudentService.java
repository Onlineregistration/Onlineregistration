package wanli.service;

import wanli.pojo.Group;
import wanli.vo.ServerResponse;

public interface StudentService {

    boolean addStuMatch(String stuId,String stuName, Integer matchId, Integer groupId,Integer groupPosition);

    boolean addGroupInformation(String groupName,Integer matchId);

    Group selectGroupByName(String groupName,Integer matchId);
}
