package runtimedata.heap;

import java.util.HashMap;

/**
 * @author koi
 * @date 2023/8/20 18:41
 */
/*
这里用来模拟JVM中的字符串池，但是由于当前的KJVM本身就是用Java写的，所以省掉了很多细节
这里用一个HashMap来模拟字符串池，key为从class文件中读到的字符串，value为我们定义的zobject
* */
public class StringPool {
    public static HashMap<String, Zobject> internedStrings = new HashMap<>();
    public static HashMap<Zobject, String> realInternedStrings = new HashMap<>();

    public static Zobject jString(ZclassLoader loader, String str) {
        if (internedStrings.containsKey(str)) {
            return internedStrings.get(str);
        }
        char[] chars = str.toCharArray();
        Zobject jChars = new Zobject(loader.loadClass("[C"), chars, null);
        Zobject jStr = loader.loadClass("java/lang/String").newObject();
        jStr.setRefVar("value", "[C", jChars);
        internedStrings.put(str, jStr);
        //这一步的实现有些取巧了，Zobject 并没有实现 equals 和 hashCode 方法，但依然可以作为 key
        //是因为在 internedStrings 中的 key 是 java 中的String，这是合法的，相同的 String 取到的 value
        //也就是 Zobject ，也是一样的；这就保证了 Zobject 可以作为hashMap 的 key；
        realInternedStrings.put(jStr, str);
        return jStr;
    }

    // 凡是调用该方法，必定是从上面的常量池中获取了相同的字符串，然后返回其在JVM中的Zobject
    public static String realString(Zobject jStr) {
        if (realInternedStrings.containsKey(jStr)) {
            return realInternedStrings.get(jStr);
        }
        Zobject ref = jStr.getRefVar("value", "[C");
        char[] chars = ref.getChars();
        String realStr = new String(chars);
        realInternedStrings.put(jStr, realStr);
        return realStr;
    }
}
