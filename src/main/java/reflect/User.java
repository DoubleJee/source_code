package reflect;

public class User {

    private int id;
    private String name;

    public User() {
        System.out.println("调用无参构造函数...");
    }

    public User(int id, String name) {
        System.out.println("调用有参构造函数..");
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    private Integer add(Integer num1,Integer num2){
        Integer sum = num1 + num2;
        System.out.println(sum);
        return sum;
    }
}
