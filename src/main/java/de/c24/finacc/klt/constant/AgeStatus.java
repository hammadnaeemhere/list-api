package de.c24.finacc.klt.constant;

public enum AgeStatus {
    YOUNG("too young"),
    OLD("too old"),
    FUNNY("funny"),
    OK("OK");


    private String status;

    AgeStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
