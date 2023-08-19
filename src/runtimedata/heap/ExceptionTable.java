package runtimedata.heap;

/**
 * @author koi
 * @date 2023/8/19 19:57
 */
public class ExceptionTable {
}

class ExceptionHandler{
    int startPc;
    int endPc;
    int handlerPc;
    ClassRef catchType;
}