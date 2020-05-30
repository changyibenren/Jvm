package org.itstack.demo.jvm.runtime;

import org.itstack.demo.jvm.runtime.methodarea.JMethod;

public class Thread {
    //Program Counter 寄存器
    private int pc;

    //虚拟机栈
    private JvmStack stack;

    public Thread(){
        this.stack = new JvmStack(1024);
    }

    public int pc(){
        return this.pc;
    }

    public void setPC(int pc){
        this.pc = pc;
    }

    public void pushFrame(Frame frame){
        this.stack.push(frame);
    }

    public Frame popFrame(){
        return this.stack.pop();
    }

    public Frame currentFrame(){
        return this.stack.top();
    }

    /*public Frame newFrame(int maxLocals, int maxStack) {
        return new Frame(this, maxLocals, maxStack);
    }*/

    public Frame topFrame(){
        return this.stack.top();
    }

    public boolean isStackEmpty(){
        return this.stack.isEmpty();
    }

    public Frame newFrame(JMethod method) {
        return new Frame(this, method);
    }
}
