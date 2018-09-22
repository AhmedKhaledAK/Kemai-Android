package akopensource.kemai;

import android.text.TextUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class KListReader {

    private Map<String, Object> map;

    public KListReader() {
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void editInMap(Map<String, Object> map, String key){
        List<Object> list = Collections.singletonList(map.get(key));
        String listString = convertListToString(list);

        if (listString.charAt(0) != '[') {
            map.put(key, list);
        }

        this.map =  map;
    }

    private String convertListToString(List<Object> list) {
        return TextUtils.join(", ", list);
    }
}