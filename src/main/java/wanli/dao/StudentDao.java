package wanli.dao;

import wanli.pojo.MyMatch;

import java.util.List;

public interface StudentDao {
    List<MyMatch> selectMatch(String stuId);

}
