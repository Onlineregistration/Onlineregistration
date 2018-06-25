package wanli.service;

import com.github.pagehelper.PageInfo;
import wanli.pojo.Matchtype;
import wanli.vo.ServerResponse;

import java.util.List;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/9
 */
public interface MatchService {

	ServerResponse<PageInfo> getMatchListByType(int type, int pageNum, int pageSize);

	ServerResponse<List<Matchtype>> getMatchType();

	boolean addMatchType(String typename);

	boolean delMatchTypeById(int id);

	boolean selectMatchTypeByName(String typename);

}
