package ch.service.sys;

import ch.common.util.CacheUtils;
import ch.dao.sys.SerialNumDao;
import ch.entity.sys.SystemSerialNumberEntity;
import ch.util.DateUtils;
import ch.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author cy
 * @date 2019年05月07日 13:23
 * version 1.0
 */
@Service
public class SerialNumberServiceImpl implements SerialNumService {

    @Autowired
    private SerialNumDao serialNumDao;

    /** 日期格式 */
    private String pattern = "yyyyMMddHHmmss";

    /** 生成器锁 */
    private final ReentrantLock lock = new ReentrantLock();

    /** 流水号格式化器 */
    private DecimalFormat format = new DecimalFormat("00");

    /** 预生成锁 */
    private final ReentrantLock prepareLock = new ReentrantLock();

    /** 最小值 */
    private int min = 0;

    /** 最大值 */
    private long max = 10;

    /** 已生成流水号（种子） */
    private long seed = min;

    /** 预生成数量 */
    private int prepare = 4;

    /** 数据库存储的当前最大序列号 **/
    long maxSerialInt = 0;

    /** 当前序列号是否为个位数自增的模式 **/
    private String isAutoIncrement = "0";

    /** 预生成流水号 */
    HashMap<String, List<String>> prepareSerialNumberMap = new HashMap<>();



    /**
     * 查询单条序列号配置信息
     * @param
     * @return
     */
    @Override
    public SystemSerialNumberEntity find(SystemSerialNumberEntity entity) {
        return null;
    }

    /**
     * 根据模块code生成预数量的序列号存放到Map中
     * @param moduleCode 模块code
     * @return
     */
    @Transactional
    public List<String> generatePrepareSerialNumbers(String moduleCode){
        //临时List变量
        List<String> resultList = new ArrayList<String>(prepare);
        lock.lock();
        // 查询数据库最大maxSerialInt
        SystemSerialNumberEntity oneByCode = serialNumDao.findOneByCode(moduleCode);
        if(oneByCode == null){
            throw new RuntimeException("不存在模块code");
        }
        this.maxSerialInt = Long.valueOf(oneByCode.getMaxSerial());
        this.prepare = Integer.valueOf(oneByCode.getPreMaxNum());
        try {
            for (int i = 0; i < prepare; i++) {
                maxSerialInt += 1;
                if(maxSerialInt > min && maxSerialInt < max){
                    seed = maxSerialInt;
                }else {
                    // 如果动态数字长度大于模板中的长度 例：模板CF000  maxSerialInt 1000
                    seed = maxSerialInt = 0;
                }
                // 动态数字生成拼接
                String formatSerialNum = format.format(seed);
                // 动态日期的生成拼接
                if(StringUtils.isNotBlank(pattern)){
                    String date = DateUtils.getDate(pattern);
                    formatSerialNum = date + formatSerialNum;
                }
                if(StringUtils.isNotBlank(oneByCode.getConfigTemplet())){
                    formatSerialNum = oneByCode.getConfigTemplet() + formatSerialNum;
                }
                resultList.add(formatSerialNum);
            }
            //更新数据
            oneByCode.setMaxSerial(maxSerialInt + "");
            serialNumDao.update(oneByCode);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return resultList;
    }

    /**
     * 根据模块code生成序列号
     * @param moduleCode  模块code
     * @return  序列号
     */
    @Override
    @Transactional
    public String generateSerialNumberByModelCode(String moduleCode) {
        //预序列号加锁
        prepareLock.lock();
        try{
            //判断内存中是否还有序列号
            if(null != prepareSerialNumberMap.get(moduleCode) && prepareSerialNumberMap.get(moduleCode).size() > 0){
                //若有，返回第一个，并删除
                return prepareSerialNumberMap.get(moduleCode).remove(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //预序列号解锁
            prepareLock.unlock();
        }
        List<String> resultList = generatePrepareSerialNumbers(moduleCode);
        prepareLock.lock();
        try {
            prepareSerialNumberMap.put(moduleCode, resultList);
            return prepareSerialNumberMap.get(moduleCode).remove(0);
        } finally {
            prepareLock.unlock();
        }
    }

}
