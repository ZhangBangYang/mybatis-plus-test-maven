package cn.wolfcode.mapper;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.EmployeeQuery;
import cn.wolfcode.web.service.IEmployeeService;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class EmployeeServcieTest {
    @Autowired
    private IEmployeeService employeeService;
    @Test
    public void test1() {
        // 获取 Mapper接口
        BaseMapper<Employee> baseMapper = employeeService.getBaseMapper();

        List<Employee> employees = baseMapper.selectList(null);

    }
    @Test
    public void test2() {
        // 获取 Mapper接口
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("name","赵总");

        Employee one = employeeService.getOne(wrapper);

        System.out.println(one);


    }

    @Test
    public void test3() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.like("name","总");
        wrapper.lt("age",26); //

        employeeService.list(wrapper);

    }








    @Test
    public void test4() {
        // 高级高级以及分页查询
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.like("name","总");
        wrapper.lt("age",26); //

        employeeService.list(wrapper);

    }


    @Test
    public void testPage(){

        EmployeeQuery qo = new EmployeeQuery();
        qo.setPageSize(3);
        qo.setCurrentPage(2);


        IPage<Employee> page = employeeService.query(qo);


        System.out.println("当前页：" + page.getCurrent());
        System.out.println("总页数：" + page.getPages());
        System.out.println("每页显示条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("当前页显示记录：" + page.getRecords());
    }


    // 增加
    @Test
    public void test6() {
        Employee employee = new Employee(null, "diannao", null, "123456", "123@qq", 20, 1, 3L, null);

        employeeService.save(employee);
    }

    // 增加
    @Test
    public void test7() {
        Employee employee = new Employee(21L, "diannaoxxx", null, "123456", "123@qq", 20, 1, 3L, null);

        employeeService.updateById(employee);
    }

    // 删除
    @Test
    public void test8() {

        employeeService.removeById(21L);
    }
}