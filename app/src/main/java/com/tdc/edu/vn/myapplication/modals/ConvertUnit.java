/*
* Class Convert Unit
* Contain Convert unit such as: VND, METER...*/
package com.tdc.edu.vn.myapplication.modals;

public class ConvertUnit {
    private String unitID;
    private String unitName;
    private float rateToRootUnit;
    private String rootID;
    private String type;

    //Constructor
    public ConvertUnit(String unitID, String unitName, float rateToRootUnit, String rootID, String type) {
        this.unitID = unitID;
        this.unitName = unitName;
        this.rateToRootUnit = rateToRootUnit;
        this.rootID = rootID;
        this.type = type;
    }

    public ConvertUnit() {
    }

    //Getter and Setter

    public String getUnitID() {
        return unitID;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public float getRateToRootUnit() {
        return rateToRootUnit;
    }

    public void setRateToRootUnit(float rateToRootUnit) {
        this.rateToRootUnit = rateToRootUnit;
    }

    public String getRootID() {
        return rootID;
    }

    public void setRootID(String rootID) {
        this.rootID = rootID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //To string

    @Override
    public String toString() {
        return "ConvertUnit{" +
                "unitID='" + unitID + '\'' +
                ", unitName='" + unitName + '\'' +
                ", rateToRootUnit='" + rateToRootUnit + '\'' +
                ", rootID='" + rootID + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
