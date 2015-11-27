
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

public class App {

    /**
     * Include your files here or scan by some code
     */
    public static String[] resources = new String[] { 
            "website/index.html", 
            "website/js/main.js" 
            };

    public static void main(String[] args) {
        String basePath = getExecutionPath(App.class);
        System.out.println("Base path:" + basePath);
        try {
            ClassLoader cl = App.class.getClassLoader();
            if (resources != null && resources.length > 0) {
                for (String path : resources) {
                    String desFile = basePath + File.separator + path.replace("/", File.separator);
                    System.out.println("Coping to: " + desFile);
                    File file = new File(desFile);
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    OutputStream os = new FileOutputStream(file);
                    IOUtils.copy(cl.getResourceAsStream(path), os);
                    os.flush();
                    IOUtils.closeQuietly(os);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getExecutionPath(Class<?> aclass) {
        String file = System.getProperty("java.class.path");
        if (file.indexOf(":") > 0) {
            file = file.substring(0, file.indexOf(":"));
        }
        File f = new File(file);
        File dir = f.getAbsoluteFile().getParentFile();
        String path = dir.toString();
        return path;
    }

}
