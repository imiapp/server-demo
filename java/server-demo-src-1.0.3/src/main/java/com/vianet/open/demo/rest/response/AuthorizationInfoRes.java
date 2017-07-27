package com.vianet.open.demo.rest.response;

public class AuthorizationInfoRes {

    // 授权用户数字身份号
    private String vportId;

    // 授权身份信息
    private String dob;
    private String sex;
    private String authority;
    private String name;
    private String cin;
    private String doe;
    private String doi;

    // 授权登录信息
    private String mobile;
    private String userName;

    public String getVportId() {
        return vportId;
    }
    public void setVportId(String vportId) {
        this.vportId = vportId;
    }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCin() {
        return cin;
    }
    public void setCin(String cin) {
        this.cin = cin;
    }
    public String getDoe() {
        return doe;
    }
    public void setDoe(String doe) {
        this.doe = doe;
    }
    public String getDoi() {
        return doi;
    }
    public void setDoi(String doi) {
        this.doi = doi;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
