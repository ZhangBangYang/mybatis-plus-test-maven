package cn.wolfcode.mapper;

import cn.wolfcode.domain.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface EmployeeMapper extends BaseMapper<Employee> {
    // 单表查询
    public List<Employee> selectByMapperList(); // 单表使用Mapper文件的形式查询所有。

    List<Employee> selectDeptAndEmp();
}
