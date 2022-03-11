package util.stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pure_java.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {
    List<Student> studentList;
    List<String> stringList;

    @BeforeEach
    void setUp() {
        studentList = new ArrayList<>();
        studentList.add(new Student("Alice", 1));
        studentList.add(new Student("Bob", 2));
        studentList.add(new Student("Chloe", 3));
        studentList.add(new Student("Dale", 4));

        stringList = new ArrayList<>();
        stringList.add("abcde");
        stringList.add("fghig");
        stringList.add("klmno");
        stringList.add("pqrst");
    }

    /**
     * map(중간 연산)
     * 스트림의 요소에 저장된 값 중에서 원하는 필드만 뽑아내거나 특정 형태로 변환해야 할 때 사용한다.
     */
    class map{

        @Test
        void 원하는_필드만_뽑아낸다() {
            studentList.stream()
                    .map(student -> student.getName())
                    .forEach(System.out::println);
        }

        @Test
        void 특정_형태로_변환한다() {
            stringList.stream()
                    .map(string -> string.substring(0, 2))
                    .forEach(System.out::println);
        }
    }
}

