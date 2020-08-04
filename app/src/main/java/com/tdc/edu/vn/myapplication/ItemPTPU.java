package com.tdc.edu.vn.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ItemPTPU {
    public String ptpu;

    public ItemPTPU() {

    }

    public ItemPTPU(String ptpu) {
        this.ptpu = ptpu;
    }

    public String getPtpu() {
        return ptpu;
    }

    public void setPtpu(String ptpu) {
        this.ptpu = ptpu;
    }
}
