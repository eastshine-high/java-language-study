package util.regex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Matcher는 패턴을 해석하여 문자 시퀀스에 대해 일치 작업을 수행하는 엔진이다.
 *
 * matcher는 pattern의 matcher 메소드로 생성할 수 있다.
 * matcher는 3가지 매칭 연산(matches, lookingAt, find)을 수행할 수 있다.
 *
 * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/Matcher.html
 */
public class MatcherTest {

    /*
     * The matches method attempts to match the entire input sequence against the pattern.
     */
    @DisplayName("matches는 전체 입력 문자열이 정규 표현식과 일치해야 한다.")
    @Test
    void matches() {
        String inputSequence = "Hi David";
        Pattern pattern = Pattern.compile("David");
        Matcher matcher = pattern.matcher(inputSequence);

        assertThat(matcher.matches()).isFalse();
    }

    /*
     * The lookingAt method attempts to match the input sequence, starting at the beginning, against the pattern.
     */
    @Test
    @DisplayName("lookingAt은 입력 문자열이 정규식으로 시작하는 지 확인한다.")
    void lookingAt() {
        String inputSequence = "Hi David";
        Pattern pattern = Pattern.compile("David");
        Matcher matcher = pattern.matcher(inputSequence);

        assertThat(matcher.lookingAt()).isFalse();
    }

     /*
     * The find method scans the input sequence looking for the next subsequence that matches the pattern.
     */
    @DisplayName("find는 입력 문자열이 정규식과 일치하는 부분이 있는 지 확인한다.")
    @Test
    void find() {
        String inputSequence = "Hi David!";
        Pattern pattern = Pattern.compile("David");
        Matcher matcher = pattern.matcher(inputSequence);

        assertThat(matcher.find()).isTrue();
    }

    @DisplayName("group메소드를 이용하여 매칭 연산으로 캡쳐된 인자를 추출할 수 있다.")
    @Test
    void group() {
         Pattern pattern = Pattern.compile("\\(([0-9]{1,2}),([0-9]{1,2})\\)");
         Matcher matcher = pattern.matcher("(22,52)");
         matcher.find();

         assertThat(matcher.group(0)).isEqualTo(("(22,52)"));
         assertThat(matcher.group(1)).isEqualTo("22");
         assertThat(matcher.group(2)).isEqualTo("52");
    }
}
