package classfile.classconstant;

import classfile.ConstantPool;

/**
 * @author koi
 * @date 2023/7/29 18:01
 */
public class ConstantInterfaceMethodRefInfo extends ConstantMemberRefInfo {
    public ConstantInterfaceMethodRefInfo(ConstantPool constantPool, int type) {
        super(constantPool, type);
    }
}

