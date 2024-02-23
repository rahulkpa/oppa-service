package com.nirsb.oppa.oppaservice.model;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class Document {

    @Size(min = 10, max = 10, message = "Mobile number size should be 10")
    private long mobileNumber;

    @NotBlank(message = "Number of copy can not be blank")
    private int noOfCopies;
    @NotNull
    private String docTypes;

    @NotNull
    private String docFormat;

    @Email(message = "Please enter a valid email address")
    private String emailAddress;

    public Document() {
    }


    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    public String getDocTypes() {
        return docTypes;
    }

    public void setDocTypes(String docTypes) {
        this.docTypes = docTypes;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDocFormat() {
        return docFormat;
    }

    public void setDocFormat(String docFormat) {
        this.docFormat = docFormat;
    }


    @Override
    public String toString() {
        return "Document{" +
                "mobileNumber=" + mobileNumber +
                ", noOfCopies=" + noOfCopies +
                ", docTypes='" + docTypes + '\'' +
                ", docFormat='" + docFormat + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
