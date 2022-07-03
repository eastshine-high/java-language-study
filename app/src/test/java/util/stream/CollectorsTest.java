package util.stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pure_java.domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

/**
 * Collectors는 Collector의 구현체이다.
 * 엘러먼트들을 컬렉션에 합치거나 다양한 기준점을 가지고 요약하는 등의 다양한 리덕션 연산을 구현한다(리덕션 연산에 대한 설명은 StreamTest#reduce를 참고하자).
 * 다음은 리덕션을 수행하기 위한 collectors의 사용 예이다.
 */
public class CollectorsTest {
    List<Student> studentList;
    List<String> stringList;

    @BeforeEach
    void setUp() {
        studentList = new ArrayList<>();
        studentList.add(new Student("Alice", 72, 4));
        studentList.add(new Student("Alice", 72, 3));
        studentList.add(new Student("Bob", 82, 3));
        studentList.add(new Student("Chloe", 83, 2));
        studentList.add(new Student("Kim", 74, 2));
        studentList.add(new Student("Joe", 84, 3));

        stringList = new ArrayList<>();
        stringList.add("abcde");
        stringList.add("abcde");
        stringList.add("klmno");
        stringList.add("pqrst");
    }

    /**
     * 문자열을 연결할 수 있다.
     * 첫번째 파라미터(option)에 delimiter를 전달하면, 엘러먼트들 사이에 delimiter가 놓인다.
     */
    @Test
    void joining() {
        stringList.stream()
                .collect(Collectors.joining(","));

        assertThat("abcde,abcde,klmno,pqrst");
    }

    @Nested
    class groupingBy{

        /**
         * 스트림(T타입 엘러먼트들)에 "group by" 연산을 수행한 뒤 Map<K(classifier 함수의 반환타입), T 타입 엘러먼트들(각 키에 대응하는 스트림의 항목 리스트)>형태로 반환한다.
         * key 값은 정렬된다.
         *
         * params : classifier – 그룹화 함수는 입력된 엘러먼트들을 키들로 매핑한다.
         */
        @Test
        void groupingBy() {
            // 학년 별로 그룹화한다.
            Map<Integer, List<Student>> collect = studentList.stream()
                    .collect(
                            Collectors.groupingBy(Student::getGrade)
                    );

            assertThat(collect)
                    .isInstanceOf(Map.class)
                    .containsKeys(2,3,4)
                    .extractingByKey(2, as(LIST)).contains(new Student("Chloe", 83, 2));
        }

        /**
         * 두번째 파라미터(Optional)를 사용하면 그룹화된 스트림(T타입 엘러먼트들)에 리덕션 연산을 수행할 수 있다.
         */
        @Test
        void groupingAndReduction() {

            // 학년별로 그룹화하고 학년별 평균 점수를 구한다.
            Map<Integer, Double> actual = studentList.stream()
                    .collect(
                            Collectors.groupingBy(
                                    Student::getGrade,
                                    Collectors.averagingInt(Student::getScore))
                    );

            assertThat(actual).extractingByKey(2).isEqualTo(78.5);
        }
    }

    /**
     * Returns a Collector which partitions the input elements according to a Predicate, and organizes them into a Map<Boolean, List<T>>.
     * The returned Map always contains mappings for both false and true keys.
     */
    @Test
    void partition() {
        // 학생의 점수(80점)를 기준으로 pass fail을 나눈다.
         Map<Boolean, List<Student>> actual = studentList.stream()
                   .collect(
                           Collectors.partitioningBy(s -> s.getScore() >= 80)
                   );

        assertThat(actual)
                .extractingByKey(true, as(LIST)).contains(new Student("Joe", 84, 3));
        assertThat(actual)
                .extractingByKey(false, as(LIST)).contains(new Student("Alice", 72, 4));
    }
}

/** Other examples
 *
 * // Accumulate names into a List
 * List<String> list = people.stream()
 *   .map(Person::getName)
 *   .collect(Collectors.toList());
 *
 * // Accumulate names into a TreeSet
 * Set<String> set = people.stream()
 *   .map(Person::getName)
 *   .collect(Collectors.toCollection(TreeSet::new)); *
 *
 * // Compute sum of salaries of employee
 * int total = employees.stream()
 *   .collect(Collectors.summingInt(Employee::getSalary)); *
 *
 */
