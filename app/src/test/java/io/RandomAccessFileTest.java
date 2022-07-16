package io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * 적은 양의 데이터를 읽거나 쓸 때 사용하면 좋다.
 */
public class RandomAccessFileTest {

    /**
     * 지정한 파일에서 읽거나 쓸 수 있는 랜덤 스트림을 만든다.
     * A new FileDescriptor object is created to represent the connection to the file.
     *
     * 생성자의 두번째 인자인 mode에는 "r", "rw", "rws", or "rwd"가 올 수 있다.
     * rws와 rwd는 기본적으로 rw와 같은데, 출력 내용이 파일에 지연 없이 바로 쓰이게 한다.
     * rwd는 파일내용만, rws는 파일의 메타정보도 포함한다.
     *
     * @throws FileNotFoundException 파일을 찾을 수 없는 경우.
     */
    @Test
    void constructor() throws FileNotFoundException {
        RandomAccessFile rf = new RandomAccessFile("./src/test/java/io/filetest", "rw");
    }


    @DisplayName("RandomAccessFile의 종합적인 사용")
    @TestFactory
    Stream<DynamicTest> exampleDynamicTest() throws FileNotFoundException {
        RandomAccessFile rf = new RandomAccessFile("./src/test/java/io/filetest", "rw");
        int intValue = 100;
        double doubleValue = 3.14;
        String stringValue = "안녕하세요";

        return Stream.of(
                dynamicTest("자료형의 크기가 4Byte인 정수 하나를 입력한다.", () -> {
                    rf.writeInt(intValue);

                    assertThat(rf.getFilePointer()).isEqualTo(4); // 파일 포인터 위치
                }),

                dynamicTest("자료형의 크기가 8Byte인 실수 하나를 입력한다.", () -> {
                    rf.writeDouble(doubleValue);

                    assertThat(rf.getFilePointer()).isEqualTo(12);
                }),

                dynamicTest("한글 문자를 입력한다.", () -> {
                    // writeUTF는 인코딩에 modified-utf-8을 사용한다.
                    rf.writeUTF(stringValue);

                    // 따라서 한글 3Byte * 5 + 2Byte(Null charicter) = 17Byte이다.
                    assertThat(rf.getFilePointer())
                            .isEqualTo(29);
                }),

                // 파일포인터가 끝지점에서 읽으면 파일 포인터 Exception이 나온다.
                dynamicTest("파일 포인터의 위치를 옮긴다.", () -> {
                    long pointerPosition = 0;
                    rf.seek(pointerPosition);

                    assertThat(rf.getFilePointer())
                            .isEqualTo(pointerPosition);
                }),

                dynamicTest("입력했던 값을 읽는다.", () -> {
                    int i = rf.readInt();
                    double d = rf.readDouble();
                    String str = rf.readUTF();

                    assertThat(rf.getFilePointer()).isEqualTo(29);
                    assertThat(i).isEqualTo(intValue);
                    assertThat(d).isEqualTo(doubleValue);
                    assertThat(str).isEqualTo(stringValue);
                })
        );
    }
}
