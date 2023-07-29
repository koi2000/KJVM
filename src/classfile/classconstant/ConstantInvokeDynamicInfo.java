package classfile.classconstant;

import classfile.ClassReader;

/**
 * @author koi
 * @date 2023/7/29 18:02
 */
public class ConstantInvokeDynamicInfo extends ConstantInfo {
    int bootstrapMethodAttrIndex;
    int nameAndTypeIndex;

    public ConstantInvokeDynamicInfo(int i) {
        type = i;
    }

    @Override
    void readInfo(ClassReader reader) {
        bootstrapMethodAttrIndex = reader.readUint16();
        nameAndTypeIndex = reader.readUint16();
    }
}
