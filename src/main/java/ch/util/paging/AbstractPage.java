package ch.util.paging;

import com.trm.constants.SystemConstants;
import com.trm.entity.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 这个抽象类实现了接口中的所有方法，但是定义了一个抽象方法init()，在子类中必须实现这个方法。
 * 上面的一个接口和一个抽象类看起来比较简单，你可能会觉得好像什么都没有做，实现上确实没有做什么,
 * 但是却可以给开发带来很大的帮助。我们可以根据自己的需要要继承这个抽象类，而数据可以通过各种方式获得，
 * 比如直接通过一个List获得，或者通过JDBC，Hibernate等等，不过我们都需要把结果封装到一个List里面,通过Hibernate就显得特别方便了。
 *
 */
public abstract class AbstractPage implements Pageable{
	private static Logger logger = LoggerFactory.getLogger(AbstractPage.class);
	
	private int currentPage;
	private int pageSize;
	private int pages;
	
	protected int count;
	@SuppressWarnings("rawtypes")
	protected List result;
	
	/**
	 * 指定当前页
	 * @param currentPage
	 */
	public AbstractPage(int currentPage) {
		this(currentPage, Pageable.DEFAULT_PAGESIZE);
	}

	/**
	 * 指定当前页和页大小
	 * @param currentPage
	 * @param pageSize
	 */
	public AbstractPage(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	
	protected void checkPage(int currentPage)throws BusinessException{
		
		if(currentPage < 1){
			logger.error("No result for your query, currentPage is "+currentPage );
			
			BusinessException be  = new BusinessException();
			be.setExceptionMsg("No result for your query, currentPage is "+currentPage);
			be.setExceptionCode(SystemConstants.REASONCODE_2048);
			throw be;
		}
	}
	
	/**
	 * 这个方法被子类重写用来初始化，也就是计算count值和result结果，在子类 的构造函数中调用。
	 */
	abstract protected void init() throws BusinessException;
	
	@SuppressWarnings("rawtypes")
	public List getResult(){
		return result;
	}
	
	public int getCount(){
		return count;
	}
	
	public int getPageSize(){
		return pageSize;
	}
	
	public int getCurrentPage(){
		return currentPage;
	}
	
	public int getPages(){
		if(pages==0) {
			this.pages=count/pageSize;
			if(count%pageSize != 0){
				this.pages += 1;
			}
		}
		return pages;
	}
	
}
