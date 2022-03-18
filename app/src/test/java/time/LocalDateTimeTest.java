package time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * LocalDateTime은 LocalDate와 LocalTime을 쌍으로 갖는 복합 클래스다.
 * 즉, LocalDateTime은 날짜와 시간을 모두 표현할 수 있다.
 */
public class LocalDateTimeTest {

    /**
     * LocalDateTime을 생성하는 기본적인 방법은
     * 직접 만드는 방법과 날짜와 시간을 조합하는 방법이다.
     */
    @Test
    void construct() {
        assertThat(
                LocalDateTime.of(2021, Month.APRIL, 21, 13, 45, 20)
        )
                .isInstanceOf(LocalDateTime.class);

        LocalDate date = LocalDate.of(2022, 3, 28);
        LocalTime time = LocalTime.of(13, 45, 20);

        assertThat(
                LocalDateTime.of(date, time)
        )
                .isInstanceOf(LocalDateTime.class);
    }
}
