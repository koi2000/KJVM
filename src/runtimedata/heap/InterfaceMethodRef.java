package runtimedata.heap;

import classfile.classconstant.ConstantIntegerInfo;
import classfile.classconstant.ConstantInterfaceMethodRefInfo;

/**
 * @author koi
 * @date 2023/8/19 21:02
 */
// 接口方法引用
public class InterfaceMethodRef extends MemberRef{
    Zmethod method;

    public InterfaceMethodRef(RuntimeConstantPool runtimeConstantPool, Cons
            tantInterfaceMethodRefInfo interfaceMethodRefInfo) {
        super(runtimeConstantPool);
        copyMemberRefInfo(interfaceMethodRefInfo);
    }

    //接口方法引用转直接引用
    public Zmethod resolvedInterfaceMethod() {
        if (method == null) {
            resolveInterfaceMethodRef();
        }
        return method;
    }

    private Zmethod lookupInterfaceMethod
}
