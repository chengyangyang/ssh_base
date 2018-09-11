package ch.common.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时任务https://blog.csdn.net/lxxc11/article/details/78471089
 *
 */
public class QuotaCheckerTask {
    private static Logger logger = LoggerFactory.getLogger(QuotaCheckerTask.class);

    public void checkerTask(){
        try {

            logger.info("额度校验定时任务"+System.currentTimeMillis());

        }catch (Exception e){
            logger.error("额度校验定时任务失败:"+e.getMessage());
        }
    }
}
