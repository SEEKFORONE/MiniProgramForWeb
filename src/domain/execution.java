package domain;

public class execution {
    private int HID;
    private int UID;
    private int score;
    private String homework;

    public execution(){}

    public int getHID() {
        return HID;
    }

    public void setHID(int HID) {
        this.HID = HID;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }
}
