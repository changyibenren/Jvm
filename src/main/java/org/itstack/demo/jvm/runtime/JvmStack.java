package org.itstack.demo.jvm.runtime;

import com.sun.tools.classfile.ConstantPool;

public class JvmStack {
    private int maxSize; //虚拟机栈中所包含栈帧的最大容量
    private int size; //虚拟机栈中所包含栈帧的最大容量
    private  Frame top; //栈顶的帧

    public JvmStack(int maxSize){
        this.maxSize = maxSize;
    }

    //新添加一个栈帧,将这个栈帧设置为 top,当然如果当前栈之前有元素,那么将要 push 进的 frame 的 lower 是指为之前的 top,当前 frame 变为 top
    public void push(Frame frame){
        if(this.size > this.maxSize){
            //如果栈已经满了，按照 Java 虚拟机规范，应该抛出 StackOverflowError 异常
            throw new StackOverflowError();
        }
        if(this.top != null){
            frame.lower = this.top; // frame 中保存前一个帧的引用,使得当前帧被 push 的时,前一个帧顶上去
        }
        this.top = frame;
        this.size++;
    }

    public Frame pop(){
        if(this.top == null){
            throw new RuntimeException("jvm stack is empty!");
        }
        Frame top = this.top;
        this.top = top.lower;
        top.lower = null; //top是pop出的栈帧,既然要 pop 出来,那么将其 lower 设置为 null,不在持有栈中的帧,避免内存泄露
        this.size--;
        return top;
    }

    public Frame top(){
        if(this.top == null){
            throw new RuntimeException("jvm stack is empty!");
        }
        return this.top;
    }

    public boolean isEmpty(){
        return this.top == null;
    }
}
