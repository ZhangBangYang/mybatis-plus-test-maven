package cn.wolfcode;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("cn.wolfcode.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    // 步骤1：配置分页插件

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);

        paginationInnerInterceptor.setOverflow(true); //合理化

        interceptor.addInnerInterceptor(paginationInnerInterceptor);

        return interceptor;

    }
}
