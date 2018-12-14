/*
package ch.activiti.service;

import java.util.Date;
import java.util.List;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("*classpath:/spring-hibernate.xml")
public class MyProcessService {

	
	 @Autowired
	 private ProcessEngine processEngine;
	 @Autowired
	 private RuntimeService runtimeService;
	 @Autowired
	 private TaskService taskService;
	 @Autowired
	 private HistoryService historyService;
	 @Autowired
	 private RepositoryService repositoryService;
	*/
/* @Autowired
	 private ManagementService managementService;
	 @Autowired
	 private FormService formService;*//*

	 
	@Test
	 //1.部署流程
	 public void publishActiviti() {
		 
		 Deployment deploy = repositoryService.createDeployment().addClasspathResource("diagrams/MyProcess.bpmn").deploy();
		 String id = deploy.getId();
		 Date deploymentTime = deploy.getDeploymentTime();
		 String name = deploy.getName();
		 String tenantId = deploy.getTenantId();
		 String category = deploy.getCategory();
		 System.out.println("部署流程的id为"+id);
		 System.out.println("部署流程的deploymentTime为"+deploymentTime);
		 System.out.println("部署流程的name为"+name);
		 System.out.println("部署流程的tenantId为"+tenantId);
		 System.out.println("部署流程的category为"+category);
	 }
	
	//2.启动一个流程
	public void startProcess(String key) {
		runtimeService.startProcessInstanceByKey("myProcess");//对应key值,可以保证每次启动的都是最新的
		
		//可以查询所有订阅了特定信号事件的执行：
		List<Execution> executions = runtimeService.createExecutionQuery()
			      .signalEventSubscriptionName("alert")
			      .list();
	}
	//3.获得任务的列表
	public void getTaskList() {
		List<Task> list = taskService.createTaskQuery().taskDefinitionKey("usertask1").list();
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();//通过组查询
		
		taskService.claim(tasks.get(0).getId(), "fozzie");//领取任务，领取后会从其他成员的列表中消失
		List<Task> taskfozzie = taskService.createTaskQuery().taskAssignee("fozzie").list();//个人任务
		taskService.complete(tasks.get(0).getId());//完成任务
	}
}
*/
