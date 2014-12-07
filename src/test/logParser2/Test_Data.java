package logParser2;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Test_Data {
    @Test
    public void testData(){
        List<String>listIn = new ArrayList<String>();
        Map<String, String> stringMap = new TreeMap<String, String>();
        Data data = new Data();

        for (int i = 0; i < 10; i++){
            listIn.add("Test" + i);
            stringMap.put("Key" + i,  "Value" + i);
        }

        data.setListRules(listIn);
        data.setListDataFromFile(listIn);
        data.putAllMapReplacement((TreeMap<String, String>) stringMap);

        //Test 1
        Assert.assertEquals(listIn, data.getListRules());
        //Test 2
        Assert.assertEquals(listIn, data.getListDataFromFile() );
        //Test 3
        Assert.assertEquals(stringMap, data.getMapReplacement());
    }
}
