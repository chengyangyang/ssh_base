package ch.tool;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:将创建表的sql语句转化为javaBean
 *
 * @author cy
 * @date 2018年08月24日 10:00
 * version 1.0
 */
public class SqlToBean {

    public static void main(String[] args) {
      String sql = "CREATE TABLE    `trm_comPany`   (" +
              "  `id` varchar(32) NOT NULL," +
              "  `user_no` varchar(50) DEFAULT NULL COMMENT '用户账号'," +
              "  `company_name` varchar(100) DEFAULT NULL COMMENT '单位名称'," +
              "  `company_code` varchar(100) DEFAULT NULL COMMENT '统一社会信用代码'," +
              "  `link_name` varchar(50) DEFAULT NULL COMMENT '联系人名称'," +
              "  `link_phone` varchar(11) DEFAULT NULL COMMENT '联系人电话'," +
              "  `address` varchar(200) DEFAULT NULL COMMENT '通讯地址'," +
              "  `prove_file` text COMMENT '证明材料'," +
              "  `company_type` varchar(1) DEFAULT NULL COMMENT '用户类型，0：服务方；1：服务供应方'," +
              "  `applay_status` varchar(5) DEFAULT NULL COMMENT '认证状态'," +
              "  `applay_date` datetime DEFAULT NULL COMMENT '认证时间'," +
              "  `audit_date` datetime DEFAULT NULL COMMENT '审核时间'," +
              "  `audit_opinion` varchar(100) DEFAULT NULL COMMENT '审核意见'," +
              "  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人'," +
              "  `create_date` datetime DEFAULT NULL COMMENT '创建时间'," +
              "  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新人'," +
              "  `update_date` datetime DEFAULT NULL COMMENT '更新时间'," +
              "  `del_flag` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0'," +
              "  PRIMARY KEY (`id`)" +
              ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业信息  Yn';";
        Param param = new Param();
        new SqlToBean().sqlToBean(sql,param);


    }

        static class Param{//对头部进行添加信息
        private List<String> titileUpParam;//标题的添加
        private List<String> getMethodUpParam;//在get方法上面添加


    }

    public  void sqlToBean(String sql,Param param){
        //获得表名称
        String tableName = findTableNameFromSql(sql);
        //获得表注释
        String tableComment = findTableComment(sql);
        //获取表字段名称

        //获得表字段

        //获得注解




        System.out.println(tableComment);


    }

    //获得字段名称
    public List<String> findFilds(String sql){
        ArrayList<String> list = new ArrayList<>();
        if(sql != null && sql != ""){
            String[] split = sql.split(",");

        }
        return list;
    }


    public String findTableComment(String sql){
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
    public String findTableNameFromSql(String sql){
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


    /**
     * String格式转化
     * @param str
     * @param toLowerCase 是否转化为小写
     * @param FirstAlphabetCapital  是否首字母大写
     * @param hump  是否驼峰命名
     * @param remove_ 是否移除_
     * @return
     */
    public String stringFormat(String str,Boolean toLowerCase,Boolean hump,Boolean FirstAlphabetCapital,Boolean remove_){
        if(str != null){
            if(toLowerCase != null && toLowerCase){//是否转化为小写
                str = str.toLowerCase();
            }
            if(hump != null && hump){//是否驼峰命名
                String a = "";
                String[] split = str.split("_");
                for (int i = 0; i < split.length; i++) {
                   if(i == 0){
                       a += split[0];
                   }else {
                       String s = split[i];
                       String capitalize = capitalize(s);
                       a += capitalize;
                   }

                }
                str = a;
            }
            if(FirstAlphabetCapital != null && FirstAlphabetCapital){//是否首字母大写
                str = capitalize(str);
            }
            if(remove_ != null && remove_){// 是否移除下滑线
                str = str.replaceAll("_", "");
            }



        }
        return str;
    }

    //首字母大写
    public String capitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        char firstChar = str.charAt(0);
        if (Character.isTitleCase(firstChar)) {
            // already capitalized
            return str;
        }

        return new StringBuilder(strLen)
                .append(Character.toTitleCase(firstChar))
                .append(str.substring(1))
                .toString();
    }

}
