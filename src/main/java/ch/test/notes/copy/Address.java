package ch.test.notes.copy;

/**
 * Description:
 *
 * @author cy
 * @date 2019年03月02日 13:32
 * version 1.0
 */
public class Address implements Cloneable {

    private String addres;

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
