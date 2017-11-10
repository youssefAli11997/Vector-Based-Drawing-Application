package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.util.ArrayList;
import java.util.Map;

public class JoeSONParser {


    public String parseArrayOfMapsIntoJSON(ArrayList<Map<String,Double>> arrayOfMap){
        StringBuilder parsedArray = new StringBuilder();
        int i = 0;
        parsedArray.append("{\n");
        for(Map<String,Double> map : arrayOfMap){
            parsedArray.append("\"" + "Shape" +"\" :\n{\n");
            for(Map.Entry entry : map.entrySet()){
                parsedArray.append("\"" + entry.getKey() +"\" : \"" + entry.getValue() + "\",");
            }
        }
        parsedArray.append("}\n");
        return parsedArray.toString();
    }



}
