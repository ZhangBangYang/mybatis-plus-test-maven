package cn.wolfcode.mapper;
import cn.wolfcode.domain.Employee;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
        Employee employee = new Employee(null,"将昵贤",null,"123456","@123.com",18,1,1L,null);
        int insert = employeeMapper.insert(employee);
    }
    // 修改
    @Test
    public void testUpdate() {
        Employee employee = new Employee(21L,"将昵贤",null,"1233333","@333.com",16,1,1L,null);
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


    @Test
    public void testLike() {
        // 模糊查询
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.likeLeft("name","四");

        List<Employee> employees = employeeMapper.selectList(wrapper);


        // likeLeft 条件左面加%   likeRight 条件右边加 %
        // like 左右两边都加　％　
    }

    @Test
    public void testLast() {
        // 最后位置 --》一般拼接 Limit
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.last("Limit 2,3");
        List<Employee> employees = employeeMapper.selectList(wrapper);
    }

    @Test
    public void testAndOr() {
        // and  or
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("password",1); // 条件1
        wrapper.or(); // or 相连
        wrapper.like("name","总"); // 条件2
        List<Employee> employees = employeeMapper.selectList(wrapper);
        // 默认多条件之间是  and 相连。
    }

    @Test
    public void testAndOr2() {
        // and 和 or 加括号  (and 优先级 默认是高于 or 的 )
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.gt("age",35); // 第一个条件 年龄大于35

        // 如何将第二和第三个参数用括号括起来?  or里面加入的多个条件会被加括号。
        wrapper.or(employeeQueryWrapper -> employeeQueryWrapper.eq("password","1")
                .like("name","总")
        );
        /*wrapper.eq("password","1"); // 第二个条件 密码等于 1
        wrapper.or();
        wrapper.like("name","总");  // 第三个参数*/

        List<Employee> employees = employeeMapper.selectList(wrapper);
    }

    @Test
    public void testIsNull() {
        // 条件     xxx为null
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("admin"); // 条件是 admin不为 null的 。
        List<Employee> employees = employeeMapper.selectList(wrapper);
        //SELECT id,name,password,email,age,admin,dept_id FROM employee WHERE (admin IS NOT NULL)
    }

    @Test
    public void testIsNotNull() {
        // 条件    xxx 不为null
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        // condition 条件  、  根据是否成立决定是否添加该条件
        QueryWrapper<Employee> admin = wrapper.isNotNull(1>2,"admin");
        List<Employee> employees = employeeMapper.selectList(wrapper);

    }

    // 修改子类 特有的方法。
    @Test
    public void testUpdate4() {
        UpdateWrapper<Employee> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("name","jiangnixian"); // 修改封装的独特方法。 设置字段的值。
        updateWrapper.eq("id",21); // 注意一定要加条件。 不加条件全表更新。

        // 修改之前先查出来。
        Employee employee = employeeMapper.selectById("21");
        employee.setName(null);
        // 更新数据
        employeeMapper.update(employee,updateWrapper);

    }

    // 查询子类   特有的方法
    @Test
    public void testQu1() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.select("name","age","password"); // 可变参数,指定要查询的列

        List<Employee> employees = employeeMapper.selectList(wrapper);
        // SELECT name,age,password FROM employee
    }
    //  工具类 wrappers 用于创建各种wrapper
    @Test
    public void testWrappers() {
        // 自己直接new进行创建效果一样。
        QueryWrapper<Object> wrapper = Wrappers.emptyWrapper();
    }

    // 排序
    @Test
    public void testOrderby() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        // 四个参数   condition 是否加入此排序条件
        //          是否为asc 是否为升序
        //  参加排序的列 ---- 可以多个
        wrapper.orderBy(true,true,"age","name");

        List<Employee> employees = employeeMapper.selectList(wrapper);
    }

    @Test
    public void testOrderBy2() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("age");
        wrapper.last("limit 3");
        // 按照年龄降序排列，取三个
        employeeMapper.selectList(wrapper);  // 按照年龄降序排列
    }

    @Test
    public void testGroupBy() {
        // 分组查询
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();

        wrapper.groupBy("dept_id"); // 按照ID分组
        // 分组查询之后的列已经变化了 不能使用 entry 存储查询结果。
        wrapper.select("count(1) total","dept_id");
        // 分组之后的过滤
        wrapper.having("total>3"); // 直接过滤查询分组后的结果。
        List<Employee> employees = employeeMapper.selectList(wrapper);

    }

    @Test
    public void testEQ1() {
        // 条件查询之比较运算符号
        // allQq  全都进行等值判断，使用ANA连接
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age","30");
        map.put("dept_id",3); //
        wrapper.allEq(map); // 相当于将map中所有的键值都作为等值连接的条件。中间使用 AND 进行连接。

        // 批量的等值连接；
        employeeMapper.selectList(wrapper);
    }

    @Test
    public void testEq2() {
        // 单个等值条件
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("name","吴总");

        // 单个的等值判断。
        List<Employee> employees = employeeMapper.selectList(wrapper);
    }

    @Test
    public void testEq3() {
        // ne 单个的不等值连接 。   不等于xxx
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.ne("name","吴总"); // 条件是name不等于 吴总
        List<Employee> employees = employeeMapper.selectList(wrapper);

    }

    @Test
    public void testNeeeee() {
        // 非等值条件 大于和小于
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.gt("age",25); // 年龄大于25    gt 大于xxx
        wrapper.lt("age",35);  // 年龄小于30   lt 小于xxx
        //     25<age<30
        employeeMapper.selectList(wrapper);
    }

    @Test
    public void testNeeeeeee() {
        // 非等值连接 之大于等于   小于等于
        QueryWrapper<Employee> wrapper = new QueryWrapper<Employee>();
        wrapper.ge("age",23); // 大于等于
        wrapper.le("age",40); // 小于等于
        List<Employee> employees = employeeMapper.selectList(wrapper);
    }

    @Test
    public void testBetween() {  // 在xxx之间。  包括两边
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.between("age",35,45); //   35<age<45
        employeeMapper.selectList(wrapper);
    }

    @Test
    public void testBetween3() {  // 在xxx之间。  包括两边
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.notBetween("age",35,45); //   不在此范围的
        employeeMapper.selectList(wrapper);
    }

    @Test
    public void testIsNull1() {  // 不为null
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("admin"); // admin不为空的
        employeeMapper.selectList(wrapper);
    }
    @Test
    public void testIsNull2() {  // 为null的
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.isNull("admin");
        employeeMapper.selectList(wrapper);
    }

    @Test
    public void testIn() {  // 在xxx等于 xxx 或 xxx 或 xx....
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();

        List<Integer> integers = Arrays.asList(25, 35);
        wrapper.in("age",integers); // 年龄为  25 以及 35 的

        employeeMapper.selectList(wrapper);
    }

    @Test
    public void testNotIn() {  // 在xxx等于 xxx 或 xxx 或 xx....
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();

        List<Integer> integers = Arrays.asList(25, 35);
        wrapper.notIn("age",integers); // 年龄不为 25 和 35 的

        employeeMapper.selectList(wrapper);
    }

    @Test
    public void testInSql() {  // 在xxx等于 xxx 或 xxx 或 xx....
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();

        List<Integer> integers = Arrays.asList(25, 35);
        wrapper.inSql("age","25,35");

        employeeMapper.selectList(wrapper);
    }

    @Test
    public void testNotInSql() {  // 在xxx等于 xxx 或 xxx 或 xx....
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();

        List<Integer> integers = Arrays.asList(25, 35);
        wrapper.notInSql("age","25,35"); // 年龄不为 25 和 35 的

        employeeMapper.selectList(wrapper);
    }

    // 模糊查询
    @Test
    public void testLike1() {
        // like
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.like("name","总");
        employeeMapper.selectList(wrapper);
    }
    // 模糊查询
    @Test
    public void testLike2() {
        // like
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.notLike("name","总");  // 不满住模糊条件的数据
        employeeMapper.selectList(wrapper);
    }
    // 模糊查询
    @Test
    public void testLike3() {
        // like
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.likeLeft("name","总");  // 左边加 %
        employeeMapper.selectList(wrapper);
    }

    // 模糊查询
    @Test
    public void testLike4() {
        // like
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.likeRight("name","总");  // 右边加 %
        employeeMapper.selectList(wrapper);
    }

    // and
    @Test
    public void testAND() {
        // like
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.likeLeft("name","总");  // 右边加 % 的模糊查询

        wrapper.eq("age","25"); // 等值条件 age=25
        employeeMapper.selectList(wrapper);
        // 条件之间默认是and 进行连接
    }


    // or
    @Test
    public void testOr() {
        // like
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.likeLeft("name","总");  // 右边加 % 的模糊查询

        // 两个条件之间变为 ro
        wrapper.or();

        wrapper.eq("age","25"); // 等值条件 age=25
        employeeMapper.selectList(wrapper);
        // 条件之间默认是and 进行连接
    }

    // mappe单表查询
    @Test
    public void testMapper() {

        List<Employee> employees = employeeMapper.selectByMapperList();
        System.out.println("employees = " + employees);
    }
    // mappe 多表联查
    @Test
    public void testMapperxxx() {

        List<Employee> employees = employeeMapper.selectDeptAndEmp();
        System.out.println("employees = " + employees);
    }


}