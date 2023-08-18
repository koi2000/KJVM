package runtimedata;

import runtimedata.heap.Zmethod;

/*
定义Thread结构体，目前只定义了pc和stack两个字段，每个线程中都持有一个虚拟机栈的引用
Java虚拟机规范对Java虚拟机栈的约束相当的宽松，
虚拟机栈可以是连续的空间，也可以不连续
可以固定大小，也可以运行时动态扩展
如果Java虚拟机栈有大小限制，且执行线程所需要的栈空间超出了这个限制，会导致StackOverError异常抛出
如果Java虚拟机栈可以动态扩展，但是内存已经耗尽，会导致OutOfMemoryError
Java命令提供了-Xss选项来设置Java虚拟机栈大小
* */
public class Zthread {
    // 该PC也不是自己修改的，而是由外部传入供当前线程所持有的
    private int pc;
    // Stack结构体（Java虚拟机栈）的引用
    private Zstack stack;

    public Zthread(){
        stack = new Zstack(1024);
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void pushFrame(Zframe frame){
        stack.push(frame);
    }

    public Zframe popFrame() {
        return stack.pop();
    }

    public Zframe getCurrentFrame() {
        return stack.top();
    }

    public Zframe createFrame(int maxLocals, int maxStack) {
        return new Zframe(this, maxLocals, maxStack);
    }

    public Zframe createFrame(Zmethod method) {
        return new Zframe(this, method);
    }

    public boolean isStackEmpty() {
        return stack.size == 0;
    }

    public void clearStack() {
        while (!isStackEmpty()) {
            stack.pop();
        }
    }

    public Zframe[] getFrames() {
        return stack.getFrames();
    }
}
