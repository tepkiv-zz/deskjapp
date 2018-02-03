package fw.base;

import fw.ApplicationManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.logging.Logger;


public class TestBase {
    protected Logger log = Logger.getLogger("TEST");
    protected ApplicationManager app;

    @BeforeClass
    @Parameters({"configFile"})
    public void setUp(@Optional String configFile) throws Exception {
        if (configFile == null) {
            configFile = System.getProperty("configFile");
        }
        if (configFile == null) {
            configFile = System.getenv("configFile");
        }
        if (configFile == null) {
            configFile = "application.properties";
        }
        Properties properties = new Properties();
        properties.load(new FileReader(new File(configFile)));
        app = ApplicationManager.getInstance();
        app.setProperties(properties);

    }

    @AfterTest
    public void tearDown() {
        app.stop();
    }

}
