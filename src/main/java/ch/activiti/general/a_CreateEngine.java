package ch.activiti.general;

import org.activiti.engine.ProcessEngineConfiguration;

/**
 * Description:创建流程引擎，一般引擎只创建一次
 *
 * @author cy
 * @date 2018年08月03日 16:34
 * version 1.0
 */
public class a_CreateEngine {

    public static void main(String[] args) {
        //流程引擎的创建（只选用了一种方法，也可以使用其他方法。），使用流程引擎配置文件，也就是创建23+张表
        //act_ge_property 是流程引擎的属性表。
        ProcessEngineConfiguration processEngine = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        //设置数据库信息
        processEngine.setJdbcUrl("jdbc:mysql://localhost:3306/cheng?useUnicode=true&characterEncoding=utf-8");
        processEngine.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngine.setJdbcUsername("root");
        processEngine.setJdbcPassword("admin");
        processEngine.setDatabaseSchemaUpdate(processEngine.DB_SCHEMA_UPDATE_TRUE);//对数据库进行更新
        processEngine.buildProcessEngine();//创建引擎
        System.out.println("创建引擎成功");
    }

}
