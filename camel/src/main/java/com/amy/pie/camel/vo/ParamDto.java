package com.amy.pie.camel.vo;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/24 15:19
 */
public class ParamDto {
    private String empCode;
    private String effective_date;
    private String actionType;

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEffective_date() {
        return effective_date;
    }

    public void setEffective_date(String effective_date) {
        this.effective_date = effective_date;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}
