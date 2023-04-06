package icu.chiou.dao;

import icu.chiou.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: chiou
 * Date: 2023/4/6
 * Time: 18:42
 * Description: No Description
 */
public interface UserMapper {
    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> selectList();

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    User selectUserById(int id);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 参数传递测试
     *
     * @param id
     * @param name
     * @param pws
     * @return
     */
    int insertUser(int id, String name, String pwd);

    /**
     * 参数传递测试2
     *
     * @param id
     * @param name
     * @param pws
     * @return
     */
    int insertUserParam(@Param("id") int id, @Param("name") String name, @Param("pwd") String pws);


    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除一个用户
     *
     * @param id
     * @return
     */
    int deleteUser(int id);

    List<User> getByUserNameLikeMethod1(@Param("name") String username);

    List<User> getByUserNameLikeMethod2(@Param("name") String username);

    List<User> getByUserNameLikeMethod3(@Param("name") String username);

    /**
     * 根据一些参数查询
     *
     * @param map
     * @return
     */
    List<User> getUsersByParams(Map<String, String> map);
}
