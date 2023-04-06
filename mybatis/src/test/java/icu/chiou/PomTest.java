package icu.chiou;

import icu.chiou.dao.HobbyMapper;
import icu.chiou.dao.UserMapper;
import icu.chiou.entity.Hobby;
import icu.chiou.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: chiou
 * Date: 2023/4/6
 * Time: 17:00
 * Description: No Description
 */
@Slf4j
public class PomTest {
    @Test
    public void testJunit() {
        System.out.println("hello world");
    }

    @Test
    public void testMysql() throws Exception {
        //1.数据库连接的4个基本要素：
        String url = "jdbc:mysql://localhost:3306/study_mybatis?characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";
        //8.0之后名字改了  com.mysql.cj.jdbc.Driver
        String driverName = "com.mysql.cj.jdbc.Driver";

        //2.实例化Driver
        Class clazz = Class.forName(driverName);
        Driver driver = (Driver) clazz.newInstance();
        //3.注册驱动
        DriverManager.registerDriver(driver);
        //4.获取连接
        Connection conn = DriverManager.getConnection(url, username, password);

        PreparedStatement preparedStatement = conn.prepareStatement("select id,`username`,`password` from user where id = ?");
        preparedStatement.setInt(1, 1);
        ResultSet resultSet = preparedStatement.executeQuery();

        // 处理结果集
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            System.out.println(user);
        }
    }

    @Test
    public void testMybatis() throws Exception {
        //1、创建一个SqlSessionFactory的建造者,用于创建SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //2、使用builder构建一个sqlSessionFactory,此处我们基于一个xml配置文件,此过程会进行xml文件的解析，过程相对比较复杂
        InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = builder.build(resource);
        // 3、通过sqlSessionFactory获取另一个session，此处使用【工厂设计模式】
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("UserMapper.selectList");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testMybatisByInterface() throws Exception {
        //1、创建一个SqlSessionFactory的建造者,用于创建SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //2、使用builder构建一个sqlSessionFactory,此处我们基于一个xml配置文件,此过程会进行xml文件的解析，过程相对比较复杂
        InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = builder.build(resource);
        // 3、通过sqlSessionFactory获取另一个session，此处使用【工厂设计模式】
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> users = userMapper.selectList();
            for (User user : users) {
                log.info("user: {}", user);
            }
        }
    }

    public SqlSessionFactory sqlSessionFactory;

    @Before
    public void open() {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream("mybatis-config.xml");
        this.sqlSessionFactory = builder.build(resource);
    }

    @Test
    public void testSelect() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectUserById(1);
            log.info("user: {}", user);
        }
    }

    @Test
    public void testInsert() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User userEntity = new User(10, "jack", "jackpwd");
            int affectRows = userMapper.addUser(userEntity);
            log.info("affectRows: {}", affectRows);
            sqlSession.commit();
        }
    }

    @Test
    public void testParams() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int affectRows = userMapper.insertUser(11, "rose", "rosepwd");
            log.info("affectRows: {}", affectRows);
            sqlSession.commit();
        }
    }

    @Test
    public void testParamsPlus() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int affectRows = userMapper.insertUserParam(11, "rose", "rosepwd");
            log.info("affectRows: {}", affectRows);
        }
    }

    @Test
    public void testUpdate() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User userEntity = new User(11, "roserose", "roserosepwd");
            int affectRows = userMapper.updateUser(userEntity);
            log.info("affectRows: {}", affectRows);
        }
    }

    @Test
    public void testDelete() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int affectRows = userMapper.deleteUser(11);
            log.info("affectRows: {}", affectRows);
        }
    }

    @Test
    public void testLikeMethod1() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            String username = "%w%";
            List<User> users = userMapper.getByUserNameLikeMethod1(username);
            log.info("users: {}", users);
        }
    }

    @Test
    public void testLikeMethod2() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            String username = "w";
            List<User> users = userMapper.getByUserNameLikeMethod2(username);
            log.info("users: {}", users);
        }
    }

    @Test
    public void testLikeMethod3() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            String username = "w";
            List<User> users = userMapper.getByUserNameLikeMethod3(username);
            log.info("users: {}", users);
        }
    }

    @Test
    public void testMapParams() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put("name", "wendy");
            queryParams.put("password", "wendypwd");
            List<User> usersByParams = userMapper.getUsersByParams(queryParams);
            log.info("usersByParams: {}", usersByParams);
        }
    }

    @Test
    public void testAnnotation() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            HobbyMapper hobbyMapper = sqlSession.getMapper(HobbyMapper.class);
            hobbyMapper.saveHobby(new Hobby(10, "飙车", "飙车啊飙车"));
        }
    }
}
