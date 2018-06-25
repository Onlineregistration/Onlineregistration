package wanli.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wanli.dao.MatchDao;
import wanli.pojo.Matchtype;
import wanli.pojo.WanliMatch;
import wanli.service.MatchService;
import wanli.vo.ServerResponse;

import java.util.List;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/9
 */
@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	private MatchDao matchDao;

	@Override
	public ServerResponse<PageInfo> getMatchListByType(int type, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<WanliMatch> matchList = matchDao.getMatchListByType(type);
		if(matchList.size() == 0) {
			return ServerResponse.createByErrorMessage("没有比赛");
		}
		PageInfo pageInfo = new PageInfo(matchList);
		return ServerResponse.createBySuccess(pageInfo);
	}

	@Override
	public ServerResponse<List<Matchtype>> getMatchType() {
		List<Matchtype> list = matchDao.listMatchType();
		return ServerResponse.createBySuccess(list);
	}
}
