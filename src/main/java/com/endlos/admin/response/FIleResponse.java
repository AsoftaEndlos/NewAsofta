package com.endlos.admin.response;

public class FIleResponse {
    private String fileid;
    private String filetype;
    private String message;
    private boolean status;

    public FIleResponse(String fileid, String filetype, String message, boolean status) {
        this.fileid = fileid;
        this.filetype = filetype;
        this.message = message;
        this.status = status;
    }

    public FIleResponse() {

    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
