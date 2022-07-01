package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pure_java.domain.Student;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Optional은 선택형값을 캡슐화하는 클래스다.
 * Optional 클래스를 사용하면 모델의 의미를 명확히 할 수 있다. 예를 들어 기존에는 Car 객체에 null 참조가 할당될 수 있는데 이것이 올바른 값인지 아니면 잘못된 값인지 판단할 정보가 없었다.
 * Optional을 이용하면 이것이 선택형값이라는 것을 명확히할 수 있다.
 * 모든 null 참조를 Optional로 대치하는 것은 바람직하지 않다. Optional의 역할은 더 이해하기 쉬운 API를 설계하도록 돕는 것이다.
 */
public class OptionalTest {

    /**
     * of 메소드는 null이 아닌 값을 포함하는 Optional을 만든다.
     */
    @Test
    void of() {
        assertThat(
                Optional.of(new Student("eastshine", 100))
        )
                .isInstanceOf(Optional.class);

        assertThatThrownBy(
                () -> Optional.of(null)
        )
                .isInstanceOf(NullPointerException.class);
    }

    /**
     * ofNullalbe 메소드는 null값을 저장할 수 있는 Optional을 만들 수 있다.
     */
    @Test
    void ofNullalbe() {
        Optional optional = Optional.ofNullable(null);

        boolean actual = optional.isEmpty();

        assertThat(actual).isTrue();
    }

    /**
     * empty 메소드는 빈 Optional 객체를 만들 수 있다.
     */
    @Test
    void empty() {
        Optional optional = Optional.empty();

        assertThat(optional.isEmpty()).isTrue();
    }

    @Nested
    class get_메소드는{

        @DisplayName("빈 Optional이 주어질 경우, NoSuchElementException 예외를 발생한다.")
        @Test
        void context_with_empty_optional() {
            Optional optional = Optional.empty();

            assertThatThrownBy(
                    () -> optional.get()
            )
                    .isInstanceOf(NoSuchElementException.class);
        }

        @DisplayName("객체가 있는 Optional이 주어질 경우, 객체를 반환한다.")
        @Test
        void context_with_nonempty_optional() {
            Optional optional = Optional.of(new Student("eastshine", 100));

            Object actual = optional.get();

            assertThat(actual).isInstanceOf(Student.class);
        }
    }


    @Nested
    class orElse_메소드는{

        @DisplayName("객체가 있는 Optional이 주어질 경우, 객체를 반환한다.")
        @Test
        void context_with_nonempty_optional() {
            Optional optional = Optional.of(new Student("eastshine", 100));

            Object actual = optional.orElse(null);

            assertThat(actual).isInstanceOf(Student.class);
        }

        @DisplayName("빈 Optional이 주어질 경우, 아규먼트 값을 반환한다.")
        @Test
        void context_with_empty_optional(){
            Optional optional = Optional.empty();

            Object actual = optional.orElse(new Student("eastshine", 100));

            assertThat(actual).isInstanceOf(Student.class);
        }
    }

    @Nested
    class orElseThrow_메소드는{

        @DisplayName("객체가 있는 Optional이 주어질 경우, 객체를 반환한다.")
        @Test
        void context_with_nonempty_optional() throws Throwable {
            Optional optional = Optional.of(new Student("eastshine", 100));

            Object actual = optional.orElseThrow(() -> new IllegalArgumentException("nono"));

            assertThat(actual).isInstanceOf(Student.class);
        }

        @DisplayName("빈 Optional이 주어질 경우, 제공된 예외를 던진다.")
        @Test
        void context_with_empty_optional() throws Throwable {
            Optional optional = Optional.empty();

            assertThatThrownBy(() ->
                    optional.orElseThrow(() -> new IllegalArgumentException("nono"))
            ).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
