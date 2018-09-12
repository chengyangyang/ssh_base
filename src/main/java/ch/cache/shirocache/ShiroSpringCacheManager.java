package ch.cache.shirocache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

/**
 * 使用spring-cache作为shiro缓存
 * 缓存管理器
 */

/**
 * <p> 自定义cacheManage 扩张shiro里面的缓存 使用reids作缓存 </p>
 * <description>
 *  引入自己定义的CacheManager
 *  关于CacheManager的配置文件在spring-redis-cache.xml中
 * </description>
 * @author xxxx
 * @date 2018年2月3日
 * @time 14:01:53
 */

public class ShiroSpringCacheManager implements CacheManager ,Destroyable{

	private org.springframework.cache.CacheManager cacheManager;

	public org.springframework.cache.CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public void destroy() throws Exception {
		cacheManager = null;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name)  {
		if (name == null ){
			return null;
		}
		//如果使用redis,需要实现Cache<k,v>

		return new ShiroSpringCache<K,V>(name,getCacheManager());
	}


}