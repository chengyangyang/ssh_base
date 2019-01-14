package ch.common.util;

import ch.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author cy
 * @date 2019年01月14日 15:31
 * version 1.0
 */
public class ElasticSearchUtils {


    /**
     *
     * 高亮显示的mapper重装,其中对象的主键为id 类型为String 不能进行改变
     * @return
     */
    public static SearchResultMapper highSearchResultMapper(){
        return new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                List<T> chunk = new ArrayList<T>();
                for (SearchHit searchHit : searchResponse.getHits()) {
                    if (searchResponse.getHits().getHits().length <= 0) {
                        return null;
                    }
                    try {
                        //将内容转换为对象
                        T t = JSONObject.parseObject(searchHit.getSourceAsString(), aClass);
                        Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                        if(null != highlightFields){
                            //遍历map集合  将高亮的内容替换进去
                            for(Map.Entry<String, HighlightField> entry : highlightFields.entrySet()){
                                String key = entry.getKey();
                                //利用反射将数据放入进入
                                Method method = aClass.getDeclaredMethod("set"+ StringUtils.capitalize(key),String.class);
                                method.invoke(t,entry.getValue().fragments()[0].toString());
                                //t.setIntroduce(higtFields.get("introduce"));
                                chunk.add(t);
                            }
                        }
                        //放入主键  固定只能使用id 使用string 类型 放入到里面
                        //利用反射将数据放入进入
                        Method method = aClass.getDeclaredMethod("setId",String.class);
                        method.invoke(t,searchHit.getId());

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                if (chunk.size() > 0) {
                    return  new AggregatedPageImpl<T>((List<T>) chunk);
                }
                return null;
            }
        };
    }


}
