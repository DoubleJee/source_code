package annotation;

@TestAnnotation(id = 1,age = 1)
public class AnnotationTest {
    @TestAnnotation(id = 2,age = 12)
    private int id;
    @TestAnnotation(age = 33)
    public static void main(String[] args) {

    }
}
