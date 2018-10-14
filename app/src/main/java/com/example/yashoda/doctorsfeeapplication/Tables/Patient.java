package com.example.yashoda.doctorsfeeapplication.Tables;

import java.util.Date;

public class Patient {
    private int patientID;
    private String patientName;
    private String surname;
    private String emailAddress;
    private String password;
    private String iDNumber;
    private Date dateofBirth;
    private String cellNumber;
    private String bloodType;

    public Patient(int patientID, String patientName, String surname, String emailAddress, String password, String iDNumber, Date dateofBirth, String cellNumber, String bloodType) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.password = password;
        this.iDNumber = iDNumber;
        this.dateofBirth = dateofBirth;
        this.cellNumber = cellNumber;
        this.bloodType = bloodType;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setiDNumber(String iDNumber) {
        this.iDNumber = iDNumber;
    }

    public void setDateofBirth(Date dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getPatientID() {
        return patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getiDNumber() {
        return iDNumber;
    }

    public Date getDateofBirth() {
        return dateofBirth;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public String getBloodType() {
        return bloodType;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientID=" + patientID +
                ", patientName='" + patientName + '\'' +
                ", surname='" + surname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", iDNumber='" + iDNumber + '\'' +
                ", dateofBirth=" + dateofBirth +
                ", cellNumber='" + cellNumber + '\'' +
                ", bloodType='" + bloodType + '\'' +
                '}';
    }
}
