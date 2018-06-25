package wanli.service;

import wanli.pojo.Group;
import wanli.pojo.MyMatch;
import wanli.vo.ServerResponse;

import java.util.List;

public interface StudentService {

    boolean addStuMatch(String stuId,String stuName, Integer matchId, Integer groupId,Integer groupPosition);

    boolean addGroupInformation(String groupName,Integer matchId);

    Group selectGroupByName(String groupName,Integer matchId);

    ServerResponse<List<MyMatch>> listMyMatchByStuid(String stuId);

    boolean deleteMyMatch(Integer groupId);
}
