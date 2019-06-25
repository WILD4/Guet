package Guet.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class ServerSqlSession {

    static String resource = "mybatis-config.xml";
    static InputStream inputStream;
    static SqlSessionFactory sqlSessionFactory;
    static SqlSession sqlSession;

    public static SqlSession openSqlSession() throws IOException {

        if(inputStream == null){
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        sqlSession = sqlSessionFactory.openSession(true);
        return sqlSession;
    }

    public static void closeSqlSession(){
        if(sqlSession != null)
            sqlSession.close();
    }

//    public void getSqlSession(){
//    }

}
