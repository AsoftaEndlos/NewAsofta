package com.endlos.admin.file.model;

import javax.persistence.*;


@Entity
@Table(name = "master_data")
//uniqueConstraints = {@UniqueConstraint(columnNames = "barcode")}
public class Masterdatamodel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "ITEM_Id")
    private long itemid;
    @Column(name = "BARCODE", unique = true, updatable = true)
    private long barcode;


    @Column(name = "MATERIAL_TYPE")
    private int materialtype;

    @Column(name = "MATERIAL")
    private String material;

    @Column(name = "ITEM_DESCRIPTION")
    private String itemdescription;

    @Column(name = "ITEM_VOLUME")
    private int itemvolume;

    @Column(name = "ITEM_WEIGHT")
    private int itemweight;

    @Column(name = "ITEM_VALUE")
    private float itemvalue;

    @Column(name = "DATA_ACQUISITION")
    private int dataacquisition;

    public Masterdatamodel() {

    }


    public Masterdatamodel(long id, long itemid, long barcode, int materialtype, String material,
                           String itemdescription, int itemvolume, int itemweight, float itemvalue, int dataacquisition) {
        super();
        this.id = id;
        this.itemid = itemid;
        this.barcode = barcode;
        this.materialtype = materialtype;
        this.material = material;
        this.itemdescription = itemdescription;
        this.itemvolume = itemvolume;
        this.itemweight = itemweight;
        this.itemvalue = itemvalue;
        this.dataacquisition = dataacquisition;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBarcode() {
        return barcode;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public int getMaterialtype() {
        return materialtype;
    }

    public void setMaterialtype(int materialtype) {
        this.materialtype = materialtype;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    public int getItemvolume() {
        return itemvolume;
    }

    public void setItemvolume(int itemvolume) {
        this.itemvolume = itemvolume;
    }

    public int getItemweight() {
        return itemweight;
    }

    public void setItemweight(int itemweight) {
        this.itemweight = itemweight;
    }

    public float getItemvalue() {
        return itemvalue;
    }

    public void setItemvalue(float itemvalue) {
        this.itemvalue = itemvalue;
    }

    public int getDataacquisition() {
        return dataacquisition;
    }

    public void setDataacquisition(int dataacquisition) {
        this.dataacquisition = dataacquisition;
    }


    public long getItemid() {
        return itemid;
    }


    public void setItemid(long itemid) {
        this.itemid = itemid;
    }


}
