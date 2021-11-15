package csv_generation.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ApiError {

    private String errorCode;
    private HttpStatus status;
    private String date;
    private String notes;
    private String message;
    public ApiError(String errorCode, HttpStatus status,String notes,String message) {
        this.errorCode = errorCode;
        this.status = status;
        this.date = new Date().toString();
        this.notes = notes;
        this.message = message;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNotes() {
        return notes;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
