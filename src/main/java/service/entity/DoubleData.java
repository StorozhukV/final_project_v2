package service.entity;



public class DoubleData {
    private String typeOfInfo;
    private String value1;
    private String value2;

    public DoubleData(String typeOfInfo, String value1, String value2) {
        this.typeOfInfo = typeOfInfo;
        this.value1 = value1;
        this.value2 = value2;
    }

    public String getTypeOfInfo() {
        return typeOfInfo;
    }

    public void setTypeOfInfo(String typeOfInfo) {
        this.typeOfInfo = typeOfInfo;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }
}
