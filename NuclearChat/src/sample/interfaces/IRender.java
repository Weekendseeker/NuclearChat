package sample.interfaces;

import components.Content;
import javafx.scene.Node;

import java.io.IOException;

public interface IRender {
    public Node Render(Content content) throws IOException;

}
