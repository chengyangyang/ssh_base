package ch.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ZipUtils {
    private ZipUtils(){
    }

    public static void doCompress(String srcFile, String zipFile) throws IOException {
        doCompress(new File(srcFile), new File(zipFile));
    }

    /**
     * 文件压缩
     * @param srcFile 目录或者单个文件
     * @param zipFile 压缩后的ZIP文件
     */
    public static void doCompress(File srcFile, File zipFile) throws IOException {
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFile));
            doCompress(srcFile, out);
        } catch (Exception e) {
            throw e;
        } finally {
            out.close();//记得关闭资源
        }
    }

    public static void doCompress(String srcPath,File... files) throws IOException{
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(srcPath));
            for (int i = 0; i < files.length; i++) {
                ZipUtils.doZip(files[i], out, "");
            }
        }catch (Exception e){
            throw e;
        }finally {
            out.close();
        }

    }

    public static void doCompress(String filelName, ZipOutputStream out) throws IOException{
        doCompress(new File(filelName), out);
    }

    public static void doCompress(File file, ZipOutputStream out) throws IOException{
        doCompress(file, out, "");
    }

    public static void doCompress(File inFile, ZipOutputStream out, String dir) throws IOException {
        if ( inFile.isDirectory() ) {
            File[] files = inFile.listFiles();
            if (files!=null && files.length>0) {
                for (File file : files) {
                    String name = inFile.getName();
                    if (!"".equals(dir)) {
                        name = dir + "/" + name;
                    }
                    ZipUtils.doCompress(file, out, name);
                }
            }
        } else {
            ZipUtils.doZip(inFile, out, dir);
        }
    }



    public static void doZip(File inFile, ZipOutputStream out, String dir) throws IOException {
        String entryName = null;
        if (!"".equals(dir)) {
            entryName = dir + "/" + inFile.getName();
        } else {
            entryName = inFile.getName();
        }
        FileInputStream fis = new FileInputStream(inFile);
        ZipEntry entry = new ZipEntry(entryName);
        out.putNextEntry(entry);
        out.setEncoding("GBK");
        int len = 0 ;
        byte[] buffer = new byte[10*1024];
        while ((len = fis.read(buffer)) > 0) {
            out.write(buffer, 0, len);
            out.flush();
        }
        out.closeEntry();
        fis.close();
    }

    public static void main(String[] args) throws IOException {
        //doCompress("D:/java/", "D:/java.zip");
        //ZipOutputStream out = new ZipOutputStream(new FileOutputStream("D:/pdf/dome.zip"));
        doCompress("D:/pdf/dome.zip",new File[]{new File("D:/pdf/test.pdf"),new File("D:/pdf/test1.pdf")});
    }
}
