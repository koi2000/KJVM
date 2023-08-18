package classfile.attribute;

import classfile.ClassReader;
import classfile.ConstantPool;
import utils.ByteUtils;

/*
变长属性，只存在于method_info结构中
JVM中Code属性的定义
Code_attribute{
    u2 attribute_name_index;
    u4 attribute_length;
    u2 max_stack;
    u2 max_locals;
    u4 code_length;
    u1 code[code_length];
    u2 exception_table_length;
    {
        u2 start_pc;
        u2 end_pc;
        u2 catch_type;
    }exception_table[exception_table_length];
    u2 attributes_count;
    attribute_info attributes[attributes_count];
}
* */
public class CodeAttribute extends AttributeInfo{
    ConstantPool constantPool;
    // 操作数栈的最大深度
    int maxStack;
    // 局部变量表大小
    int maxLocals;
    // 字节码
    byte[] code;
    ExceptionTableEntry[] exceptionTable;

    AttributeInfo[] attributes;

    public CodeAttribute(ConstantPool constantPool){
        this.constantPool = constantPool;
    }

    @Override
    void readInfo(ClassReader reader) {
        maxStack = reader.readUint16();
        maxLocals = reader.readUint16();
        int codeLength = ByteUtils.byteToInt32(reader.readUint32());
        code = reader.readBytes(codeLength);
        attributes = readAttributes(reader,constantPool);
    }

    private ExceptionTableEntry[] readExceptionTable(ClassReader reader){
        int exceptionTableLength = reader.readUint16();
        ExceptionTableEntry[] exceptionTable = new ExceptionTableEntry[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptionTable[i] = new ExceptionTableEntry(reader);
        }
        return exceptionTable;
    }

    public LineNumberTableAttribute lineNumberTableAttribute(){
        for (int i = 0; i < attributes.length; i++) {
            if(attributes[i] instanceof LineNumberTableAttribute){
                return (LineNumberTableAttribute) attributes[i];
            }
        }
        return null;
    }


    // 异常表，包含指针，分别为
    public static class ExceptionTableEntry{
        // 可能排除异常的代码块的起始字节码（包括）
        int startPc;
        // 可能排除异常的代码块的终止字节码（不包括）
        int endPc;
        // 负责处理异常的 catch 块的起始位置
        int handlerPc;
        // 指向运行时常量池的一个索引，解析后可以得到一个异常类
        int catchType;
        // 改为传入一个reader的方法，比上面的构造方法更优雅一些
        public ExceptionTableEntry(ClassReader reader){
            this.startPc = reader.readUint16();
            this.endPc = reader.readUint16();
            this.handlerPc = reader.readUint16();
            this.catchType = reader.readUint16();
        }

        public int getStartPc() {
            return startPc;
        }

        public int getEndPc() {
            return endPc;
        }

        public int getHandlerPc() {
            return handlerPc;
        }

        public int getCatchType() {
            return catchType;
        }
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public ExceptionTableEntry[] getExceptionTable() {
        return exceptionTable;
    }
}
