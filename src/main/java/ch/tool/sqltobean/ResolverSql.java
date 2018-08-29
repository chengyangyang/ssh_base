package ch.tool.sqltobean;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:将创建表的sql语句转化为javaBean,该sql语句必须有主键的定义
 *
 * @author cy
 * @date 2018年08月24日 10:00
 * version 1.0
 */
public class ResolverSql {

    public  ResolverResult findFromSqlParam(String sql){
    	//获得表名称
        String tableName = findTableNameFromSql(sql);
        //获得表注释
        String tableComment = findTableComment(sql);
        //获得内容的字段
        ArrayList<FieldAndComment> filds = findFilds(sql);
    
    	ResolverResult result = new ResolverResult();
        result.setTableName(tableName);
        result.setTableComment(tableComment);
        result.setFieldAndCommentList(filds);
    	return result;
    }

    //获得sql内容中的字段名称,类型,注释
    private ArrayList<FieldAndComment> findFilds(String sql){
        ArrayList<FieldAndComment> list = new ArrayList<>();
        if(sql != null && sql != ""){
            //获得sql的内容语句
            int dex = sql.indexOf("(");
            String str = sql.substring(dex+1,sql.length());
            String[] split = str.split(",");
            for (int i = 0; i < split.length-1; i++) {
                //创建所需要的对象内容
                FieldAndComment fieldAndComment = new FieldAndComment();
                String s = split[i];
                //去除特定的符号
                String s1 = s.replaceAll("`", "");
                //用空格进行切割
                String[] split1 = s1.split(" ");//空格隔开
                //获得字段名称和类型
                ArrayList<String> strings = new ArrayList<>();
                for (int j = 0; j < split1.length; j++) {
                    if(!split1[j].trim().equals("")){//字段名称
                        strings.add(split1[j]);
                    }
                }
                fieldAndComment.setSqlFieldName(strings.get(0));//第一个字段是字段
                fieldAndComment.setSqlType(strings.get(1));//第二个字段是类型

                //获取字段的注释
                //转化为小写
                String s2 = s1.toLowerCase();
                //获得comment的下标
                int comment = s2.indexOf("comment");
                if(comment != -1){
                    //截取剩余的字符串
                    String substring1 = s1.substring(comment, s1.length());
                    //获取注释内容
                    String[] split2 = substring1.split("'");
                    String zj = split2[1];//获得注释的内容
                    //放入到类中
                    fieldAndComment.setComment(zj);
                }
                list.add(fieldAndComment);
            }
        }
        return list;
    }

    //获得表的注释
    private String findTableComment(String sql){
        //定义表明
        String tableCommentName = "";
        if(sql != null && sql != ""){
            String[] split = sql.split("\\)");
            String s = split[split.length - 1];

            String q = s.toLowerCase();
            int index = q.indexOf("comment");
            if(index != -1){
                String substring = s.substring(index, s.length());
                String[] split1 = substring.split("'");
                tableCommentName = split1[1];
            }

        }
        return tableCommentName;
    }

    /**
     * @param sql 获得原本的表名称
     * @return
     */
    private String findTableNameFromSql(String sql){
        //定义表明
        String tableName = "";
        //解析sql
        //解析数据库的头
        if(sql != null && sql != ""){
            String[] split = sql.split("\\(");
            //
            String t = split[0];//获得sql头部
            String[] t1 = t.split(" ");//以空格进行分割

            String s = t.toLowerCase();//转化为小写
            String[] s1 = s.split(" ");//以空格进行分割
            ok:
            //获得表的名称
            for (int i = 0; i < s1.length; i++) {
                if("table".equals(s1[i])){
                    for (int j = i+1; j < s1.length; j++){
                        if(!s1[j].trim().equals("")){
                            tableName =  t1[j];
                            break ok;
                        }
                    }
                }
            }
        }
        tableName = tableName.replaceAll("`", "");//去除不必要的字符
        return tableName;
    }


    
}
