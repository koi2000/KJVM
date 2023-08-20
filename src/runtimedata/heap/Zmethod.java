package runtimedata.heap;

import classfile.MemberInfo;
import classfile.attribute.CodeAttribute;
import classfile.attribute.ExceptionsAttribute;
import classfile.attribute.LineNumberTableAttribute;

import java.util.ArrayList;

// 方法的抽象，是在class中定义的方法，包括静态的和非静态的
public class Zmethod extends ClassMember{
    private int maxStack;
    private int maxLocals;
    // 如果没有code属性，取值为null，不过就算是空方法也有一个return 语句;
    private byte[] code;
    private ExceptionTable exceptionTable;
    private LineNumberTableAttribute lineNumberTable;
    // 由方法声明中显式throws显示的异常
    private ExceptionsAttribute exceptions;
    // 解析后的方法描述符
    private MethodDescriptor parsedDescriptor;
    // 方法所需的参数个数，对于非静态方法，至少是一个(this)
    private int argSlotCount;

    private Zmethod(Zclass clazz, MemberInfo classFileMethod){
        super(clazz,classFileMethod);
        copyAttribute(classFileMethod);
        parsedDescriptor = new MethodDescriptor(this.descriptor);
        argSlotCount = calcArgSlotCount(parsedDescriptor.getParameterTypes());
        if (isNative()){
            injectCodeAttribute(parsedDescriptor.getReturnType());
        }
    }

    // 该方法用来初始化成员变量，maxStack,maxLocals,code
    // 如果是native方法，没有任何code字节码
    private void copyAttribute(MemberInfo classFileMethod){
        CodeAttribute codeAttribute = classFileMethod.getCodeAttribute();
        if (codeAttribute!=null){
            maxStack = codeAttribute.getMaxStack();
            maxLocals = codeAttribute.getMaxLocals();
            code = codeAttribute.getCode();
            lineNumberTable = codeAttribute.lineNumberTableAttribute();
            // 这一步主要是将classFile中的异常处理表(符号引用)，转换为运行时的异常处理表(直接引用)
            exceptionTable = new ExceptionTable(codeAttribute.getExceptionTable(),
                    clazz.getRuntimeConstantPool());
        }
        exceptions = classFileMethod.getExceptionsAttribute();
    }

    private int calcArgSlotCount(ArrayList<String> args){
        int slotCount = 0;
        // 如果当前方法不是静态方法，那么其第一个参数为this引用
        if (!isStatic()){
            slotCount++;
        }
        for (String arg : args) {
            slotCount++;
            if ("J".equals(arg) || "D".equals(arg)) {
                slotCount++;
            }
        }
        return slotCount;
    }

    // JVM并没有规定如何实现和调用本地方法，这里依然使用JVM栈来执行本地方法
    //
}
