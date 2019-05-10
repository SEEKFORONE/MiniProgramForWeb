package domain;

public class knowledge {
    private int cellID;
    private int unitID;
    private String cellName;
    private String discription;
    private String path;

    public knowledge(){}

    public int getCellID() {
        return cellID;
    }

    public void setCellID(int cellID) {
        this.cellID = cellID;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
