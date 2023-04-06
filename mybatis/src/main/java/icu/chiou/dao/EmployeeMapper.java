package icu.chiou.dao;

import icu.chiou.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: chiou
 * Date: 2023/4/6
 * Time: 20:45
 * Description: No Description
 */
public interface EmployeeMapper {
    /**
     * 查询每个员工和它所在的部门信息-多条sql
     *
     * @return
     */
    List<Employee> selectOneToOne();

    /**
     * 查询每个员工和它所在的部门信息-一条sql
     *
     * @return
     */
    List<Employee> selectOneToOne2();

    Employee selectByDid(@Param("did") Integer did);
}
