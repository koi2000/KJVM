package runtimedata.heap;

import classfile.MemberInfo;

/**
 * @author koi
 * @date 2023/8/19 12:37
 */
/*
字段和方法都属于类的成员，他们都有一些相同的信息（访问标志，名字，描述符）
所以这里定义一个符类ClassMember用来存放字段和方法公共的部分
但是字段和方法不同的部分还需要分开处理：Zfield，Zmethod
* */
public class ClassMember {
    // 访问标识
    protected int accessFlags;
    // 字段，方法名称
    protected String name;
    // 字段，方法描述
    protected String descriptor;
    // 所属的类，这样可以通过字段或方法访问到它所属的类
    protected Zclass clazz;

    public ClassMember(Zclass clazz, MemberInfo classFileMemberInfo) {

    }

    // 从class文件的memberInfo中复制数据
    private void copyMemberInfo(MemberInfo memberInfo) {
        accessFlags = memberInfo.getAccessFlags();
        name = memberInfo.getName();
        descriptor = memberInfo.getDescriptor();
    }

    public boolean isAccessTo(Zclass d) {
        if (isPublic()) return true;
        if (isProtected()) {
            return d == clazz || d.isSubClassOf(clazz) || d.getPackageName().equal(clazz.getPackageName());
        }
        if (!isPrivate()) {
            return d.getPackageName().equals(clazz.getPackageName());
        }
        return d == clazz;
    }

    public boolean isPublic() {
        return 0 != (accessFlags & AccessFlag.ACC_PUBLIC);
    }

    public boolean isPrivate() {
        return 0 != (accessFlags & AccessFlag.ACC_PRIVATE);
    }

    public boolean isProtected() {
        return 0 != (accessFlags & AccessFlag.ACC_PROTECTED);
    }

    public boolean isStatic() {
        return 0 != (accessFlags & AccessFlag.ACC_STATIC);
    }

    public boolean isFinal() {
        return 0 != (accessFlags & AccessFlag.ACC_FINAL);
    }

    public boolean isSynthetic() {
        return 0 != (accessFlags & AccessFlag.ACC_SYNTHETIC);
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public Zclass getClazz() {
        return clazz;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setClazz(Zclass clazz) {
        this.clazz = clazz;
    }
}
