package time;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * LocalTime은 시간을 표현한다.
 */
public class LocalTimeTest {

    @Test
    @DisplayName("팩토리 메서드로 생성 후, getter 메소드로 생성된 객체의 값을 확인")
    void testGottenValues() {
        LocalTime actual = LocalTime.of(13, 45, 20);

        assertThat(actual.toString()).isEqualTo("13:45:20");

        assertThat(actual.getHour()).isEqualTo(13);
        assertThat(actual.getMinute()).isEqualTo(45);
        assertThat(actual.getSecond()).isEqualTo(20);
    }

    @Test
    @DisplayName("초 단위 없이 생성했을 경우, 0을 반환한다.")
    void creationWithoutSecond() {
        LocalTime actual = LocalTime.of(14, 30);

        assertThat(actual.getSecond()).isEqualTo(0);
    }

    /**
     * compare 메소드는 인스턴스의 값과 아규먼트의 값을 비교한 결과를 반환한다.
     *
     * Return:
     *  인스턴스가 비교 값보다 크면 1을 반환한다.
     *  인스턴스가 비교 값보다 작으면 -1을 반환한다.
     *  인스턴스가 비교 값과 같으면 0을 반환한다.
     */
    @Test
    void compare() {
        LocalTime thirteenOClock = LocalTime.of(13, 45, 20);

        assertThat(thirteenOClock.compareTo(LocalTime.of(14, 45, 20)))
                .isEqualTo(-1);
        assertThat(thirteenOClock.compareTo(LocalTime.of(13, 45, 20)))
                .isEqualTo(0);
        assertThat(thirteenOClock.compareTo(LocalTime.of(12, 45, 20)))
                .isEqualTo(1);

    }

    /**
     * 인스턴스의 시간이 특정 시간보다 이후인지 확인한다.
     *
     * Returns: true, 특정 시간보다 이후일 경우
     */
    @Test
    void isAfter() {
        LocalTime thirteenOClock = LocalTime.of(13, 45, 20);

        assertThat(thirteenOClock.isAfter(LocalTime.of(12, 45, 20)))
                .isTrue();
        assertThat(thirteenOClock.isAfter(LocalTime.of(13, 45, 20)))
                .isFalse();
        assertThat(thirteenOClock.isAfter(LocalTime.of(14, 45, 20)))
                .isFalse();
    }

    /**
     * atDate 메서드에 날짜를 제공하여 LocalDateTime 객체를 생성할 수 있다.
     */
    @Test
    void atDate() {
        LocalTime time = LocalTime.of(13, 45, 20);

        assertThat(
                time.atDate(LocalDate.of(2022, 3, 28))
        )
                .isInstanceOf(LocalDateTime.class);
    }
}
