package domain;

import java.sql.Date;

public class resource {
    private int RID;
    private int courseID;
    private String path;
    private Date createTime;

    public resource(){}

    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
