package com.ashnaco.g16.hibernate;

import com.ashnaco.g16.hibernate.cfg.DataObject;

public class TestNozzleInfo {
    
    private String gsId;
    private String nozzleId;
    private String nozzleName;
    private String oilCanId;
    private String state;
    private String initStatus;
    
    public TestNozzleInfo() {
    }

    public void setGsId(String gsId) {
        this.gsId = gsId;
    }

    public String getGsId() {
        return gsId;
    }

    public void setNozzleId(String nozzleId) {
        this.nozzleId = nozzleId;
    }

    public String getNozzleId() {
        return nozzleId;
    }

    public void setNozzleName(String nozzleName) {
        this.nozzleName = nozzleName;
    }

    public String getNozzleName() {
        return nozzleName;
    }

    public void setOilCanId(String oilCanId) {
        this.oilCanId = oilCanId;
    }

    public String getOilCanId() {
        return oilCanId;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setInitStatus(String initStatus) {
        this.initStatus = initStatus;
    }

    public String getInitStatus() {
        return initStatus;
    }
}
