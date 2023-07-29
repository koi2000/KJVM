package classfile.classconstant;

import classfile.ClassReader;

/**
 * @author koi
 * @date 2023/7/29 18:02
 */
public class ConstantMethodHandleInfo extends ConstantInfo {
    //关于byte上界,自行处理;
    private byte referenceKind;
    private int referenceIndex;

    public ConstantMethodHandleInfo(int i) {
        type = i;
    }


    @Override
    void readInfo(ClassReader reader) {
        referenceKind = reader.readUint8();
        referenceIndex = reader.readUint16();
    }

    public int getReferenceKind() {
        return (referenceKind + 256) % 256;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }
}
