package wanli.pojo;

import java.util.Arrays;

/**
 * 报名接收数据类
 */
public class Apply {
    //小组名称
    private String groupname;
    //比赛id
    private Integer matchid;
    //报名成员
    private String[] stuid;
    private String[] name;


    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Integer getMatchid() {
        return matchid;
    }

    public void setMatchid(Integer matchid) {
        this.matchid = matchid;
    }

    public String[] getStuid() {
        return stuid;
    }

    public void setStuid(String[] stuid) {
        this.stuid = stuid;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "groupname='" + groupname + '\'' +
                ", matchid=" + matchid +
                ", stuid=" + Arrays.toString(stuid) +
                ", name=" + Arrays.toString(name) +
                '}';
    }
}
