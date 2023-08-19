package runtimedata.heap;

/**
 * @author koi
 * @date 2023/8/19 12:50
 */
/*
如何设法保证同一个对象的class==返回 true?如果不自己定义classloader，那么由系统提供的统一的
类加载器去加载class，可以保证同一个对象的class==返回true，因为加载后的class对象保存在方法区的hashMap中
key为类的全限定类名
* */
public class Zclass {
    private int accessFlags;
    public String thisClassName;
    public String superClassName;
    public String[] interfaceNames;
    private RuntimeConstantPool runtimeConstantPool;

}
