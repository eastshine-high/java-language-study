package pure_java.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Random.html
 *
 */
public class RandomUtil {
    public static final int NUMBERS_SIZE = 3;
    public static final int BOUND = 10;

    /**
     * 0에서 9까지 서로 다른 임의의 수 3개를 생성하여 반환한다.
     *
     * @return 0에서 9까지 서로 다른 임의의 수 3개.
     */
    public Set<Integer> generateThreeDifferentNumbers() {
        Set<Integer> nubmers = new HashSet<>();
        Random random = new Random();

        while(nubmers.size() < NUMBERS_SIZE){
            nubmers.add(random.nextInt(BOUND));
        }

        return nubmers;
    }
}
