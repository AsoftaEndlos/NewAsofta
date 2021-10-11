package com.endlos.admin.dashboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Table(name = "DashBoard")
@Entity
public class DashBoardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String icon;
    private String link;
    @Column(nullable = true)
    private boolean home;
    private int dstatus0;
    private int dstatus1;
    private int dstatus2;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "dashboard_id", referencedColumnName = "id")
    @JsonIgnoreProperties("dash")
    private LinkModel linkmodel;

    public DashBoardModel() {

    }


    public DashBoardModel(String title, String icon, String link, boolean home, int dstatus0, int dstatus1,
                          int dstatus2) {
        super();
        this.title = title;
        this.icon = icon;
        this.link = link;
        this.home = home;
        this.dstatus0 = dstatus0;
        this.dstatus1 = dstatus1;
        this.dstatus2 = dstatus2;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public int getDstatus0() {
        return dstatus0;
    }

    public void setDstatus0(int dstatus0) {
        this.dstatus0 = dstatus0;
    }

    public int getDstatus1() {
        return dstatus1;
    }

    public void setDstatus1(int dstatus1) {
        this.dstatus1 = dstatus1;
    }

    public int getDstatus2() {
        return dstatus2;
    }

    public void setDstatus2(int dstatus2) {
        this.dstatus2 = dstatus2;
    }

    public LinkModel getLinkmodel() {
        return linkmodel;
    }

    public void setLinkmodel(LinkModel linkmodel) {
        this.linkmodel = linkmodel;
    }

    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

}
