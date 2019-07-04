package Guet.util;

import Guet.pojo.UserInfo;

public class UserManager {

    private static UserInfo user;

    public static UserInfo getUserInfo(){
        return user != null ? user : (user = new UserInfo());
    }

    public static void setUserInfo(UserInfo userInfo){
        user = userInfo;
    }

}
