import com.echo.server.utils.FileUtils;
import org.junit.Test;

public class FileTest {


    @Test
    public void RandomFileAccessTest(){
        String path = "C:\\Users\\ddt\\Desktop\\helloIO.txt";
        byte[] out = FileUtils.getFile(path);
        System.out.println(out);


    }
}
