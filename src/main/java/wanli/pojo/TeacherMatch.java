package wanli.pojo;

public class TeacherMatch {
    private Integer id;
    private String teacherId;
    private Integer matchId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    @Override
    public String toString() {
        return "TeacherMatch{" +
                "id=" + id +
                ", teacherId='" + teacherId + '\'' +
                ", matchId=" + matchId +
                '}';
    }
}
