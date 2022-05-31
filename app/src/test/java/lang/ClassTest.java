package lang;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class 클래스의 인스턴스는 실행 중인 Java 애플리케이션의 클래스 및 인터페이스를 나타낸다.
 * <p>
 * 어떤 객체의 참조가 저장된 변수가 있는 상태에서 해당 객체 정보를 더 얻고 싶을 때, getClass 메서드는 Class 클래스의 객체를 돌려준다(Class 클래스는 공개 생성자를 가지고 있지 않다).
 * 이 객체를 이용하여 해당 객체의 정보를 더 얻을 수 있다.
 * <p>
 * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Class.html
 */
public class ClassTest {

    @Test
    void getName() {
        String obj = new String("1234");
        Class<?> objClass = obj.getClass();

        assertThat(objClass.getName()).isEqualTo("java.lang.String");
    }
}
