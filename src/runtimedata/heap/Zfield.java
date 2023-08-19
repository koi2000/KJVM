package runtimedata.heap;

import classfile.MemberInfo;
import classfile.attribute.ConstantValueAttribute;

/**
 * @author koi
 * @date 2023/8/19 14:24
 */
// 字段的抽象，是在class中定义的字段，包括静态的和非静态的
public class Zfield extends ClassMember{
    // 运行时常量池的索引，该属性只有在static，final成员有初值的情况下才有
    int constValueIndex;
    // 类中字段数组slots中的索引，其赋值在首次加载class文件后，为其分配的slotId
    // 如果是静态字段，该 slotId 表示的是在 Class 中staticVars数组中的索引
    // 如果是非静态字段，该 slotId 表示的是在 Object 中 fields 数组中的索引
    int slotId;

    private Zfield(Zclass clazz, MemberInfo classFileField){
        super(clazz,classFileField);
        copyAttribute(classFileField);
    }

    public static Zfield[] makeFields(Zclass zclass,MemberInfo[] cfFields){
        Zfield[] fields = new Zfield[cfFields.length];
        for (int i = 0; i < fields.length; i++) {
            Zfield field = new Zfield(zclass, cfFields[i]);
            fields[i] = field;
        }
        return fields;
    }

    private void copyAttribute(MemberInfo classFileField) {
        ConstantValueAttribute constantValueAttribute = classFileField.getConstantValueAttribute();
        if (constantValueAttribute != null) {
            constValueIndex = constantValueAttribute.getConstantValueIndex();
        }
    }

    public boolean isVolatile() {
        return 0 != (accessFlags & AccessFlag.ACC_VOLATILE);
    }

    public boolean isTransient() {
        return 0 != (accessFlags & AccessFlag.ACC_TRANSIENT);
    }

    public boolean isEnum() {
        return 0 != (accessFlags & AccessFlag.ACC_ENUM);
    }


    public int getConstValueIndex() {
        return constValueIndex;
    }

    public int getSlotId() {
        return slotId;
    }

    public boolean isLongOrDouble() {
        return getDescriptor().equals("J") || getDescriptor().equals("D");
    }
}
