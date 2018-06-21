package wanli.pojo;

/**
 * 学生与比赛对应 对象类
 */
public class StuMatch {

    private Integer id;
    private String stuId;
    private String stuName;
    private Integer matchId;
    private Integer groupId;
    private Integer groupPosition;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupPosition() {
        return groupPosition;
    }

    public void setGroupPosition(Integer groupPosition) {
        this.groupPosition = groupPosition;
    }

    @Override
    public String toString() {
        return "StuMatch{" +
                "id=" + id +
                ", stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", matchId=" + matchId +
                ", groupId=" + groupId +
                ", groupPosition=" + groupPosition +
                '}';
    }
}
