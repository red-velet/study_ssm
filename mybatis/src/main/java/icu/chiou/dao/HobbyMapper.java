package icu.chiou.dao;

import icu.chiou.entity.Hobby;
import org.apache.ibatis.annotations.Insert;

/**
 * Created with IntelliJ IDEA.
 * Author: chiou
 * Date: 2023/4/6
 * Time: 20:13
 * Description: No Description
 */
public interface HobbyMapper {
    /**
     * 添加爱好
     *
     * @param hobby
     * @return
     */
    @Insert("insert into hobby (id,`name`,description) values (#{id},#{name},#{description})")
    int saveHobby(Hobby hobby);
}
