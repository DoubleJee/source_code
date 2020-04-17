package use.annotation.entity;

public class UserEntity {
    private Long id;
    private Long name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    private void initMethod(){
        System.out.println("bean初始化");
    }

    private void destroyMethod(){
        System.out.println("bean销毁");
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
