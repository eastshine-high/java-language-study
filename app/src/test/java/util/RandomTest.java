package util;

import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomTest {

    /**
     * int형 난수를 생성하여 반환한다. 난수의 범위는 0<= pseudorandom < bound 이다.
     *
     * Params: bound - 상한, 반드시 양의 정수이어야 한다.
     * Returns: int형 난수, 난수의 범위는 0<= pseudorandom < bound 이다.
     * throws: IllegalArgumentException - 상한의 값이 양수가 아닐 경우.
     */
    @RepeatedTest(5)
    void nextInt() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        while(list.size() < 10){
            list.add( random.nextInt(10) );
        }

        assertThat(list).doesNotContain(10);
        assertThat(list.size() != new HashSet<>(list).size()); // 생성된 난수는 중복될 수 있다
    }
}
