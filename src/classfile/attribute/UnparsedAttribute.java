package classfile.attribute;

import classfile.ClassReader;

// 没有实现的所有属性都放在这里
public class UnparsedAttribute extends AttributeInfo{
    private String attrName;
    private int attrLen;
    private byte[] info;

    public UnparsedAttribute(String attrName, int attrLen) {
        this.attrName = attrName;
        this.attrLen = attrLen;
    }


    @Override
    void readInfo(ClassReader reader) {
        info = reader.readBytes(attrLen);
    }
}
