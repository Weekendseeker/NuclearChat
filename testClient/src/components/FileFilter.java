package components;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class FileFilter {

    private Content content;

    public Content fileType(File file)  {
        Formats.init();

        String fileName = file.getName();
        String format = "";

        for (int i = fileName.length() - 1; i > 0; i--) {
            if (fileName.charAt(i) == '.') {
                for (int j = i + 1; j < fileName.length(); j++) {
                    char buff = fileName.charAt(j);
                    format += buff;
                }
            }
        }

        for (Map.Entry<String, String[]> formats : Formats.getFormat().entrySet()) {
            for (String currentFormat: formats.getValue()) {

                if (currentFormat.indexOf(format)==-1) continue;
                //System.out.print("Work!");
                try {
                    Class cont = Class.forName("components."+formats.getKey());
                    Constructor con= cont.getConstructor(File.class);

                    this.content=(Content)con.newInstance(file);
                    System.out.println(this.content);

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
            }
        }
        return this.content;
    }
}
