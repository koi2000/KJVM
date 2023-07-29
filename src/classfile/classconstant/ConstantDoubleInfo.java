package classfile.classconstant;

import classfile.ClassReader;
import utils.ByteUtils;

/**
 * @author koi
 * @date 2023/7/29 14:06
 */
public class ConstantDoubleInfo extends ConstantInfo{
    double val;

    public ConstantDoubleInfo(int i) {
        type = i;
    }


    @Override
    void readInfo(ClassReader reader) {
        byte[] data = reader.readUint64();
        val = ByteUtils.byte2Double64(data);
    }

    public double getVal() {
        return val;
    }
}
