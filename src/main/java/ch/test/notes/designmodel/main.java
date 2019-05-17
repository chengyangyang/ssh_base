package ch.test.notes.designmodel;

/**
 * Description: // 设计模式
 *
 * @author cy
 * @date 2019年05月10日 9:16
 * version 1.0
 */
public class main {

    public static void main(String[] args) {
        StoreImpl store = new StoreImpl();
        store.getGoods(false);
        new Proxy(store).getGoods();
    }

}
