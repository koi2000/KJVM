package classfile.attribute;


import classfile.ClassReader;

/*
仅起标记作用，不包含任何数据，
可以出现在ClassFile, field_info和method_info结构中
属于布尔属性，只有存在和不存在的区别
* */
public class DeprecatedAttribute extends AttributeInfo{
    int attribute_name_index;
    int attribute_length;


    @Override
    void readInfo(ClassReader reader) {

    }
}
