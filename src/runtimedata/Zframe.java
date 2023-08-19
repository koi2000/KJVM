package runtimedata;

import runtimedata.heap.Zmethod;

/*
栈帧，执行方法所需的局部变量表大小和操作数栈深度是由编译器预先计算好的，
存储在class文件method_info结构的Code属性中
* */
public class Zframe {
    // 当前帧的前一帧的引用，相当于单向链表的前一个指针
    Zframe lower;
    // 局部变量表的引用
    LocalVars localVars;
    // 操作数栈的引用
    OperandStack operandStack;
    // 当前帧所在的线程
    Zthread thread;
    Zmethod method;
    // frame中并不改变PC的值，其PC值是由ByteReader读取字节码不断改变的
    int nextPC;

    public Zframe(Zthread thread,int maxLocals,int maxStack){
        this.thread = thread;
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }

    public Zframe(Zthread thread, Zmethod method) {
        this.thread = thread;
        this.method = method;
        localVars = new LocalVars(method.getMaxLocals());
        operandStack = new OperandStack(method.getMaxStack());
    }

    public LocalVars getLocalVars(){
        return localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public Zthread getThread() {
        return thread;
    }

    public Zmethod getMethod() {
        return method;
    }

    public int getNextPC() {
        return nextPC;
    }

    public void setNextPC(int nextPC){
        this.nextPC = nextPC;
    }

    public void setMethod(Zmethod method) {
        this.method = method;
    }

    // 在用new,getStatic,invokeStatic,等指令中，判断clinit方法是否执行
    // 如果执行，则需要保存当前thread的pc
    // eg: 当前执行的是new指令，那么thread的pc指向的是new
    // 再push一个新栈去执行<clinit>
    // 等<clinit>直接结束后，在回到当前 frame，拿到 pc，此时的 pc 指向的还是 new
    // 重新执行一遍 new
    public void revertNextPC(){
        this.nextPC = thread.getPc();
    }
}
