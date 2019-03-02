package ch.test.notes.copy;

/**
 * Description:java 中拷贝
 *
 * @author cy
 * @date 2019年03月02日 12:19
 * version 1.0
 */
public class main {

    public static void main(String[] args) {
        Address address = new Address();
        address.setAddres("陕西省");
        Student student = new Student();
        student.setAge(1);
        student.setAddress(address);

        try {
            Student clone = (Student)student.clone();
            clone.getAddress().setAddres("上海");
            clone.setAge(2);
            System.out.println(student.getAge()+"==="+student.getAddress().getAddres());
            System.out.println(clone.getAge()+"==="+clone.getAddress().getAddres());

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }

}
