package classfile.attribute;

import classfile.ClassReader;

/*
LineNumberTable属性表存放方法的行号信息，和前面介绍的SourceFile属性都属于调试信息，都不是运行时必须
在使用javac编译字节码程序时，默认会在class文件中生成这些信息。
可以使用javac提供的-g:none选项来关闭这些信息的生成
描述Java源码行号与字节码行号之间的对应关系
* */
public class LineNumberTableAttribute extends AttributeInfo{
    LineNumberTableEntry[] lineNumberTable;

    @Override
    void readInfo(ClassReader reader) {
        int lineNumberTableLength = reader.readUint16();
        this.lineNumberTable = new LineNumberTableEntry[lineNumberTableLength];
        for (int i = 0; i < lineNumberTableLength; i++) {
            lineNumberTable[i] = new LineNumberTableEntry(reader.readUint16(),reader.readUint16());
        }
    }

    static class LineNumberTableEntry{
        // 字节码行号
        int startPc;
        // Java源码行号，二者执行的关联
        int lineNumber;

        public LineNumberTableEntry(int startPc, int lineNumber) {
            this.startPc = startPc;
            this.lineNumber = lineNumber;
        }
    }
}
