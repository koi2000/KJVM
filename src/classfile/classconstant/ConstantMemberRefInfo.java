package classfile.classconstant;

import classfile.ClassReader;
import classfile.ConstantPool;

/**
 * @author koi
 * @date 2023/7/29 15:53
 */
/*
 * CONSTANT_Fieldref_info表示字段符号引用
 * CONSTANT_Methodref_info表示普通（非接口）方法符号引用
 * CONSTANT_InterfaceMethodref_info表示接口方法符号引用
 * 这三种类型结构一样,所以给出统一的类结构;
 * 然后定义三个类继承这个超类;
 * class_index和name_and_type_index都是常量池索引，
 * 分别指向CONSTANT_Class_info和CONSTANT_NameAndType_info常量。
*/
/*
CONSTANT_Fieldref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}
 */
public class ConstantMemberRefInfo extends ConstantInfo{

    ConstantPool constantPool;
    int classIndex;
    int nameAndTypeIndex;

    // 构造方法是供外部调用的;
    public ConstantMemberRefInfo(ConstantPool constantPool, int type) {
        this.constantPool = constantPool;
        // 接口,方法,字段通用这一个类,所以在构造方法中传入 i 来区分不同的类型;
        this.type = type;
    }


    @Override
    void readInfo(ClassReader reader) {
        classIndex = reader.readUint16();
        nameAndTypeIndex = reader.readUint16();
    }
}
