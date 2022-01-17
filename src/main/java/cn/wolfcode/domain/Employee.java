package cn.wolfcode.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@TableName("employee")  //指定对应的表名
@NoArgsConstructor
public class Employee {

    @TableId(type= IdType.AUTO) // 指定ID 以及自增长
    private Long id;

    @TableField("name")  // 指定对应的字段吗。
    private String name;

    @TableField(exist=false)  // 排除不属于数据库字段的类型。
    private String vip;


    private String password;

    private String email;

    private int age;

    private int admin;

    private Long deptId;
}
