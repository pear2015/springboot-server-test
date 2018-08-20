package com.gs.crms.statisticsanalysis.contract.model;

/**
 * Created by zhufengjie on 2017/9/19.
 */
public class ReportRtnModel {
    private String coord_X;
    private String typeName;
    private String coord_Y ;
    private String count;

    public String getCoord_X() {
        return coord_X;
    }

    public void setCoord_X(String coord_X) {
        this.coord_X = coord_X;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCoord_Y() {
        return coord_Y;
    }

    public void setCoord_Y(String coord_Y) {
        this.coord_Y = coord_Y;
    }


}
