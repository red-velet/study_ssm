package icu.chiou.dao;

import icu.chiou.entity.Dept;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: chiou
 * Date: 2023/4/6
 * Time: 20:45
 * Description: No Description
 */
public interface DeptMapper {
    /**
     * 通过id查询部门信息
     *
     * @param id
     * @return
     */
    Dept selectById(Integer id);

    /**
     * 查询各个部门的和其部门的所有员工
     *
     * @param dept
     * @return
     */
    List<Dept> selectOneToMany();

    List<Dept> selectOneToMany2();
}
