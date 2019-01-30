package com.amy.pie.sharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.amy.pie.sharding.algorithm.DatabaseShardingAlgorithm;
import com.amy.pie.sharding.algorithm.TablePreciseShardingAlgorithm;
import com.amy.pie.sharding.algorithm.TableRangeShardingAlgorithm;
import io.shardingsphere.core.api.ShardingDataSourceFactory;
import io.shardingsphere.core.api.config.ShardingRuleConfiguration;
import io.shardingsphere.core.api.config.TableRuleConfiguration;
import io.shardingsphere.core.api.config.strategy.StandardShardingStrategyConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/29 16:25
 */
@Slf4j
@Configuration
public class ShardingConfig {
    @Value("${spring.datasource.ds0.url}")
    private String ds0Url;
    @Value("${spring.datasource.ds0.username}")
    private String ds0Username;
    @Value("${spring.datasource.ds0.password}")
    private String ds0Password;
    @Value("${spring.datasource.ds0.driver-class-name}")
    private String ds0Driver;

    @Bean(name = "ds_0")
    @Qualifier("ds_0")
    @Primary
    public DataSource dataSource0() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setPassword(ds0Password);
        druidDataSource.setUsername(ds0Username);
        druidDataSource.setDriverClassName(ds0Driver);
        druidDataSource.setUrl(ds0Url);
        return druidDataSource;
    }

    @Value("${spring.datasource.ds1.url}")
    private String ds1Url;
    @Value("${spring.datasource.ds1.username}")
    private String ds1Username;
    @Value("${spring.datasource.ds1.password}")
    private String ds1Password;
    @Value("${spring.datasource.ds1.driver-class-name}")
    private String ds1Driver;

    @Bean(name = "ds_1")
    @Qualifier("ds_1")
    public DataSource dataSource1() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setPassword(ds1Password);
        druidDataSource.setUsername(ds1Username);
        druidDataSource.setDriverClassName(ds1Driver);
        druidDataSource.setUrl(ds1Url);
        return druidDataSource;
    }

    @Bean(name = "shardingDataSource")
    @Qualifier("shardingDataSource")
    public DataSource getDataSource(@Qualifier("ds_0") DataSource ds_0, @Qualifier("ds_1") DataSource ds_1) throws SQLException {
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");

        //配置分库策略
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new DatabaseShardingAlgorithm()));

        // 配置分表策略
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", new TablePreciseShardingAlgorithm(), new TableRangeShardingAlgorithm()));

        // 获取数据源对象
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds_0", ds_0);
        dataSourceMap.put("ds_1", ds_1);
        Properties properties = new Properties();
        properties.setProperty("sql.show", Boolean.TRUE.toString());
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new HashMap<>(), properties);
    }

    // 配置表规则
    private static TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_order");
        result.setActualDataNodes("ds_${0..1}.t_order_${[0, 1]}");
        result.setKeyGeneratorColumnName("order_id");
        return result;
    }

    // 配置表规则
    private static TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_order_item");
        result.setActualDataNodes("ds_${0..1}.t_order_item_${[0, 1]}");
        return result;
    }
}
