package icu.chiou.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: chiou
 * Date: 2023/4/6
 * Time: 20:44
 * Description: No Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    private int id;
    private String name;
    //用于关系维护
    List<Employee> employees;
}
