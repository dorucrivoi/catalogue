package com.demo.catalogue.common;

import java.time.LocalDateTime;

public class ErrorMessage {

    private LocalDateTime timestamp;
//    private int status;
    private String error;
    private String message;

    public ErrorMessage(LocalDateTime timestamp, int status, String error, String message) {
        this.timestamp = timestamp;
      //  this.status = status;
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

//    public int getStatus() {
//        return status;
//    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }


}
