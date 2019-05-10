package domain;

import java.sql.Date;

public class discussion {
    private int DID;
    private int courseID;
    private int UID;
    private String discussion;
    private Date createTime;

    public discussion(){}

    public int getDID() {
        return DID;
    }

    public void setDID(int DID) {
        this.DID = DID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getDiscussion() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
