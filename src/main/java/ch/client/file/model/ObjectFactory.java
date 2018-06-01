
package ch.client.file.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.trm.webservice.client.file.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UploadFileInnovate_QNAME = new QName("http://server.webservice.trm.com/", "uploadFileInnovate");
    private final static QName _UploadFileInnovateResponse_QNAME = new QName("http://server.webservice.trm.com/", "uploadFileInnovateResponse");
    private final static QName _UploadFileUeditor_QNAME = new QName("http://server.webservice.trm.com/", "uploadFileUeditor");
    private final static QName _UploadFileUeditorResponse_QNAME = new QName("http://server.webservice.trm.com/", "uploadFileUeditorResponse");
    private final static QName _IOException_QNAME = new QName("http://server.webservice.trm.com/", "IOException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.trm.webservice.client.file.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UploadFileInnovate }
     * 
     */
    public UploadFileInnovate createUploadFileInnovate() {
        return new UploadFileInnovate();
    }

    /**
     * Create an instance of {@link UploadFileInnovateResponse }
     * 
     */
    public UploadFileInnovateResponse createUploadFileInnovateResponse() {
        return new UploadFileInnovateResponse();
    }

    /**
     * Create an instance of {@link UploadFileUeditor }
     * 
     */
    public UploadFileUeditor createUploadFileUeditor() {
        return new UploadFileUeditor();
    }

    /**
     * Create an instance of {@link UploadFileUeditorResponse }
     * 
     */
    public UploadFileUeditorResponse createUploadFileUeditorResponse() {
        return new UploadFileUeditorResponse();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link FileEntity }
     * 
     */
    public FileEntity createFileEntity() {
        return new FileEntity();
    }

    /**
     * Create an instance of {@link UploadResponse }
     * 
     */
    public UploadResponse createUploadResponse() {
        return new UploadResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadFileInnovate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.trm.com/", name = "uploadFileInnovate")
    public JAXBElement<UploadFileInnovate> createUploadFileInnovate(UploadFileInnovate value) {
        return new JAXBElement<UploadFileInnovate>(_UploadFileInnovate_QNAME, UploadFileInnovate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadFileInnovateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.trm.com/", name = "uploadFileInnovateResponse")
    public JAXBElement<UploadFileInnovateResponse> createUploadFileInnovateResponse(UploadFileInnovateResponse value) {
        return new JAXBElement<UploadFileInnovateResponse>(_UploadFileInnovateResponse_QNAME, UploadFileInnovateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadFileUeditor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.trm.com/", name = "uploadFileUeditor")
    public JAXBElement<UploadFileUeditor> createUploadFileUeditor(UploadFileUeditor value) {
        return new JAXBElement<UploadFileUeditor>(_UploadFileUeditor_QNAME, UploadFileUeditor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadFileUeditorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.trm.com/", name = "uploadFileUeditorResponse")
    public JAXBElement<UploadFileUeditorResponse> createUploadFileUeditorResponse(UploadFileUeditorResponse value) {
        return new JAXBElement<UploadFileUeditorResponse>(_UploadFileUeditorResponse_QNAME, UploadFileUeditorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.trm.com/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

}
