package ch.util.paging;

import java.util.List;

/*
 * 这个接口非常简单，就是包括一个结果列表和一些分页的必要信息，这里注意几点：
 * 1.这个接口的实现表示的是某一次查询的某一页数据，和上次查询无关
 * 2.这个接口的实现应该是只读的，也就是说不可以修改的。
 * 3.getPages()方法是冗余的，但是这里仍然提供这个方法。
 * 
 * */

public interface Pageable {
	
	public final static int DEFAULT_PAGESIZE=20;
	
	/**
	 * 查询结果
	 */
	@SuppressWarnings("rawtypes")
	public List getResult();
	
	/**
	 * 查询总数
	 */
	public int getCount();
	
	/**
	 * 查询页大小
	 */
	public int getPageSize();
	
	/**
	 * 查询当前页
	 */
	public int getCurrentPage();
	
	/**
	 * 查询总页数
	 */
	public int getPages();
	
	public int getStartIndex();
	
	public int getEndIndex();

}
