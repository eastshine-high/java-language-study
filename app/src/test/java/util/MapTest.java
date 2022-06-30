package util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Map은 연관된 키와 값을 저장한다. 연관된 키와 값을 새로 추가하거나 기존 키 값을 변경할 때는 put 메서드를 호출한다.
 * 키를 순서대로 방문해야 하는 경우가 아니라면 보통은 HashMap을 선택하는 것이 좋다.
 * 키를 정렬 순서로 방문하려면 TreeMap을 사용한다.
 *
 * 참조
 * Javadoc HashMap.
 * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashMap.html
 *
 * AssertJ - Map 자료형의 assertion 작성
 * https://github.com/assertj/assertj-examples/blob/main/assertions-examples/src/test/java/org/assertj/examples/MapAssertionsExamples.java
 */
class MapTest {

    /**
     * (메서드 요약)
     * 맵에 키가 있을 경우, 키의 값을 반환한다. 키가 없을 경우, defaultValue를 반환한다.
     *
     * get 메서드는 키가 없으면 null을 반환한다. null이 반환되면 값이 언박싱될 때 NullPointerException이 일어난다.
     * 이를 방지하기 위해 getOrDefault 메소드를 사용하면 좋다.
     */
    @Test
    void getOrDefault() {
        Map<String, Integer> map = new HashMap<>();

        int actual = map.getOrDefault("hello", 0);

        assertThat(actual).isEqualTo(0);
    }

    /**
     * 업데이트할 맵에 키가 아직 없으면 새로운 값(value)을 설정하고, 있으면 BiFunction<? super V, ? super V, ? extends V>에 전달된 함수를 수행한다.
     *
     * Map에 들어 있는 값을 업데이트할 때는 먼저 해당 키에 값이 있는지 검사한 후에 있으면 '기존 값'에 '새로운 값'을 더한다.
     * merge 메서드를 사용하면 이 작업을 더 간단히 수행할 수 있다.
     */
    @Test
    void merge() {
        Map<String, Integer> counts = new HashMap<>();
        String keyName = "keyName";

        counts.merge(keyName, 1, Integer::sum);

        assertThat(counts)
                .containsKeys(keyName)
                .containsEntry(keyName, 1)
                .doesNotContainEntry(keyName, 2);
    }

    @Test
    void loopingMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 10);
        map.put("Motorolla", 20);

        // classic java
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ", Stock : " + entry.getValue());
        }

        // modern java
        map.forEach((K,V) -> System.out.println(K + ", Stock : " + V));
    }
}

/**
 * Map의 여러 사용 방법
 *
 * map-to-string
 * https://www.techiedelight.com/convert-map-to-string-java/
 *
 * 맵 자료형을 list로
 * https://www.techiedelight.com/convert-map-array-java/
 */
