package lang;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {

    @Test
    void split() {
        String string = "123";

        String[] actuals = string.split("");

        for(String each : actuals){
            System.out.println(each);
        }
    }

    @Test
    @DisplayName("matches는 문자열이 완전히 일치할 경우, true를 반환한다.")
    public void matches() {
        String string = "123";

        assertThat(string.matches("12")).isFalse();
    }

    @Test
    @DisplayName("substring은 부분 문자열을 반환한다.")
    public void substring() {
        String string = "Bearer hello world!";

        String actual = string.substring("Bearer ".length()); // 바로 다음 글자('h')부터 시작한다.

        assertThat(actual).isEqualTo("hello world!");
    }

    @Test
    @DisplayName("trim은 문자열의 시작과 끝 부분에 있는 공백 문자들을 제거한다. 문자열 중간의 공백은 제거하지 않는다.")
    void trim() {
        String string = "  1234 5678  ";

        String actual = string.trim();

        assertThat(actual).isEqualTo("1234 5678");
    }

    @Test
    void replaceAll() {
        String string = "(10,10)-(14,15)";

        String actual = string.replaceAll("[\\(\\-\\)]", "");

        System.out.println(actual);
    }
}
