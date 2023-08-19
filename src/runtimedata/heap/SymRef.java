package runtimedata.heap;

import java.util.regex.Pattern;

/**
 * @author koi
 * @date 2023/8/19 20:11
 */
// 保存符号引用
public class SymRef {
    // 存放符号引用所在的运行时常量池指针，可以通过符号引用访问到运行时常量池，进一步又可以访问到类数据
    RuntimeConstantPool runtimeConstantPool;
    // 存放类的全限定类名
    String className;
    // 上述运行时常量池的宿主类中的符号引用的真正类,在外面访问时，根据 clazz 是否为 null 来决定是否执行 loadClass
    Zclass clazz;

    public SymRef(RuntimeConstantPool runtimeConstantPool){
        this.runtimeConstantPool = runtimeConstantPool;
    }

    // 类引用转直接引用
    public Zclass resolvedClass(){
        if (clazz==null) {
            resolvedClassRef();
        }
        return clazz;
    }

    // 当前类d中，如果引用了类c，那么就将c加载进来
    private void resolvedClassRef(){
        Zclass d = runtimeConstantPool.clazz;
        Zclass c = d.loader.loadClass(className);
        //在这里判断下 d 能否访问 c
        if (!c.isAccessibleTo(d)) {
            throw new IllegalAccessError(d.thisClassName + " can't access " + c.thisClassName);
        }
        clazz = c;
    }
}
