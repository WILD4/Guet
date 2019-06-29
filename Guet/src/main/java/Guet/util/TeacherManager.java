package Guet.util;

import Guet.pojo.TeacherInfo;

public class TeacherManager {

    private static TeacherInfo teacherInfo;

    public static TeacherInfo getTeacher(){
        if(teacherInfo == null)
            return teacherInfo = new TeacherInfo();
        else
            return teacherInfo;
    }

    public static void setTeacher(TeacherInfo tea){
        teacherInfo = tea;
    }

}
