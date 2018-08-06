package ch.test;

import ch.util.FileUtils;

import java.io.File;

/**
 * Description:
 *
 * @author cy
 * @date 2018年08月06日 15:19
 * version 1.0
 */
public class MyTest {

    private String makePath(String filename,String savePath){
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        //构造新的保存目录
        String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        //File既可以代表文件也可以代表目录
        File file1 = new File(dir);
        File file = file1;
        //如果目录不存在
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
    public static void main(String[] args) {

        System.out.println(FileUtils.reName("aaaa.zip"));
    }
}
