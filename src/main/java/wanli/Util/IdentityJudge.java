package wanli.Util;

import wanli.common.Const;
import wanli.pojo.User;

import javax.servlet.http.HttpSession;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/22
 */
public abstract class IdentityJudge {
	/*
	 * 权限检测
	 * @author zxh
	 * @date 2018/6/22
	 * @param [session]
	 * @return java.lang.String
	 */
	public static String identification(HttpSession session, int identity) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return "用户未登录";
		}
		if (user.getIdentity() != identity) {
			return "你没有权限对此操作";
		}
		return null;
	}
}
