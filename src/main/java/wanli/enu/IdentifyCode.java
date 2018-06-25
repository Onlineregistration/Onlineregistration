package wanli.enu;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/22
 */
public enum IdentifyCode {


	STUDENT(1, "STUDENT"),
	TEACHER(2, "TEACHER"),
	ADMIN(3, "ADMIN");


	private final int code;
	private final String desc;


	IdentifyCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
