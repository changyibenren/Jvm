package org.itstack.demo.jvm.runtime;

import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.Method;
import org.itstack.demo.jvm.runtime.methodarea.JMethod;

public class Frame {
    Frame lower; //lower 字段用来实现链表数据结构，保存的是栈中前一个栈帧的引用，相当于链表中的 next 指针，这样当栈顶的帧出栈时，栈顶帧把其持有的 lower 帧设置为当前栈顶帧。
    //局部变量表
    private LocalVars localVars;
    //操作数栈
    private OperandStack operandStack;
    private Thread thread;
    private Method method;
    private int nextPC;
    private ConstantPool constantPool;
    private JMethod jMethod;

    /*public Frame(Thread thread,int maxLocals, int maxStack) {
        this.thread = thread;
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }*/

    public Frame(Thread thread, JMethod jmethod) {
        this.thread = thread;
        this.jMethod = jmethod;
        //Code_attribute codeAttr = (Code_attribute)method.attributes.get(Attribute.Code);
        this.localVars = new LocalVars(jmethod.maxLocals);
        this.operandStack = new OperandStack(jmethod.maxStack);

    }


    public LocalVars localVars(){
        return localVars;
    }

    public OperandStack operandStack(){
        return operandStack;
    }

    public Thread thread() {
        return this.thread;
    }

    public int nextPC() {
        return this.nextPC;
    }

    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }

    public Method method(){
        return this.method;
    }

    public ConstantPool getConstantPool(){
        return this.constantPool;
    }

    public JMethod jMethod(){
        return this.jMethod;
    }


}
