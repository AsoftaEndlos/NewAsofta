package com.example.demo.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "master_database")
public class Masterdatamodel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ITEM_Id")
	private long id;

	@Column(name = "BARCODE")
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
	private int itemvalue;

	@Column(name = "DATA_ACQUISITION")
	private int dataacquisition;
	
	public Masterdatamodel() {
		// TODO Auto-generated constructor stub
	}

	
	public Masterdatamodel(long id, long barcode, int materialtype, String material, String itemdescription,
			int itemvolume, int itemweight, int itemvalue, int dataacquisition) {
		super();
		this.id = id;
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

	public int getItemvalue() {
		return itemvalue;
	}

	public void setItemvalue(int itemvalue) {
		this.itemvalue = itemvalue;
	}

	public int getDataacquisition() {
		return dataacquisition;
	}

	public void setDataacquisition(int dataacquisition) {
		this.dataacquisition = dataacquisition;
	}
		
	
	
}
