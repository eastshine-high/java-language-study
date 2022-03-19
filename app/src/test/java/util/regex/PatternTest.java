package util.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Pattern은 정규 표현식의 컴파일된 표현이다.
 *
 * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/Pattern.html#DOTALL
 */
public class PatternTest {

    /**
     * matches 정적 메소드는 문자열이 정규 표현식을 준수하는지 확인한다.
     * 같은 정규식을 여러번 사용할 필요가 없을 때 사용한다.
     * 같은 정규식을 여러번 사용할 경우에는 컴파일하는 것이 더 효율적인다. 컴파일한 다음 각 입력에 사용할 Matcher를 생성한다.
     */
    @Test
    void matches() {
        String regex = "\\d\\d";
        String input = "30";

        boolean actual = Pattern.matches(regex, input);

        assertThat(actual).isTrue();
    }
}
