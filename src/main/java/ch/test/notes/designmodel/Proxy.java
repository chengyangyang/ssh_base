package ch.test.notes.designmodel;

/**
 * Description: 代理
 *
 * @author cy
 * @date 2019年05月10日 10:34
 * version 1.0
 */
public class Proxy{

    private Store store;
    private Boolean vip = true;

    public Proxy(Store store){
        this.store = store;
    }

    // 购买商品
    public String getGoods() {
        // 这里也可以做一些校验
        // 检查身份证
        return store.getGoods(this.vip);
    }
}
