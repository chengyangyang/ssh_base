package ch.common.bean;

/**
 * Description:文件下载读取文件的bean
 *
 * @author cy
 * @date 2018年08月06日 18:10
 * version 1.0
 */
public class ProgressEntity {
    private long pBytesRead = 0L;   //到目前为止读取文件的比特数
    private long pContentLength = 0L;    //文件总大小
    private int pItems;                //目前正在读取第几个文件
    private int percent; // 完成比例
    private long startTime = System.currentTimeMillis(); //开始时间
    private long useTime = System.currentTimeMillis(); //已用时间
    

    public long getpBytesRead() {
        return pBytesRead;
    }
    public void setpBytesRead(long pBytesRead) {
        this.pBytesRead = pBytesRead;
    }
    public long getpContentLength() {
        return pContentLength;
    }
    public void setpContentLength(long pContentLength) {
        this.pContentLength = pContentLength;
    }
    public int getpItems() {
        return pItems;
    }
    public void setpItems(int pItems) {
        this.pItems = pItems;
    }
    public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getUseTime() {
		return useTime;
	}
	public void setUseTime(long useTime) {
		this.useTime = useTime;
	}
	@Override
    public String toString() {
        float tmp = (float)pBytesRead;
        float result = tmp/pContentLength*100;
        return "ProgressEntity [pBytesRead=" + pBytesRead + ", pContentLength="
                + pContentLength + ", percentage=" + result + "% , pItems=" + pItems + "]";
    }

}