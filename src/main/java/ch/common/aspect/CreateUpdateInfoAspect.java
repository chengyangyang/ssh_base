package ch.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Vani
 * add update and create info when update or create any object with ASPECT
 *
 */
@Component("createUpdateInfoAspect")
@Aspect    //声明一个切面
@Order(10)
public class CreateUpdateInfoAspect {
	
	private static Logger logger = LoggerFactory.getLogger(CreateUpdateInfoAspect.class);
	
	@Pointcut(value="execution(* ch.dao..*.create*(..))") //声明一个切点
	public void createInfoAspect(){}
	
	@Pointcut(value="execution(* ch.dao..*.modify*(..))")
	public void updateInfoAspect(){}
	
	@Before(value="createInfoAspect()")
    public void beforeCreateInfo(JoinPoint pjp) throws Exception {
		
		logger.debug("EnterInto beforeCreateInfo");
		
		Object[] objectArray = pjp.getArgs();
		if(objectArray.length!=0){
			
			Object obj = objectArray[0];
			Date d = new Date();
			Timestamp ts = new Timestamp(d.getTime());
			//String username = SessionUtil.getUserNameFromSession();
			String username = "用户名";
			Method m1, m2, m3, m4;
			try {

				m1 = obj.getClass().getMethod("setCreateBy", String.class);
				m2 = obj.getClass().getMethod("setCreateDate", Timestamp.class);

				m3 = obj.getClass().getMethod("setUpdateBy", String.class);
				m4 = obj.getClass().getMethod("setUpdateDate", Timestamp.class);

				m1.invoke(obj, username);
				m2.invoke(obj, ts);
				m3.invoke(obj, username);
				m4.invoke(obj, ts);
				
			} catch (Exception e) {
				logger.error("ErrorIn beforeCreateInfo " + e.getMessage());
				e.printStackTrace();
				throw e;
				
			} 
		}
		logger.debug("ExitFrom beforeCreateInfo");
	}
	
	
	@Before(value="updateInfoAspect()")
    public void beforeUpdateInfo(JoinPoint pjp) throws Exception {
		
		logger.debug("EnterInto beforeUpdateInfo");
		
		Object[] objectArray = pjp.getArgs();
		if(objectArray.length!=0){
			Object obj = objectArray[0];
			Date d = new Date();
			Timestamp ts = new Timestamp(d.getTime());
			//String username = SessionUtil.getUserNameFromSession();

			String username = "用户名称";

					Method m1, m2;
			try{
				m1 = obj.getClass().getMethod("setUpdateBy", String.class);
				m2 = obj.getClass().getMethod("setUpdateDate", Timestamp.class);

				m1.invoke(obj, username);
				m2.invoke(obj, ts);
			} catch (Exception e) {
				logger.error("ErrorIn beforeUpdateInfo " + e.getMessage());
				e.printStackTrace();
				throw e;
			} 
			
		}
		
		logger.debug("ExitFrom beforeUpdateInfo");
	}




	/*//前置通知（进入环绕后执行，下一步执行方法）
	@Before(value="pointCut()")
	public void doAccessCheck(JoinPoint joinPoint){
		System.out.println("@Before前置通知:"+Arrays.toString(joinPoint.getArgs()));
	}

	//异常通知（出错时执行）
	@AfterThrowing(value="pointCut()",throwing="ex")
	public void doAfterThrow(JoinPoint joinPoint,Throwable ex){
		System.out.println("@AfterThrowing例外通知(异常通知)"+Arrays.toString(joinPoint.getArgs()));
		System.out.println("@AfterThrowing异常信息："+ex);
	}

	//后置通知(返回之前执行)
	@After(value="pointCut()")
	public void after(){
		System.out.println("@After后置通知...");
	}

	//最终通知（正常返回通知，最后执行）
	@AfterReturning(value="pointCut()")
	public void doAfter(){
		System.out.println("@AfterReturning最终通知...End!");
	}*/
}
