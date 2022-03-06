package com.nirsb.oppa.oppaservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "It contains Document Information")
@Component
public class Document{
    @ApiModelProperty(notes = "Users' mobile number to notify him")
    @Size(min = 10, max = 10,message = "Mobile number size should be 10")
    private long mobileNumber;
    @ApiModelProperty(notes = "How many copy user wants to print")
    @NotBlank(message = "Number of copy can not be blank")
    private int noOfCopies;
    @ApiModelProperty(notes = "Color or black & White")
    @NotNull
    private String docTypes;
    @ApiModelProperty(notes = "OneSided or DoubleSided")
    @NotNull
    private String docFormat;
    @ApiModelProperty(notes = "Users' email address to notify him about print status")
    @Email(message = "Please enter a valid email address")
    private String emailAddress;
    @ApiModelProperty(notes = "Document which user want to print")
    private MultipartFile file;

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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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
