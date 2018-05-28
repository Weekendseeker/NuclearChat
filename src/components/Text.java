package components;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.Serializable;


public class Text extends Content implements Serializable {

    private String text="";
    private File file;
    private Label label;

    private VBox vbox;

    public Text(String text) {
        this.text=text;
//        label=new Label();
//        label.setWrapText(true);
//        label.setMaxWidth(250);
//        label.setText(text);
    }
    public Text(File file){
        this.file=file;
    }

    public File getFile() {
        return file;
    }

    public String getContent() {
        return this.text;
    }
    @Override
    public Object getObject() {
        return this;
    }
}
