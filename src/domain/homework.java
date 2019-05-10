package domain;

public class homework {
    private int HID;
    private int courseID;
    private String discription;

    public homework(){}

    public int getHID() {
        return HID;
    }

    public void setHID(int HID) {
        this.HID = HID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
