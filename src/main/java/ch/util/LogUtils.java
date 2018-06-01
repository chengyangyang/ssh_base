package ch.util;

import com.trm.constants.Exceptions;
import com.trm.constants.SystemConstants;
import com.trm.dao.basicbusiness.LogDao;
import com.trm.entity.business.TrmLog;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.sql.Timestamp;

public class LogUtils {
    private static LogDao logDao = SpringContextHolder.getBean(LogDao.class);
    /**
     * 保存日志
     */
    public static void saveLog(HttpServletRequest request, String title){
        saveLog(request, null, null, title);
    }

    /**
     * 保存日志
     */
    public static void saveLog(HttpServletRequest request, Object handler, Exception ex, String title){
        TrmLog log = new TrmLog();
        log.setTitle(title);
        log.setType(ex == null ? SystemConstants.TYPE_ACCESS : SystemConstants.TYPE_EXCEPTION);
        log.setRemoteAddr(StringUtils.getRemoteAddr(request));
        log.setUserAgent(request.getHeader("user-agent"));
        log.setRequestUri(request.getRequestURI());
        StringBuffer params = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                params.append(line);
            }
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        log.setParams(params.toString());
        log.setMethod(request.getMethod());
        log.setCreateDate(new Timestamp(Datetime.getLocalDate().getTime()));
        log.setCreateBy(SessionUtil.getUserNameFromSession());
        // 异步保存日志
        new SaveLogThread(log, handler, ex).start();
    }
    /*
     * 保存日志线程
	 */
    public static class SaveLogThread extends Thread{

        private TrmLog log;
        private Object handler;
        private Exception ex;

        public SaveLogThread(TrmLog log, Object handler, Exception ex){
            super(SaveLogThread.class.getSimpleName());
            this.log = log;
            this.handler = handler;
            this.ex = ex;
        }

        @Override
        public void run() {
            // 获取日志标题
            if (StringUtils.isBlank(log.getTitle())){
                log.setTitle(log.getMethod());
            }
            // 如果有异常，设置异常信息
            log.setException(Exceptions.getStackTraceAsString(ex));
            // 如果无标题并无异常日志，则不保存信息
            if (StringUtils.isBlank(log.getTitle()) && StringUtils.isBlank(log.getException())){
                return;
            }
            // 保存日志信息
            logDao.saveLog(log);
        }
    }

}
