package ch.test.notes.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:一种方便的excel导出
 *
 * @author cy
 * @date 2018年11月16日 14:54
 * version 1.0
 */
public class main {

    public static void main(String[] args) throws Exception {
        //excel的导出
        /*List<MyEasypoi> list = new ArrayList<>();
        MyEasypoi myEasypoi = new MyEasypoi("1","苹果",12,12.12,new Date());
        MyEasypoi myEasypoi1 = new MyEasypoi("12","苹果1",121,12.112,new Date());
        MyEasypoi myEasypoi2 = new MyEasypoi("13","苹果2",124,12.122,new Date());
        list.add(myEasypoi);
        list.add(myEasypoi1);
        list.add(myEasypoi2);
        Workbook work = ExcelExportUtil.exportBigExcel(new ExportParams("我的测试", "person"), MyEasypoi.class, list);
        FileOutputStream out = new FileOutputStream(new File("C://a.xlsx"));
        work.write(out);
        work.close();
        out.close();*/

        //带集合的导出
       /* List<MyEasypoi> list1 = new ArrayList<>();
        MyEasypoi1 myEasypoi11 = new MyEasypoi1("黄色",12);
        MyEasypoi1 myEasypoi12 = new MyEasypoi1("绿色",11);
        List<MyEasypoi1> list11 = new ArrayList<>();
        list11.add(myEasypoi11);
        list11.add(myEasypoi12);

        MyEasypoi myEasypoi = new MyEasypoi("1","苹果",12,12.12,new Date());
        myEasypoi.setList(list11);
        MyEasypoi myEasypoi1 = new MyEasypoi("12","苹果1",121,12.112,new Date());
        myEasypoi1.setList(list11);
        MyEasypoi myEasypoi2 = new MyEasypoi("13","苹果2",124,12.122,new Date());
        list1.add(myEasypoi);
        list1.add(myEasypoi1);
        list1.add(myEasypoi2);
        Workbook work = ExcelExportUtil.exportBigExcel(new ExportParams("我的测试", "person"), MyEasypoi.class, list1);
        FileOutputStream out = new FileOutputStream(new File("C://a.xlsx"));
        work.write(out);
        work.close();
        out.close();*/

        //将上面的进行导入(需要无参构造方法)
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(2);//就是头部的截止行,自己可调节
        List<MyEasypoi> objects = ExcelImportUtil.importExcel(new File("C://a.xlsx"), MyEasypoi.class, importParams);


        System.out.println("结束了");

    }
}
