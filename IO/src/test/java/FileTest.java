import com.echo.io.file.EchoFileWriter;
import com.echo.io.file.FileWorker;
import org.junit.Test;

import java.io.*;

public class FileTest {

    @Test
    public void FileWorkerTest(){
        String path = "C:\\Users\\ddt\\Desktop\\helloIO.txt";
        try (FileInputStream input = new FileInputStream(path)){
            byte[] fileByte = input.readAllBytes();
            EchoFileWriter fileWriter = new EchoFileWriter();
            fileWriter.setFileByte(fileByte);
            fileWriter.setPath("C:\\Users\\ddt\\Desktop\\helloOUT.txt");

            FileWorker fileWorker = new FileWorker();
            fileWorker.write(fileWriter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
