package components;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RenderContent implements IRender {

    @Override
    public Node Render(Content content) {
        switch (content.getClass().getName()){
            case "components.Text"   :return textRender((Text)content.getObject());
            case "components.CImage" :return imageRender((CImage)content.getObject());
        }
        return null;
    }


    public Label textRender(Text text){
        Label label=new Label(text.getContent());
        label.setMaxWidth(250);
        label.setWrapText(true);
        return label;
    }


    public ImageView imageRender(CImage cImage){
        ImageView imageView =new ImageView();
        Image image;
        try {
            FileInputStream fileInputStream=new FileInputStream((File)cImage.getContent());
            image=new Image(fileInputStream);
            imageView.setFitWidth(image.getWidth()/3);
            imageView.setFitHeight(image.getHeight()/3);
            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imageView;
    }
}
