package ch.test.notes.copy;

/**
 * Description:
 *
 * @author cy
 * @date 2019年03月02日 12:25
 * version 1.0
 */
public class Student implements Cloneable {

    private Integer age = 1;
    private Address address;


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        Student st = (Student)clone;
        Address clone1 = (Address)st.getAddress().clone();
        st.setAddress(clone1);
        return super.clone();
    }
}
