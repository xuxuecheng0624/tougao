package com.eprobj.enums;

/**
 * Created by IntelliJ IDEA
 * 被操作的对象单元
 * @author kangjian
 * @date 2019/9/10
 */
public enum OperationUnit {

    /**
     * 被操作的单元
     */
    UNKNOWN("unknown"),
    USER("user"),
    LOG("log"),
    CONTRIBUTION("contribution"),
    CONTRIBUTORCLICKLOG("contributorclicklog"),
    CONSULT("consult"),
    DOCUMENTS("documents");

    private String value;

    OperationUnit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
