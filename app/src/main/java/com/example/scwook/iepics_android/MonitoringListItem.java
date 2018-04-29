package com.example.scwook.iepics_android;

public class MonitoringListItem {

    String pvName;
    String pvValue;
    int arrayImageID;
    int clockImageID;

    public MonitoringListItem(String pvName, String pvValue) {
        this.pvName = pvName;
        this.pvValue = pvValue;
    }

    public MonitoringListItem(String pvName, String pvValue, int arrayImageID, int clockImageID) {
        this.pvName = pvName;
        this.pvValue = pvValue;
        this.arrayImageID = arrayImageID;
        this.clockImageID = clockImageID;
    }

    public String getPVName() {
        return pvName;
    }

    public void setPVName(String pvName) {
        this.pvName = pvName;
    }

    public String getPVValue() {
        return pvValue;
    }

    public void setPVValue(String pvValue) {
        this.pvValue = pvValue;
    }

    public int getArrayImage() {
        return arrayImageID;
    }

    public void setArrayImageID(int arrayImageID) {
        this.arrayImageID = arrayImageID;
    }

    public int getClockImageID() {
        return clockImageID;
    }

    public  void setClockImageID(int clockImageID) {
        this.clockImageID = clockImageID;
    }
}
