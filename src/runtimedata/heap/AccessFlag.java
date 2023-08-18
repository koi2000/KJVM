package runtimedata.heap;

/*
访问标识，对于类，方法，字段都有自己的访问标识
这里进行统一的封装，以后类，方法，字段的访问标识都是从这个类取
* */
public class AccessFlag {
    // class field method
    public static final int ACC_PUBLIC = 0x0001;
    // field method
    public static final int ACC_PRIVATE = 0x0002;
    // field method
    public static final int ACC_PROTECTED = 0x0004;
    // field method
    public static final int ACC_STATIC = 0x0008;
    // class field method
    public static final int ACC_FINAL = 0x0010;
    // class
    public static final int ACC_SUPER = 0x0020;
    // method
    public static final int ACC_SYNCHRONIZED = 0x0020;
    // field
    public static final int ACC_VOLATILE = 0x0040;
    // method
    public static final int ACC_BRIDGE = 0x0040;
    // field
    public static final int ACC_TRANSIENT = 0x0080;
    // method
    public static final int ACC_VARARGS = 0x0080;
    // method
    public static final int ACC_NATIVE = 0x0100;
    // class
    public static final int ACC_INTERFACE = 0x0200;
    // class method
    public static final int ACC_ABSTRACT = 0x0400;
    // method
    public static final int ACC_STRICT = 0x0800;
    // class field method
    public static final int ACC_SYNTHETIC = 0x1000;
    // class
    public static final int ACC_ANNOTATION = 0x02000;
    // class field
    public static final int ACC_ENUM = 0x4000;
}
