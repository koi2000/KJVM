package runtimedata.heap;

/**
 * @author koi
 * @date 2023/8/19 13:33
 */
/*
运行时常量，区别于class文件中的常量
* */
public class RuntimeConstantInfo<T> {
    private T value;
    private int type;

    RuntimeConstantInfo(T value,int type){
        this.value = value;
        this.type = type;
    }

    public T getValue(){
        return value;
    }

    public int getType() {
        return type;
    }
}
