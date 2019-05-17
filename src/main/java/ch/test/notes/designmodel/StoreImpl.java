package ch.test.notes.designmodel;

/**
 * Description:
 *
 * @author cy
 * @date 2019年05月10日 10:35
 * version 1.0
 */
public class StoreImpl implements Store {

    private int num = 10;

    @Override
    public String getGoods(boolean vip) {
        if(!vip){
            System.out.println("只有VIP才能购买商品！");
            return "只有VIP才能购买商品！";
        }
        num --;
        System.out.println("购买了货物，剩余数量为"+ num);
        return "购买了货物，剩余数量为"+ num;
    }
}
