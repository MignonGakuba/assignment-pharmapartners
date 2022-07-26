package com.assignment.pharmapartners.helper;

import java.util.HashMap;
import java.util.Map;

/**
 * Errorcode is a enum that contains the value which unexpected events occur.
 * It helps maintain the normal, desired flow of the program even when unexpected events occur
 * Main focus is on the  exception handling control flow.
 */
public enum ErrorCode {

    NOTHING_WRONG(0),
    FAILED_JSON_CONVERSION(1000),

    FAILED_CREATING_CURRENCY(3001),
    FAILED_DELETE_CURRENCY(3003),
    FAILED_RETRIEVING_ALL_CURRENCIES(3004),
    FAILED_UPDATE_CURRENCY(3005),
    FAILED_RETRIEVE_CURRENCY_BY_Id(3006),

    GENERIC_OR_UNKNOWN(1);

    private int value;
    private static final Map map = new HashMap<>();

    ErrorCode() {

    }

    ErrorCode(int value) {
        this.value = value;
    }

    static {
        for (ErrorCode errorCode : ErrorCode.values()) {
            map.put(errorCode.value, errorCode);
        }
    }

    public static ErrorCode valueOf(int errorCode) {
        ErrorCode code = (ErrorCode) map.get(errorCode);
        if (code == null) {
            code = GENERIC_OR_UNKNOWN;
        }
        return code;
    }

    public int getValue() {
        return value;
    }

}
