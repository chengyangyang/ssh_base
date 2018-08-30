package ch.common.listener;

import ch.common.bean.ProgressEntity;
import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Description:文件上传的监听器
 *
 * @author cy
 * @date 2018年08月06日 17:59
 * version 1.0
 */

@Component
public class MyProgressListener implements ProgressListener {

    private HttpSession session;

    public void setSession(HttpSession session){
        this.session=session;
        ProgressEntity status = new ProgressEntity();
        session.setAttribute("status", status);
    }

    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
        ProgressEntity status = (ProgressEntity) session.getAttribute("status");
        status.setpBytesRead(pBytesRead);
        status.setpContentLength(pContentLength);
        status.setpItems(pItems);
        status.setUseTime(System.currentTimeMillis()-status.getStartTime());
        status.setPercent((int)(pBytesRead*100/pContentLength));
        //完成的计算已完成pBytesRead/1024/1024 M
    }
}
