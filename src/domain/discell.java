package domain;

import java.sql.Date;

public class discell {
    private int ID;
    private int DID;
    private int UID;
    private String discussion;
    private Date createTime;

    public discell(){}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDID() {
        return DID;
    }

    public void setDID(int DID) {
        this.DID = DID;
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
