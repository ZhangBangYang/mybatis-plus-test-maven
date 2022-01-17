package cn.wolfcode.mapper;
import cn.wolfcode.domain.Employee;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import javax.swing.text.AttributeSet;
import java.util.*;

@SpringBootTest
class EmployeeMapperTest {
    @Autowired
    EmployeeMapper employeeMapper;
    @Test
    public void testDelete() {
        // 封装删除条件
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("name","赵总");
        wrapper.eq("age",35);
        // 执行删除
        employeeMapper.delete(wrapper);
    }

    @Test
    public void testSelect1() {
        // select count(*) from employee where dept_id = 5;
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("dept_id",5);
        Integer count = employeeMapper.selectCount(wrapper);
        System.out.println("count = " + count);
    }


    // 增加
    @Test
    public void testInsert() {
        Employee employee = new Employee(null,"将昵贤",null,"123456","@123.com",18,1,1L);
        int insert = employeeMapper.insert(employee);
    }
    // 修改
    @Test
    public void testUpdate() {
        Employee employee = new Employee(21L,"将昵贤",null,"1233333","@333.com",16,1,1L);
        // 根据ID修改。
        int i = employeeMapper.updateById(employee);
    }
    @Test
    public void testUpdate2() {
        // 根据条件修改
        UpdateWrapper<Employee> wrapper = new UpdateWrapper<>();
        // 设置更新条件
        wrapper.eq("name","将昵贤").eq("age",16);
        // 设置更新字段
        wrapper.set("password","111111");
        // 执行更新语句
        employeeMapper.update(null,wrapper);
    }

    // 删除
    @Test
    public void testDelete1() {
        employeeMapper.deleteById("21");
    }

    @Test
    public void testDelete2() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("name","赵一明").eq("id",4); // 多个条件默认使用and连接。
        employeeMapper.delete(wrapper);
    }

    @Test
    public void testDelete3() {
        // 使用map来封装查询条件
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","赵一明");
        map.put("age",20);   // 多个条件之间使用AND相连。
        employeeMapper.deleteByMap(map);
    }

    @Test
    public void testDelete4() {
        // 根据ID批量删除
        List<Integer> ids = Arrays.asList(1, 2, 3, 4); // DELETE FROM employee WHERE id IN ( ? , ? , ? , ? )
        employeeMapper.deleteBatchIds(ids);
    }

    // 查询
    @Test
    public void testSelect2() {
        // 根据ID查询
        Integer id = 10;
        Employee employee = employeeMapper.selectById(id);
        System.out.println(employee);

    }

    @Test
    public void testSelect3() {
        // 根据ID批量的查询
        List<Integer> ids = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Employee> employees = employeeMapper.selectBatchIds(ids);
        employees.forEach(System.out::println);
    }

    @Test
    public void testSelect4() {
        // 根据Map中封装的条件查询
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","郑总");
        map.put("password",1L);

        List<Employee> employees = employeeMapper.selectByMap(map);
        for (Employee employee : employees) {
            System.out.println("employee = " + employee);  // 多个条件之间用 AND 连接
        }
    }

    @Test
    public void testSelect5() {
        // 根据 Wrapper 封装查询参数
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("name","吴六明");
        Integer integer = employeeMapper.selectCount(wrapper);
        System.out.println("integer = " + integer);

        // 没有条件就是查询所有
        Integer integer1 = employeeMapper.selectCount(null);
        System.out.println("integer1 = " + integer1);
    }

    @Test
    public void testSelect6() {
        // 根据条件查询数据集合
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("password","1").eq("dept_id",3);
        // SELECT id,name,password,email,age,admin,dept_id FROM employee WHERE (password = ? AND dept_id = ?)
        List<Employee> employees = employeeMapper.selectList(wrapper);
        for (Employee employee : employees) {
            System.out.println("employee = " + employee);
        }
    }

    @Test
    public void testSelect7() {
        // 返回Map集合。 数据用map封装。   一行数据一个Map
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();

        List<Map<String, Object>> maps = employeeMapper.selectMaps(wrapper);
        for (Map<String, Object> map : maps) {
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                System.out.println("entry = " + entry);
            }
        }
    }

    @Test
    public void testSelect8() {

        // 分页参数，以及接收分页后的数据。
        Page<Employee> page = new Page<>(1,5);

        // 查询条件
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();


        // 分页查询
        Page<Employee> page1 = employeeMapper.selectPage(page, wrapper); // 没有添加条件就是全部查询
        List<Employee> records = page1.getRecords();
        for (Employee record : records) {
            System.out.println("record = " + record);
        }
    }


    // 继承体系    abstructWrapper  两个主要实现类 updataWrapper QueryWrappper
    // Warpper继承体系常用方法
    @Test
    public void testWrapper() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("name","zhangsan");
        wrapper.allEq(new HashMap<String,Object>()); // 等值   同时一堆等值。

        wrapper.ne("name","lishi"); // ne 不等于

        wrapper.gt("age",10); //大于
        wrapper.lt("age",100); // 小于

        //    less then 小于   lt
        //    le   小于等于    less than or equal  equal to  小于等于
        // eq  等于

        // ne 不等于
        // ge greater   ge 大于等于   le 小于等于
        // gt 大于


        //    ge  大于等于
        //    le  小于等于
        //    eq  等于
        //    lt  小于
        //    gt 大于
        //    ne  不等于
        //

        // between  在什么什么中间。  包括两边。
        // notBetween 不在什么什么中间。

        // like 模糊查询
        // orderByDesc 降序排列
        // orderByAsc 升序排列
        // groupBy 分组
















    }


}