package runtimedata;

/*
局部变量表按索引访问，将其视为一个数组
根据Java虚拟机规范，这个数组的每个元素至少可以容纳一个int或引用，两个连续的元素可以容纳一个long或者double
没有处理boolean，byte，short，和char类型，将其视为int
* */
public class LocalVars extends Slots {
    public LocalVars(int size) {
        super(size);
    }
}
