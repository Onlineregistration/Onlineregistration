package wanli.pojo;

public class MyMatch {
    private Integer groupId;
    private String matchName;
    private String matchStartTime;
    private String matchEndTime;
    private String registStartTime;
    private String registEndTime;
    private String place;
    private Integer memberNum;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getMatchStartTime() {
        return matchStartTime;
    }

    public void setMatchStartTime(String matchStartTime) {
        this.matchStartTime = matchStartTime;
    }

    public String getMatchEndTime() {
        return matchEndTime;
    }

    public void setMatchEndTime(String matchEndTime) {
        this.matchEndTime = matchEndTime;
    }

    public String getRegistStartTime() {
        return registStartTime;
    }

    public void setRegistStartTime(String registStartTime) {
        this.registStartTime = registStartTime;
    }

    public String getRegistEndTime() {
        return registEndTime;
    }

    public void setRegistEndTime(String registEndTime) {
        this.registEndTime = registEndTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(Integer memberNum) {
        this.memberNum = memberNum;
    }

    @Override
    public String toString() {
        return "MyMatch{" +
                "groupId=" + groupId +
                ", matchName='" + matchName + '\'' +
                ", matchStartTime='" + matchStartTime + '\'' +
                ", matchEndTime='" + matchEndTime + '\'' +
                ", registStartTime='" + registStartTime + '\'' +
                ", registEndTime='" + registEndTime + '\'' +
                ", place='" + place + '\'' +
                ", memberNum=" + memberNum +
                '}';
    }
}
