package ch.common.rebean;

import ch.common.listener.MyProgressListener;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:对上传文件的重写
 *
 * @author cy
 * @date 2018年08月06日 18:05
 * version 1.0
 */
public class CustomMultipartResolver extends CommonsMultipartResolver {

	@Autowired
    private MyProgressListener progressListener;

    public CustomMultipartResolver() {
    }

    public CustomMultipartResolver(ServletContext servletContext) {
        super(servletContext);
    }

    
    public void setFileUploadProgressListener(MyProgressListener progressListener){
        this.progressListener=progressListener;
    }

    @Override
    public MultipartParsingResult parseRequest(HttpServletRequest request)
            throws MultipartException {
        String encoding = determineEncoding(request);
        FileUpload fileUpload = prepareFileUpload(encoding);
        progressListener.setSession(request.getSession());
        fileUpload.setProgressListener(progressListener);
        try {
            List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
            return parseFileItems(fileItems, encoding);
        }
        catch (FileUploadBase.SizeLimitExceededException ex) {
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
        }
        catch (FileUploadException ex) {
            throw new MultipartException("Could not parse multipart servlet request", ex);
        }
    }


}
