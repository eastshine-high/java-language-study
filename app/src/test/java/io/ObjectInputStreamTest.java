package io;

import org.junit.jupiter.api.Test;
import pure_java.domain.Student;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ObjectOutputStream은 주로 Serializable을 구현한 객체와 같이 사용한다.
 * Serializable은 인스턴스의 상태를 그대로 파일에 저장하거나 네트워크로 전송하고 이를 다시 복원하는 방식이다.
 * 직렬화는 인스턴스의 내용이 외부로 유출되는 것이므로 프로그래머가 해당 객체에 대한 직렬화 의도를 표시해야 한다(Serializable 구현이 이것을 의미).
 * 대부분의 자바 객체들은 Serializable이 구현되어 있다. ex. String 클래스
 * 직렬화를 직접 구현하고 싶을 경우 Externalizable을 구현한다.
 */
class School implements Serializable{
    private String name;
    private String location;
    private transient List<Student> students; // transient가 선언된 멤버는 무시하고 직렬화 한다. 예를 들어, 소켓과 같은 멤버들은 직렬화 할 수 없다

    public List<Student> getStudents() {
        return students;
    }

    public School(String name, String location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return Objects.equals(name, school.name) && Objects.equals(location, school.location) && Objects.equals(students, school.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, students);
    }
}

/**
 * ObjectInputStream은 ObjectOutputStream을 이용해 작성한 원시 데이터나 객체들을 역직렬화(deserialize)한다.
 * 보조 스트림.
 */
public class ObjectInputStreamTest{

    @Test
    void readObject() throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/test/java/io/filetest.txt"));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/test/java/io/filetest.txt"))){

            School highSchool = new School("삼성고등학교", "관악구");
            School elementarySchool = new School("삼성초등학교", "관악구");
            oos.writeObject(highSchool);
            oos.writeObject(elementarySchool);

            assertThat((School) ois.readObject())
                    .isEqualTo(highSchool)
                    .extracting(school -> school.getStudents()).isNull(); // transient를 선언한 멤버는 null이 채워진다.
            assertThat((School) ois.readObject())
                    .isEqualTo(elementarySchool);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * ObjectInputStream은 원시 자료형을 읽을 수도 있다. 내부적으로는 DataInputStream(원시 자료형의 입력을 담당)의 메소드를 사용한다.
     */
    @Test
    void readInt() throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/test/java/io/filetest.txt"));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/test/java/io/filetest.txt"))){

            oos.writeInt(350);
            // flush는 출력을 위해 잠시 자료가 머무르는 출력 버퍼를 강제로 비워 자료를 출력한다.
            // OutputStream은 내부적으로 작성한 스트림을 바로 출력하지 않는다.
            // 예를 들어 네트워크에 작성한 1바이트 마다 출력을 한다고 생각해보자. 이렇게 되면 비싼 네트워크를 굉장히 비효율적으로 사용하게 된다.
            oos.flush();

            assertThat(ois.readInt())
                    .isEqualTo(350);

            oos.close(); // close 메서드 내부에서도 flush()가 호출되므로 close메서드가 호출되면 출력 버퍼가 비워진다.
            ois.close();
        }
    }
}
