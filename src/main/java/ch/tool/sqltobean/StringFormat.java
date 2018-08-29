package ch.tool.sqltobean;

public class StringFormat {
	
	/**
     * String格式转化
     * @param str
     * @return
     */
    public String stringFormat(String str,StringFormatConfig config){
        if(str != null){
            if(config.getToLowerCase() != null && config.getToLowerCase()){//是否转化为小写
                str = str.toLowerCase();
            }
            if(config.getHump() != null && config.getHump()){//是否驼峰命名
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
            if(config.getCapital() != null && config.getCapital()){//是否首字母大写
                str = capitalize(str);
            }
            if(config.getRemove_() != null && config.getRemove_()){// 是否移除下滑线
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
