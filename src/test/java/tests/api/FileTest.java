package tests.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

import static java.lang.System.getProperty;



public class FileTest {

    private final Logger log = LogManager.getLogger();

    public void some(){
        File testFile = new File(getProperty("user.dir") + "/pom/xml");
        System.out.println(testFile.exists());
        try {
            testFile.createNewFile();
        }catch (IOException e){
            log.error(e);
        }
        System.out.println(testFile.exists());
    }
}
