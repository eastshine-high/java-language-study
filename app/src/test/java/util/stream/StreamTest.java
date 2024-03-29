package util.stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pure_java.domain.Student;
import pure_java.domain.StudentDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamTest {
    List<Student> studentList;
    List<String> stringList;

    @BeforeEach
    void setUp() {
        studentList = new ArrayList<>();
        studentList.add(new Student("Alice", 1));
        studentList.add(new Student("Bob", 2));
        studentList.add(new Student("Chloe", 3));
        studentList.add(new Student("Bob", 4));

        stringList = new ArrayList<>();
        stringList.add("abcde");
        stringList.add("abcde");
        stringList.add("klmno");
        stringList.add("pqrst");
    }

    /**
     * map(중간 연산)
     * 스트림의 요소에 저장된 값 중에서 원하는 필드만 뽑아내거나 특정 형태로 변환해야 할 때 사용한다.
     */
    @Nested
    class map{

        @Test
        void 원하는_필드만_뽑아낸다() {
            List<String> names = studentList.stream()
                    .map(student -> student.getName()) // Student::getName
                    .collect(Collectors.toList());

            assertThat(names).anySatisfy(name -> {
                assertThat(name).isEqualTo("Chloe");
            });
        }

        @Test
        void 특정_형태로_변환한다() {
            List<String> substrings = stringList.stream()
                    .map(string -> string.substring(0, 2))
                    .collect(Collectors.toList());

            assertThat(substrings)
                    .containsExactly("ab", "ab", "kl", "pq");
        }

        @Test
        void 새로운_객체스트림을_생성한다() {
            List<Object> dtoList = studentList.stream()
                    .map(StudentDto::new)
                    .collect(Collectors.toList());

            assertThat(dtoList).allSatisfy(dto -> {
                assertThat(dto).isInstanceOf(StudentDto.class);
            });
        }
    }

    /**
     * (중간 연산)
     * 고유한 엘러먼트(중복된 엘러먼트 제거)로 구성된 스트림을 반환한다.
     *
     * distinct 외에 다른 중간 연산이 필요하지 않다면 최종 연산에서 Collectors.toSet()을 고려해도 좋을 것 같다.
     */
    @Test
    void distinct() {
        List<String> nameList = studentList.stream()
                .map(Student::getName)
                .distinct()
                .collect(Collectors.toList());

        assertThat(nameList.size())
                .isNotEqualTo(4)
                .isEqualTo(3);
    }

    /**
     * (최종 연산)
     * reduce 메서드는 스트림의 요소에 reduction연산을 수행한다.
     * reduction연산은 함수형 프로그래밍 언어 용어로는 이 과정이 마치 종이(우리의 스트림)를 작은 조각이 될 때까지 반복해서 접는 것과 비슷하다는 의미로 폴드(fold)라고 부른다.
     * reduce 메서드로 스트림의 모든 요소를 반복 조합하며 값을 도출할 수 잇다. 예를 들어 reduce로 스트림의 최댓값이나 모든 요소의 합계를 계산할 수 있다.
     */
    @Nested
    class reduce{

        /**
         * reduce 메서드는 스트림의 요소에 reduction연산을 수행한다.
         * 주어진 identity(초기값으로 볼 수 있다) 값과 연관 누적 함수를 이용하여 리듀스된 값을 반환한다.
         * 위 내용을 표현하면 다음과 같다.
         *     T result = identity;
         *     for (T element : this stream)
         *         result = accumulator.apply(result, element)
         *     return result;
         */
        @Test
        void reduceWithTwoArgumnets() {
            int actual = studentList.stream()
                    .map(Student::getScore)
                    .reduce(0, (a, b) -> a + b); // Integer::sum으로도 가능

            assertThat(actual)
                    .isEqualTo(10);
        }

        /**
         * identity(초기값)을 받지 않도록 오버로드된 reduce도 있다. 그러나 이 reduce는 Optional 객체를 반환한다.
         */
        @Test
        void reduceWithAnArgumnet() {
            Optional<Integer> actual = studentList.stream()
                    .map(Student::getScore)
                    .reduce((a, b) -> a < b ? b : a); // Integer::max로도 가능

            assertThat(actual.get()).isEqualTo(4);
            assertThat(actual).isInstanceOf(Optional.class);
        }
    }


    /**
     * (최종 연산)리덕션 연산의 특별한 형태이다. 다음 연산과 동등하다. return reduce(Integer::min);
     */
    @Test
    void min() {
         OptionalInt minX = studentList.stream()
                 .mapToInt(Student::getScore)
                 .min();

        assertThat(minX.getAsInt()).isEqualTo(1);
        assertThat(minX).isInstanceOf(OptionalInt.class);
    }
}
