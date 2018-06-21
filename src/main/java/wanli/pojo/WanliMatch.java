package wanli.pojo;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class WanliMatch {

    private Integer id;
    private Integer matchType;
    private String matchName;
    private String matchStartTime;
    private String matchEndTime;
    private String registStartTime;
    private String registEndTime;
    private String place;
    private Integer memberNum;
    private String teacherId;
    private Integer groupNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatchType() {
        return matchType;
    }

    public void setMatchType(Integer matchType) {
        this.matchType = matchType;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    @Override
    public String toString() {
        return "WanliMatch{" +
                "id=" + id +
                ", matchType=" + matchType +
                ", matchName='" + matchName + '\'' +
                ", matchStartTime=" + matchStartTime +
                ", matchEndTime=" + matchEndTime +
                ", registStartTime=" + registStartTime +
                ", registEndTime=" + registEndTime +
                ", place='" + place + '\'' +
                ", memberNum=" + memberNum +
                ", teacherId=" + teacherId +
                ", groupNum=" + groupNum +
                '}';
    }
}
