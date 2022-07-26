package com.assignment.pharmapartners.helper;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * This Class is responsible to send  the client-side the response of api call.
 *
 * @param item      hold the important data  of the requested api call.
 * @param timestamp  property holds the date-time instance of when the error happened or succeed .
 * @param message   let the client-side know what the status is on the api call.
 * @param result    check if the rest call was successfully or not for the client-side
 * @param errorCode check which error handling events occur
 */
public class JsonResult {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private Boolean result;
    private Object item;
    private int errorCode;
    private ErrorCode errorEnum;
    private String errorMessage;

    public JsonResult() {
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ErrorCode getErrorEnum() {
        return errorEnum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        setErrorEnum(ErrorCode.valueOf(errorCode));
    }

    public void setErrorCode(ErrorCode errorCode) {
        setErrorEnum(errorCode);
        setErrorCode(errorCode.getValue());
    }

    public void setErrorCode(String errorCode) {
        setErrorCode((ErrorCode.valueOf(errorCode)));
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    private void setErrorEnum(ErrorCode errorEnum) {
        this.errorEnum = errorEnum;
    }


}