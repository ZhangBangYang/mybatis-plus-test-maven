package cn.wolfcode.web.service.impl;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.mapper.EmployeeMapper;
import cn.wolfcode.qo.EmployeeQuery;
import cn.wolfcode.web.service.IEmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


// 实现类继承 ServiceImpl<mappee接口，实体类>
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements IEmployeeService {

    // 实现分页查询。
    @Override
    public IPage<Employee> query(EmployeeQuery qo) {

        IPage<Employee> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());  //设置分页信息

        QueryWrapper<Employee> wrapper = Wrappers.<Employee>query();  //拼接条件

        return super.page(page,wrapper);
    }
}
