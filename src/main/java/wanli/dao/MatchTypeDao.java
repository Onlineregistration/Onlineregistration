package wanli.dao;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/22
 */
public interface MatchTypeDao {

	int insertMatchType(String typename);

	int deleteMatchTypeById(int id);

	int selectMatchTypeByName(String typename);
}
