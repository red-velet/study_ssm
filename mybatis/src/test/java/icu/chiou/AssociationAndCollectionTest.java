package icu.chiou;

import icu.chiou.dao.DeptMapper;
import icu.chiou.dao.EmployeeMapper;
import icu.chiou.entity.Dept;
import icu.chiou.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: chiou
 * Date: 2023/4/6
 * Time: 20:52
 * Description: No Description
 */
@Slf4j
public class AssociationAndCollectionTest {
    public SqlSessionFactory sqlSessionFactory;

    @Before
    public void open() {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream("mybatis-config.xml");
        this.sqlSessionFactory = builder.build(resource);
    }

    @Test
    public void testAssociation() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // todo 一对一查询
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> list = employeeMapper.selectOneToOne();
            log.info("list: {}", list);
        }
    }

    @Test
    public void testAssociation2() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // todo 一对一查询
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> list = employeeMapper.selectOneToOne2();
            log.info("list: {}", list);
        }
    }

    @Test
    public void testCollection() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // todo 一对一查询
            DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
            List<Dept> depts = deptMapper.selectOneToMany();
            log.info("list: {}", depts);
        }
    }

    @Test
    public void testCollection2() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // todo 一对一查询
            DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
            List<Dept> depts = deptMapper.selectOneToMany();
            log.info("list: {}", depts);
        }
    }
}
