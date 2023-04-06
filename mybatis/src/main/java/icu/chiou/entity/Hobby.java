package icu.chiou.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Author: chiou
 * Date: 2023/4/6
 * Time: 20:11
 * Description: No Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hobby implements Serializable {
    private int id;
    private String name;
    private String description;
}
