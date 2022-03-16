package time;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * LocalDate는 시간을 제외한 날짜를 표현하는 불변 객체다.
 * Ref. https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/LocalDate.html
 */
public class LocalDateTest {

    @Test
    @DisplayName("LocalDate의 getter 메소드로 가져온 값을 확인")
    void testGottenValues() {
        LocalDate actual = LocalDate.of(2022, 3, 28);

        assertThat(actual.toString()).isEqualTo("2022-03-28");

        assertThat(actual.getYear()).isEqualTo(2022);
        assertThat(actual.getMonth()).isEqualTo(Month.MARCH);
        assertThat(actual.getDayOfMonth()).isEqualTo(28);
        assertThat(actual.getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
        assertThat(actual.lengthOfMonth()).isEqualTo(31);
        assertThat(actual.isLeapYear()).isEqualTo(false); // 윤년 여부
    }

    /**
     * atTime 메서드에 시간을 제공하여 LocalDateTime 객체를 생성할 수 있다.
     */
    @Test
    void atTime() {
        LocalDate date = LocalDate.of(2022, 3, 28);

        assertThat(date.atTime(13, 45, 20))
                .isInstanceOf(LocalDateTime.class);

        assertThat(date.atTime(LocalTime.of(13, 45, 20)))
                .isInstanceOf(LocalDateTime.class);
    }
}
