package com.neusoft.springboottest.dto;


public class ManufactWithUserId {
     int user_id;
    String name_en;
    String name_cn;
    String gmc_report_type;
    String gmc_report_url;
    String description;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName_en() {
        return name_en;
    }

    public String getName_cn() {
        return name_cn;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getGmc_report_type() {
        return gmc_report_type;
    }

    public void setGmc_report_type(String gmc_report_type) {
        this.gmc_report_type = gmc_report_type;
    }

    public String getGmc_report_url() {
        return gmc_report_url;
    }

    public void setGmc_report_url(String gmc_report_url) {
        this.gmc_report_url = gmc_report_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ManufactWithUserId{" +
                "user_id=" + user_id +
                ", name_en='" + name_en + '\'' +
                ", name_cn='" + name_cn + '\'' +
                ", gmc_report_type='" + gmc_report_type + '\'' +
                ", gmc_report_url='" + gmc_report_url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
