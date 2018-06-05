package components;

import java.util.HashMap;
import java.util.Map;

public class Formats {

    private static Map<String,String[]> formats=new HashMap<String, String[]>();

    public static void init(){
        formats.put("CImage", new String[]{"jpg","png","tif"});
        formats.put("Text", new String[]{"doc","txt"});
        //formats.put("")
    }

    public static Map<String,String[]>getFormat(){
        return formats;
    }

    public Map<String, String[]> getFormats() {
        return formats;
    }

    public void addFormat( String content, String... name){
        formats.put(content,name);

    }
    public Formats(){

    }

}
