package wanli.pojo;

public class GroupExt {
    private Integer groupId;
    private String groupName;
    private String stuName;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Override
    public String toString() {
        return "GroupExt{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", stuName='" + stuName + '\'' +
                '}';
    }
}
