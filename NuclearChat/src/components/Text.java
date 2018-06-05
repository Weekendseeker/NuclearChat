package components;


import java.io.File;
import java.io.Serializable;


public class Text extends Content implements Serializable {

    private String text="";

    private File file;

    public Text(String text) {
        this.text=text;
    }
    public Text(File file){
        this.file=file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String getContent() {
        return this.text;
    }

    @Override
    Object getObject() {
        return this;
    }

}
