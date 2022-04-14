import com.echo.io.env.SystemProperty;
import com.echo.io.file.EchoFileWriter;
import com.echo.io.file.FileWorker;
import org.junit.Assert;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void fileCreate(){
        String home = System.getProperty("user.home");
        String path = home + SystemProperty.defaultSyncPath;

        File file = new File(path);

        if(!file.exists()){
            System.out.println(file.mkdirs());
        }

        Assert.assertTrue(file.exists());

    }
}
