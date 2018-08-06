package ch.activiti.general;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;

/**
 * Description:流程引擎的部署
 *
 * @author cy
 * @date 2018年08月03日 17:22
 * version 1.0
 */
public class b_DeploymentEngine {

    private static ProcessEngineConfiguration processEngine = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();

    public static void main(String[] args) {
        ClassLoader classLoader = b_DeploymentEngine.class.getClassLoader();
        System.out.println(classLoader.getResource(""));
        //省略bpmn的画法
        //通过RepositoryService 进行部署，它是对应的就是流程的静态资源，将图片和bpmn上传至数据库。
        //流程引擎的部署所对应的表有：
        RepositoryService repositoryService = processEngine.getRepositoryService();
        /*repositoryService.createDeployment()
                .addInputStream().addInputStream();*/
    }
}
