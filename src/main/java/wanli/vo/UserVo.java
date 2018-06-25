package wanli.vo;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/22
 */
public class UserVo extends BasicVo {

	private Integer sex;
	private String phone;
	private String mail;
	private String Tsize;
	private Integer identity;

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTsize() {
		return Tsize;
	}

	public void setTsize(String tsize) {
		Tsize = tsize;
	}

	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

}
