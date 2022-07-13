package io;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 파일 개념을 추상화한 클래스
 * 입출력 기능은 없고, 파일의 이름, 경로, 읽기 전용등의 속성을 알 수 있는 메소드들을 지원한다.
 */

/**
 * 자바에서는 File클래스를 통해서 파일과 디렉토리를 다룰 수 있도록 하고 있다. 그래서 File인스턴스는 파일일 수도 있고 디렉토리일 수도 있다.
 */
public class FileStudy {

    /**
     * 주어진 문자열을 이름으로 갖는 파일을 위한 File 인스턴스를 생성한다. 파일 뿐만 아니라 디렉토리도 같은 방법으로 다룬다.
     * fileName은 주로 경로(path)를 포함해서 지정해주지만, 파일 이름만 사용해도 되는 데 이 경우 프로그램이 실행되는 위치가 경로(path)로 간주된다.
     */
    @Test
    void File() {
        File file = new File("./src/test/java/io/filetest"); // 상대경로는 classpath이다.
        System.out.println("1234123411234");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
    }

    /**
     * File 생성자는 파일이 없을 경우 FileOutputStream과 달리 자동으로 파일을 생성해 주지 않는다.
     * 따라서 파일을 생성할 필요가 있을 경우, 파일 생성 메소드를 호출해야 한다.
     *
     * throw IOException (예, 경로가 잘못되었을 경우)
     */
    @Test
    void createNewFile() throws IOException{
        File file = new File("./src/test/java/io/filetest"); // 상대경로는 classpath이다.

        file.createNewFile();

        assertThat(file.exists()).isTrue();
    }
}
