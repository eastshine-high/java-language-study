package io;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileOutputStreamStudy {

    /**
     * 지정된 파일에 출력하기 위한 아웃풋 스트림을 생성한다.
     *
     * Params: name - 파일 이름.
     * Params: append - false(기본값)일 경우, 파일을 덮어 쓴다.
     *
     * Throws: 1. 비정상적인 파일인 경우. 2. 파일을 생성할 수 없는 경우. 3. 파일을 열어볼 수 없을 경우.
     * FileInputStream와 달리 파일이 없을 경우 곧 바로 FileNotFoundException을 던지지는 않는다. 파일이 없을 경우 자동으로 생성한다.
     */
    @Test
    void FileOutputStream() throws FileNotFoundException {
        OutputStream outputStream = new FileOutputStream("./src/test/java/io/filetest.txt");
    }

    /**
     * 아웃풋 스트림에 바이트를 쓴다.
     */
    @Test
    void write() {
        try(FileOutputStream fos = new FileOutputStream("./src/test/java/io/filetest.txt")){ // try with resource(java7부터 사용 가능)
            // 변수를 밖에서 선언하고 try-resource하는 것은 java9 부터 가능하다.
            fos.write(65);  //A
            fos.write(66);  //B
            fos.write(67);  //C
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    // flush 한 바이트씩 바로 작성하지 않는다. 예를 들어 네트워크 출력에 1바이트 마다 작성한다고 생각해보자. 출력을 위해 잠시 자료가 머무르는 출력 버퍼를 강제로 비워 자료를 출력한다.
    // file보다는 소켓이나 네트워크에서 사용하는 경우가 있다.
    // 일정 정도 쌓이면
    //close 메서드 내부에서 flush()가 호출되므로(도 동시에 호출) close메서드가 호출되면 출력 버퍼가 비워진다.
}
