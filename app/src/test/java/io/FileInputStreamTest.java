package io;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 기반 스트림.
 */
public class FileInputStreamTest {

    @BeforeAll
    static void beforeAll() {
        try {
            OutputStream outputStream = new FileOutputStream("./src/test/java/io/filetest.txt");
            outputStream.write(65); // A
            outputStream.write(66); // B
            outputStream.write(67); // C

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 아규먼트 없이 read 메소드를 호출할 경우,
     * 1Byte 값을 읽는다. 읽을 수 있는 값의 범위는 0~255이다.
     * 더이상 값을 읽을 수 없는 경우 -1을 반환한다.
     */
    @Test
    void readWithoutArgument() {
        try(InputStream inputStream = new FileInputStream("./src/test/java/io/filetest.txt")){

            assertThat(inputStream.read()).isEqualTo(65);
            assertThat((char) inputStream.read()).isEqualTo('B');
            assertThat((char) inputStream.read()).isEqualTo('C');
            assertThat(inputStream.read()).isEqualTo(-1);

        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 아규먼트 값(N Bytes)만큼 읽는다.
     */
    @Test
    void readNBytes() {
        try(InputStream inputStream = new FileInputStream("./src/test/java/io/filetest.txt")){

            byte[] actuals = inputStream.readNBytes(3);

            assertThat(actuals.length).isEqualTo(3);
            assertThat(actuals[0]).isEqualTo((byte) 65);
            assertThat((char) actuals[1]).isEqualTo('B');
            assertThat((char) actuals[2]).isEqualTo('C');

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 파라미터에 버퍼(배열)를 전달하면 버퍼의 사이즈 만큼 읽을 수 있다.
     */
    @Test
    void readWithBuffer() {
        try (FileInputStream fis = new FileInputStream("./src/test/java/io/filetest.txt")){
            byte[] bs = new byte[2];

            assertThat(fis.read(bs)).isEqualTo(2); // 읽은 갯수만큼 반환한다.
            assertThat(bs[0]).isEqualTo((byte)65);
            assertThat((char)bs[1]).isEqualTo('B');

            assertThat(fis.read(bs)).isEqualTo(1);
            assertThat((char)bs[0]).isEqualTo('C');
            assertThat((char)bs[1]).isEqualTo('B'); // 기존에 읽은 값이 남아있다.

            // fis.read(bs, 1, 9) 버퍼에 오프셋을 입력하여 선택적으로 읽을 수 있다.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
