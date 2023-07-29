package classfile.classconstant;

import classfile.ClassReader;
import utils.ByteUtils;

/**
 * @author koi
 * @date 2023/7/29 14:02
 */
/*
 *使用4字节存储整数常量
 * 实际上比int更小的boolean、byte、short和char类型的常量也放在 CONSTANT_Integer_info 中,也是存的四字节,这是为了4k对齐,可是也造成了字节的浪费;
 */
public class ConstantIntegerInfo extends ConstantInfo{
    int val;

    public ConstantIntegerInfo(int i){
        type = i;
    }

    @Override
    void readInfo(ClassReader reader) {
        byte[] data = reader.readUint32();
        val = ByteUtils.byteToInt32(data);
    }

    public int getVal() {
        return val;
    }
}
