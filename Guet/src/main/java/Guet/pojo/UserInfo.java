package Guet.pojo;

import java.sql.Date;

public class UserInfo {

    private String UID;
    private String userName;
    private String userSex;
    private Date userBirthday;
    private String password;
    private String status;
    private String birthPlace;

    public UserInfo(){}

    public UserInfo(String UID, String userName, String userSex, Date userBirthday, String password, String status, String birthPlace) {
        this.UID = UID;
        this.userName = userName;
        this.userSex = userSex;
        this.userBirthday = userBirthday;
        this.password = password;
        this.status = status;
        this.birthPlace = birthPlace;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "UID='" + UID + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userBirthday=" + userBirthday +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                '}';
    }

}
