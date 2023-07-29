package classfile.classconstant;

import classfile.ConstantPool;

/**
 * @author koi
 * @date 2023/7/29 18:03
 */
public class ConstantMethodRefInfo extends ConstantMemberRefInfo {
    public ConstantMethodRefInfo(ConstantPool constantPool, int type) {
        super(constantPool, type);
    }
}
