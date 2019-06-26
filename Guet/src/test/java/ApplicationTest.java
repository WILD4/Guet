import Guet.controller.CourseController;
import Guet.dao.CourseMapper;
import Guet.dao.StudentMapper;
import Guet.pojo.SelectedCourse;
import Guet.pojo.StudentInfo;
import Guet.util.CourseManager;
import Guet.util.ServerSqlSession;
import Guet.util.StudentManager;
import Guet.view.CenterView;
import Guet.view.JTableView;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

@RunWith(JUnit4.class)
public class ApplicationTest {

//    @Resource(name = "")
    public StudentMapper studentMapper;
    public CourseMapper courseMapper;

    private static Logger logger = Logger.getLogger(String.valueOf(ApplicationTest.class));

    @Before
    public void setUp() throws IOException {
        studentMapper = ServerSqlSession.openSqlSession().getMapper(StudentMapper.class);
        courseMapper = ServerSqlSession.openSqlSession().getMapper(CourseMapper.class);
    }

    @Test
    public void testQueryStudent() throws IOException {
        StudentInfo student;

//        List<StudentInfo> students;
//        students = studentMapper.queryAllStu();
//
//        for(StudentInfo stu : students){
//            logger.info(stu);
//        }
        CourseController courseController = new CourseController();
        List<SelectedCourse> listContent;
        listContent = courseController.getNoSelectedCourse("1700420201","BG0000007X0") ;

        for(SelectedCourse selectedCourse : listContent)
            logger.info(selectedCourse);

    }

    @Test
    public void testView(){


    }

}