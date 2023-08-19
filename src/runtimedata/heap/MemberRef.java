package runtimedata.heap;

import classfile.classconstant.ConstantMemberRefInfo;

/**
 * @author koi
 * @date 2023/8/19 20:26
 */
// 字段和方法的符号引用保存的相同信息，包含全限定名和描述符
// 字段和方法特有的属性。由其对应的字类来实现
public class MemberRef extends SymRef {
    protected String name;
    protected String descriptor;

    public MemberRef(RuntimeConstantPool runtimeConstantPool) {
        super(runtimeConstantPool);
    }

    void copyMemberRefInfo(ConstantMemberRefInfo refInfo) {
        className = refInfo.getClassName();
        name = refInfo.getName();
        descriptor = refInfo.getDescriptor();
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }
}
