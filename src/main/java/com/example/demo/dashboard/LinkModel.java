package com.example.demo.dashboard;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "Link")
@Entity
public class LinkModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String title;
	private String link;
	private boolean home;
	private int lstatus0;
	private int lstatus1;
	private int lstatus2;

	@OneToMany(mappedBy = "linkmodel", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnoreProperties("linkmodel")
	public List<DashBoardModel> dash = new ArrayList<DashBoardModel>();

	public LinkModel() {
		// TODO Auto-generated constructor stub
	}

	public LinkModel(String title, String link, boolean home, int lstatus0, int lstatus1, int lstatus2,
			DashBoardModel dashmodel) {
		super();
		this.title = title;
		this.link = link;
		this.home = home;
		this.lstatus0 = lstatus0;
		this.lstatus1 = lstatus1;
		this.lstatus2 = lstatus2;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isHome() {
		return home;
	}

	public void setHome(boolean home) {
		this.home = home;
	}

	public int getLstatus0() {
		return lstatus0;
	}

	public void setLstatus0(int lstatus0) {
		this.lstatus0 = lstatus0;
	}

	public int getLstatus1() {
		return lstatus1;
	}

	public void setLstatus1(int lstatus1) {
		this.lstatus1 = lstatus1;
	}

	public int getLstatus2() {
		return lstatus2;
	}

	public void setLstatus2(int lstatus2) {
		this.lstatus2 = lstatus2;
	}

	

}
