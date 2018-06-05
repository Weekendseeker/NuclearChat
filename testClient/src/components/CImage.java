package components;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class CImage extends Content implements Serializable{
    private File file;

    public CImage(File file) throws IOException{
        this.file=file;
        String url=this.file.toURI().toURL().toString();
    }

    @Override
    public File getContent() {
        return this.file;
    }

    @Override
    Object getObject() {
        return this;
    }

}
