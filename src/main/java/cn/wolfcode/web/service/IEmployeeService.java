package cn.wolfcode.web.service;

import cn.wolfcode.domain.Employee;
        import cn.wolfcode.qo.EmployeeQuery;
        import com.baomidou.mybatisplus.core.metadata.IPage;
        import com.baomidou.mybatisplus.extension.service.IService;

// IService 集成了一些基本的service方法。
public interface IEmployeeService extends IService<Employee> {
    IPage<Employee> query(EmployeeQuery qo);
}
