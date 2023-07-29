package classfile.classconstant;


import classfile.ClassReader;
import utils.ByteUtils;


public class ConstantFloatInfo extends ConstantInfo {
    float val;

    public ConstantFloatInfo(int i) {
        type = i;
    }

    @Override
    void readInfo(ClassReader reader) {
        byte[] data = reader.readUint32();
        val = ByteUtils.byte2Float32(data);
    }

    public float getVal() {
        return val;
    }
}
