package classfile.classconstant;

import classfile.ClassReader;
import utils.ByteUtils;

/**
 * @author koi
 * @date 2023/7/29 14:07
 */
public class ConstantLongInfo extends ConstantInfo {

    long val;

    public ConstantLongInfo(int i) {
        type = i;
    }


    @Override
    void readInfo(ClassReader reader) {
        byte[] data = reader.readUint64();
        val = ByteUtils.byteToLong64(data);
    }

    public long getVal() {
        return val;
    }
}
