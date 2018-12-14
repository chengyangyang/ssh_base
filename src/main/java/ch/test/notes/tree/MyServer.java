package ch.test.notes.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author cy
 * @date 2018年12月11日 17:17
 * version 1.0
 */
public class MyServer {
    private String id;
    private String name;
    private String code;
    private String parentCode;
    private String sort;
    private List<MyServer> list1 = new ArrayList<>();

    public static  List<MyServer> list = new ArrayList<>();

    static {
        list.add(new MyServer("1","一级菜单1","1","0","1"));
        list.add(new MyServer("2","一级菜单2","2","0","2"));
        list.add(new MyServer("3","一级菜单3","3","0","3"));

        list.add(new MyServer("9","三级菜单1","9","4","1"));
        list.add(new MyServer("10","三级菜单2","10","4","2"));

        list.add(new MyServer("4","二级菜单1","4","1","1"));
        list.add(new MyServer("5","二级菜单2","5","1","2"));
        list.add(new MyServer("6","二级菜单3","6","1","3"));

        list.add(new MyServer("7","二级菜单1","7","2","1"));
        list.add(new MyServer("8","二级菜单2","8","2","2"));

    }

    public MyServer() {
    }

    public MyServer(String id, String name, String code, String parentCode, String sort) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.parentCode = parentCode;
        this.sort = sort;
    }

    public List<MyServer> getList1() {
        return list1;
    }

    public void setList1(List<MyServer> list1) {
        this.list1 = list1;
    }

    public List<MyServer> getList() {
        return list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
