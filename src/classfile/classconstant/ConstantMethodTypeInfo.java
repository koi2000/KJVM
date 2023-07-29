package classfile.classconstant;

import classfile.ClassReader;

/**
 * @author koi
 * @date 2023/7/29 14:34
 */
public class ConstantMethodTypeInfo extends ConstantInfo{
    private int descriptorIndex;

    public ConstantMethodTypeInfo(int i) {
        type = i;
    }


    @Override
    void readInfo(ClassReader reader) {
        descriptorIndex = reader.readUint16();
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
