package Guet.util;

import Guet.pojo.StudentInfo;

public class StudentManager {

    private static StudentInfo student;

    public static StudentInfo getStudent(){
        if(student == null)
            return student = new StudentInfo();
        else
            return student;
    }

    public static void setStudent(StudentInfo stu){
        student = stu;
    }

}
