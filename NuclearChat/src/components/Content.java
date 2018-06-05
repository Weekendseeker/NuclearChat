package components;

import java.io.Serializable;

public abstract class Content implements Serializable {

    /** Родительский класс контента который можно использовать внутри программы */

    static int id;

    public Content(){
        id++;
    }

    public static int getId() {
        return id;
    }

    abstract Object getContent();
    abstract Object getObject();

}
