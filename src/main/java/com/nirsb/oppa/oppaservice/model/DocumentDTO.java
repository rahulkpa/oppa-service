package com.nirsb.oppa.oppaservice.model;
import javax.persistence.*;

@Entity
@Table(name = "USERS_DOCUMENT")
public class DocumentDTO{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="DOC_ID")
    private long docId;
    @Column(name="MOB_NO")
    private long mobileNumber;
    @Column(name="NO_OF_COPIES")
    private int noOfCopies;
    @Column(name="DOC_TYPE")
    private String docTypes;
    @Column(name="DOC_FORMAT")
    private String docFormat;
    @Column(name="EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name="File")
    @Lob
    private byte[] file;

    public DocumentDTO() {
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public long getDocId() {
        return docId;
    }

    public void setDocId(long docId) {
        this.docId = docId;
    }
}
