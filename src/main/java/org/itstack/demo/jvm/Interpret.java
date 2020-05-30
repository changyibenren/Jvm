package org.itstack.demo.jvm;

import com.alibaba.fastjson.JSON;
import com.sun.tools.classfile.*;
import org.itstack.demo.jvm.instructions.Factory;
import org.itstack.demo.jvm.instructions.base.BytecodeReader;
import org.itstack.demo.jvm.instructions.base.Instruction;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.Slot;
import org.itstack.demo.jvm.runtime.Thread;
import org.itstack.demo.jvm.runtime.methodarea.JMethod;


public class Interpret {
    /*Interpret(Method m){
        Code_attribute codeAttr = (Code_attribute)m.attributes.get(Attribute.Code);
        int maxLocals = codeAttr.max_locals;
        int maxStack = codeAttr.max_stack;
        byte[] byteCode = codeAttr.code;
        Thread thread = new Thread();
        Frame frame = thread.newFrame(maxLocals, maxStack);
       // Frame frame = thread.newFrame(m);
        thread.pushFrame(frame);
        loop(thread, byteCode);
    }*/
    /*Interpret(JMethod m){
        //Code_attribute codeAttr = (Code_attribute)m.attributes.get(Attribute.Code);
        int maxLocals = m.maxLocals;
        int maxStack = m.maxStack;
        byte[] byteCode = m.code();
        Thread thread = new Thread();
        Frame frame = thread.newFrame(m);
        // Frame frame = thread.newFrame(m);
        thread.pushFrame(frame);
        loop(thread, byteCode);
    }

    private void loop(Thread thread, byte[] byteCode){
        Frame frame = thread.popFrame();
        BytecodeReader reader = new BytecodeReader();

        while (true) {
            //循环
            int pc = frame.nextPC();
            thread.setPC(pc);
            //decode
            reader.reset(byteCode, pc);
            byte opcode = reader.readByte();
            Instruction inst = Factory.newInstruction(opcode);
            if (null == inst) {
                System.out.println("寄存器(指令)尚未实现 " + byteToHexString(new byte[]{opcode}));
                break;
            }
            inst.fetchOperands(reader);
            frame.setNextPC(reader.pc());
            //System.out.format("寄存器(指令)：" + byteToHexString(new byte[]{opcode}) + " -> " + inst.getClass().getSimpleName() + " => 局部变量表：" + JSON.toJSONString(frame.localVars().getSlots()) + " 操作数栈：" + frame.operandStack().getSlots());            //exec
           System.out.format("寄存器(指令)：0x%x -> %s => 局部变量表：%s 操作数栈：%s\n", opcode, inst.getClass().getSimpleName(),  JSON.toJSONString(frame.localVars().getSlots()), JSON.toJSONString(frame.operandStack().getSlots()));
            inst.execute(frame);
        }

    }

    private static String byteToHexString(byte[] codes) {
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        for (byte b : codes) {
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if (strHex.length() < 2) {
                strHex = "0" + strHex;
            }
            sb.append(strHex);
        }
        return sb.toString();
    }

    public String transform(Slot[] slots){
        String string = "[";
        for(int i=0;i<slots.length;i++){

            string += "{"+slots[i].num+"},";
        }
        string+="]";
        return string;
    }*/
    Interpret(JMethod method, boolean logInst) throws ClassNotFoundException {
        Thread thread = new Thread();
        Frame frame = thread.newFrame(method);
        thread.pushFrame(frame);

        loop(thread, logInst);
    }

    private void loop(Thread thread, boolean logInst) throws ClassNotFoundException {
        BytecodeReader reader = new BytecodeReader();
        while (true) {
            Frame frame = thread.currentFrame();
            int pc = frame.nextPC();
            thread.setPC(pc);

            reader.reset(frame.jMethod().code, pc);
            byte opcode = reader.readByte();
            Instruction inst = Factory.newInstruction(opcode);
            if (null == inst) {
                System.out.println("寄存器(指令)尚未实现 " + byteToHexString(new byte[]{opcode}));
                break;
            }
            inst.fetchOperands(reader);
            frame.setNextPC(reader.pc());

            if (logInst) {
                logInstruction(frame, inst, opcode);
            }

            //exec
            inst.execute(frame);

            if (thread.isStackEmpty()) {
                break;
            }
        }
    }

    private static void logInstruction(Frame frame, Instruction inst, byte opcode) {
        JMethod jMethod = frame.jMethod();
        String className = jMethod.clazz().name();
        String methodName = jMethod.name();
        String outStr = (className + "." + methodName + "() \t") +
                "寄存器(指令)：" + byteToHexString(new byte[]{opcode}) + " -> " + inst.getClass().getSimpleName() + " => 局部变量表：" + JSON.toJSONString(frame.localVars().getSlots()) + " 操作数栈：" + JSON.toJSONString(frame.operandStack().getSlots());
        System.out.println(outStr);
    }

    //byte数组转换为十六进制字符串
    private static String byteToHexString(byte[] codes) {
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        for (byte b : codes) {
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if (strHex.length() < 2) {
                strHex = "0" + strHex;
            }
            sb.append(strHex);
        }
        return sb.toString();
    }
}
