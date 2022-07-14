package io;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 보조 스트림.
 * 기반 InputStream은 1Byte의 값 밖에 읽지 못한다. 그런데 문자는 보통 2바이트 혹은 3바이트 단위이다.
 * 따라서 문자를 읽기 위해서 1Byte 단위로 읽어 들인 정보를 2바이트 혹은 3바이트의 문자로 바꾸는 보조 스트림이 필요하다. InputStreamReader은 그러한 역할을 한다.
 */
public class InputStreamReaderTest {

    /**
     * 파라미터가 없는 read 메소드는 문자 하나를 읽고 반환한다.
     * 읽을 수 있는 범위는 (0xFFFF=65535)이다.
     */
    @Test
    void readWithoutArguments() {
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("./src/test/java/io/filetest.txt"));
                InputStreamReader isr = new InputStreamReader(new FileInputStream("./src/test/java/io/filetest.txt"))) {

            osw.write("안녕요!");

            assertThat((char) isr.read()).isEqualTo('안');
            assertThat((char) isr.read()).isEqualTo('녕');
            assertThat(isr.read()).isEqualTo(50836);
            assertThat((char)isr.read()).isEqualTo('!');
            assertThat(isr.read()).isEqualTo(-1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
