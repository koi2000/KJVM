package classfile;

import classfile.attribute.AttributeInfo;
import classfile.attribute.CodeAttribute;
import classfile.attribute.ConstantValueAttribute;
import classfile.attribute.ExceptionsAttribute;

/*
字段表和方法表，共用该类，二者在虚拟机规范中的定义是相同的
里面包含的是类中所定义的成员变量/方法
字段/方法中可能还包含属性
静态和非静态的都包含
* */
/*
field_info {
    u2 access_flags;
    u2 name_index;
    u2 descriptor_index;
    u2 attributes_count;
    attribute_info attributes[attributes_count];
}
* */
public class MemberInfo {
    ConstantPool constantPool;
    int accessFlags;
    int nameIndex;
    int descriptorIndex;
    AttributeInfo[] attributes;

    public MemberInfo(ClassReader reader, ConstantPool constantPool) {
        this.constantPool = constantPool;
        accessFlags = reader.readUint16();
        nameIndex = reader.readUint16();
        descriptorIndex = reader.readUint16();
        attributes = AttributeInfo.readAttributes(reader, constantPool);
    }

    public static MemberInfo[] readMembers(ClassReader reader, ConstantPool constantPool) {
        int memberCount = reader.readUint16();
        MemberInfo[] members = new MemberInfo[memberCount];
        for (int i = 0; i < memberCount; i++) {
            members[i] = new MemberInfo(reader, constantPool);
        }
        return members;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public String getName(){
        return constantPool.getUtf8(nameIndex);
    }

    public String getDescriptor(){
        return constantPool.getUtf8(descriptorIndex);
    }

    public CodeAttribute getCodeAttribute(){
        for (AttributeInfo info : attributes) {
            if (info instanceof CodeAttribute){
                return (CodeAttribute) info;
            }
        }
        return null;
    }

    // 并非每个成员变量都有ConstantValueAttribute属性
    // 该属性只针对于static final 基础类型变量或者String 类型变量
    public ConstantValueAttribute getConstantValueAttribute(){
        for (AttributeInfo info : attributes) {
            if (info instanceof ConstantValueAttribute){
                return (ConstantValueAttribute) info;
            }
        }
        return null;
    }

    public ExceptionsAttribute getExceptionsAttribute(){
        for (int i = 0; i < attributes.length; i++) {
            if (attributes[i] instanceof ExceptionsAttribute) {
                return (ExceptionsAttribute) attributes[i];
            }
        }
        return null;
    }
}
