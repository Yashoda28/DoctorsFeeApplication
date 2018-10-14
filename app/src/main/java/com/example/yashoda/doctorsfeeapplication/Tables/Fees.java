package com.example.yashoda.doctorsfeeapplication.Tables;

import java.util.Date;

public class Fees
{
    private int feesID;
    private Date dateTime;
    private String reason;
    private Double total;
    private int patientID;

    public Fees(int feesID, Date dateTime, String reason, Double total, int patientID) {
        this.feesID = feesID;
        this.dateTime = dateTime;
        this.reason = reason;
        this.total = total;
        this.patientID = patientID;
    }

    public int getFeesID() {
        return feesID;
    }

    public void setFeesID(int feesID) {
        this.feesID = feesID;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    @Override
    public String toString() {
        return "Fees{" +
                "feesID=" + feesID +
                ", dateTime=" + dateTime +
                ", reason='" + reason + '\'' +
                ", total=" + total +
                ", patientID=" + patientID +
                '}';
    }
}
