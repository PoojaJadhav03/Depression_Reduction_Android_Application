package com.example.uworthy;

class Savedatauser {
    String name;
    String mobileno;
    String emailid;
    String gender;

    String clnicaddress;
    String useername;
    String password;
    String usertype;
    public String imageName;
    String id;
    public String imageURL;

    public Savedatauser(String id, String name, String mobileno, String emailid, String gender, String clnicaddress, String useername, String password, String usertype, String tempImageName, String toString) {
        this.id=id;
        this.name=name;
        this.mobileno=mobileno;
        this.emailid=emailid;
        this.gender=gender;

        this.clnicaddress=clnicaddress;
        this.useername=useername;
        this.password=password;
        this.usertype=usertype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClnicaddress() {
        return clnicaddress;
    }

    public void setClnicaddress(String clnicaddress) {
        this.clnicaddress = clnicaddress;
    }

    public String getUseername() {
        return useername;
    }

    public void setUseername(String useername) {
        this.useername = useername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
