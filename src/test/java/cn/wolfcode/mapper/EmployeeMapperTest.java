package cn.wolfcode.mapper;

import cn.wolfcode.domain.Employee;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

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


}