package classpath;

import java.io.File;
import java.io.IOException;
import java.time.Period;

/**
 * @author koi
 * @date 2023/7/22 11:30
 */
public abstract class Entry {
    // 路径分隔符，在window下，使用;分隔开，在linux下使用: 分割开
    public static final String pathListSeparator = System.getProperty("os.name").contains("Windows") ? ";" : ":";

    // 负责寻找和加载class文件
    // class文件的相对路径，路径之间用斜线 / 分隔，文件名有.class后缀
    abstract byte[] readClass(String className) throws IOException;

    // 返回className的字符串表示形式
    abstract String printClassName();

    // 工厂方法，根据传入的path的形式不同
    static Entry createEntry(String path) {
        if (path != null) {
            if (path.contains(pathListSeparator)) {
                return new CompositeEntry(path, pathListSeparator);
            } else if (path.contains("*")) {
                return new WildcardEntry("");
            } else if (path.contains(".jar") || path.contains(".JAR") || path.contains(".zip")
                    || path.contains("" + ".ZIP")) {
                return new ZipJarEntry(path);
            }
            return new DirEntry(path);
        } else {
            //如果命令行中没有显式的指定-cp选项,那么默认要找的class就在当前路径下
            File file = new File("");
            try {
                path = file.getCanonicalPath();
                return new DirEntry(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("illegal classpath format,or you should point out the classpath explicitly");
    }
}
