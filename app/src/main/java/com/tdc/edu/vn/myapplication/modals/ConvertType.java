package com.tdc.edu.vn.myapplication.modals;

public class ConvertType {
    private String typeId;
    private String typeName;
    private String subTitle;
    private int image;

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public ConvertType(String typeId, String typeName, String subTitle, int image) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.subTitle = subTitle;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ConvertType() {
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
