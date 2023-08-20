package runtimedata.heap;

import classfile.MemberInfo;
import classfile.classconstant.ConstantFieldRefInfo;

/**
 * @author koi
 * @date 2023/8/19 20:25
 */
// 字段引用
public class FieldRef extends MemberRef {
    Zfield field;
    private Zclass c;

    public FieldRef(RuntimeConstantPool runtimeConstantPool, ConstantFieldRefInfo fieldRefInfo){
        super(runtimeConstantPool);
        copyMemberRefInfo(fieldRefInfo);
    }

    // 字段引用转为直接引用
    public Zfield resolveField(){
        if (field==null){
            resolveRefField();
        }
        return field;
    }

    public void resolveRefField(){
        Zclass d = runtimeConstantPool.clazz;
        // 获取fieldRef所在的类
        Zclass c = resolvedClass();
        // 在该类中找到对应的字段field
        Zfield field = lookupField(c, name, descriptor);
        if (field == null) {
            throw new NoSuchFieldError("NoSuchFieldError：" + name);
        }

        if (!field.isAccessTo(d)) {
            throw new IllegalAccessError(d.thisClassName + " can't access " + name + "in Class " + c.thisClassName);
        }

        this.field = field;
    }

    private Zfield lookupField(Zclass c,String name,String descriptor){
        for (Zfield zf : c.fields){
            if (zf.name.equals(name)&&zf.getDescriptor().equals(descriptor)){
                return zf;
            }
        }
        for (Zclass zin : c.interfaces) {
            return lookupField(zin, name, descriptor);
        }

        if (c.superClass != null) {
            return lookupField(c.superClass, name, descriptor);
        }

        return null;
    }
}
