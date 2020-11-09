package helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T deserialize(String strObject, Class<T> objectClass){
        try {
            return  mapper.readValue(strObject, objectClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can not deserialize object from string : "+strObject+" and cast it to "+objectClass.getName()+" class");
        }
    }

    public static <T> String serialize (T object){
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("can not serialize object to string");
        }
    }


}