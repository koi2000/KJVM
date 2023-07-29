package classfile.classconstant;

import classfile.ConstantPool;

// 字段符号引用
public class ConstantFieldRefInfo extends ConstantMemberRefInfo {
    public ConstantFieldRefInfo(ConstantPool constantPool, int type) {
        super(constantPool,type);
    }
}
