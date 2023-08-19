package runtimedata.heap;


import runtimedata.Slots;

// 用来模拟Java中的Object类，这里只是简单的模拟定义一个类，用来盛放索引的
public class Zobject {
    private Zclass clazz;
    private Object data;
    public Object extra;

    public Zobject(Zclass clazz){
        this.clazz = clazz;
        data = new Slots(clazz.in)
    }
}
