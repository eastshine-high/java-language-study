package time;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 정적 팩토리 메소드로 두 시간 객체 사이의 지속 시간을 만들 수 있다.
 * Duration 클래스는 초와 나노초로 시간 단위를 표현하므로 between 메서드에 LocalDate를 전달할 수 없다.
 */
public class DurationTest {

    @Nested
    @DisplayName("between 메소드는")
    class Describe_between{

        @Test
        @DisplayName("두 개의 LocalTime, 두 개의 LocalDateTime, 또는 두 개의 Instant로 Duration을 만들 수 있다.")
        void contextWithLocalTime() {
            Duration between = Duration.between(LocalTime.of(23, 25, 13), LocalDateTime.now());

            System.out.println(between);
        }

        @Test
        @DisplayName("LocalTime은 사람이 사용하도록, Instant는 기계가 사용하도록 만들어진 클래스로 두 인스턴스는 서로 혼합할 수 없다.")
        void contextWithFalseType() {
            assertThatThrownBy(
                    () -> Duration.between(LocalTime.of(23, 25, 13), Instant.now())
            )
                    .isInstanceOf(DateTimeException.class);
        }
    }

    // Duration에는 여러 팩토리 메서드가 있다.
    @Test
    void ofMinutes() {
        Duration minutes = Duration.ofMinutes(3);

        assertThat(minutes)
                .isEqualTo(Duration.of(3, ChronoUnit.MINUTES));
    }
}
