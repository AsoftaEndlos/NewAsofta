package com.endlos.admin.file.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Table
@Entity(name = "Fileuploading_Table")
public class Fileinfomodel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fileName;
    private String fileType;
    @Type(type = "org.hibernate.type.BinaryType")
    @Lob
    private byte[] image;

    public Fileinfomodel() {
        // TODO Auto-generated constructor stub
    }

    public Fileinfomodel(String fileName, String fileType, byte[] image) {
        super();
        this.fileName = fileName;
        this.fileType = fileType;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
