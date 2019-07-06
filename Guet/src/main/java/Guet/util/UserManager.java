package Guet.util;

import Guet.pojo.StudentInfo;
import Guet.pojo.TeacherInfo;
import Guet.pojo.UserInfo;

public class UserManager {

    private static UserInfo user;

    public static UserInfo getUserInfo(){
        return user != null ? user : (user = new StudentInfo());
//        return user;
    }

    public static void setUserInfo(UserInfo userInfo){
        user = userInfo;
    }

}
