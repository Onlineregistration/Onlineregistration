package wanli.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import wanli.pojo.Matchtype;
import wanli.pojo.WanliMatch;

import java.util.List;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/9
 */
@Repository
public interface MatchDao {

	List<WanliMatch> getMatchListByType(int type);

	List<Matchtype> listMatchType();

	Integer insertMatch(WanliMatch wanliMatch);

	List<WanliMatch> selectMatch(@Param("matchName")String matchName,
								 @Param("place") String place,
								 @Param("matchType")Integer matchType);
	Integer deleteMatch(Integer id);

	Integer updateMatch(WanliMatch wanliMatch);

	WanliMatch selectMatchById(Integer id);
}
