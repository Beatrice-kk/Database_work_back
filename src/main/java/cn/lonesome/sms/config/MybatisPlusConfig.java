package cn.lonesome.sms.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author Yale
 * @version 1.0
 */
@Configuration
public class MybatisPlusConfig {
    //@Bean
    //public EasySqlInjector easySqlInjector() {
    //    return new EasySqlInjector();
    //}
    //
    //@Bean
    //public GlobalConfig globalConfiguration() {
    //    GlobalConfig config = new GlobalConfig();
    //    config.setSqlInjector(easySqlInjector());
    //    return config;
    //}

    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
        paginationInterceptor.setMaxLimit(20L);
        paginationInterceptor.setDbType(DbType.OPENGAUSS);
        paginationInterceptor.setOptimizeJoin(true);
        return paginationInterceptor;
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.setInterceptors(Collections.singletonList(paginationInnerInterceptor()));
        return interceptor;
    }
}