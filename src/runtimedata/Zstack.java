package runtimedata;

import java.util.EmptyStackException;

/*
并没有采取数组的形式来保存栈帧，而是使用单向链表的形式，Zframe中保存这前一个帧的引用
最多持有1024个栈帧，可以设置这个值
* */
public class Zstack {
    // 虚拟机栈中所包含栈帧的最大容量
    int maxSize;
    // 当前虚拟机栈中包含帧的数量
    int size;
    // 栈顶的帧
    private Zframe _top;

    public Zstack(int maxSize) {
        this.maxSize = maxSize;
    }

    // 新添加一个栈帧，将这个栈帧设置为top，当然如果当前栈之前有元素
    // 那么将要push进的frame的lower是指为之前的top,当前frame变为top;
    void push(Zframe frame){
        if (size>maxSize){
            throw new StackOverflowError();
        }
        if (_top!=null){
            frame.lower = _top;
        }
        _top = frame;
        size++;
    }

    Zframe pop(){
        if (_top==null){
            throw new EmptyStackException();
        }
        Zframe tmp = _top;
        _top = tmp.lower;
        //tmp是带pop出的栈帧,既然要pop出来,那么将其lower设置为null,不在持有栈中的帧,避免内存泄露;
        tmp.lower = null;
        size--;
        return tmp;
    }

    Zframe top() {
        if (_top == null) {
            throw new EmptyStackException();
        }
        return _top;
    }

    Zframe[] getFrames() {
        Zframe[] frames = new Zframe[size];
        int i = 0;
        for (Zframe frame = _top;frame!=null;frame = frame.lower) {
            frames[i] = frame;
            i++;
        }
        return frames;
    }
}
