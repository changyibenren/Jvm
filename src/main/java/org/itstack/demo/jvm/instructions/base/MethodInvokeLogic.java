package org.itstack.demo.jvm.instructions.base;

import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.Slot;
import org.itstack.demo.jvm.runtime.methodarea.JMethod;
import org.itstack.demo.jvm.runtime.Thread;

public class MethodInvokeLogic {

    public static void invokeMethod(Frame invokerFrame, JMethod method) {
        Thread thread = invokerFrame.thread();
        Frame newFrame = thread.newFrame(method);
        thread.pushFrame(newFrame);

        int argSlotCount = method.argSlotCount();
        if (argSlotCount > 0) {
            for (int i = argSlotCount - 1; i >= 0; i--) {
                Slot slot = invokerFrame.operandStack().popSlot();
                newFrame.localVars().setSlot(i, slot);
            }
        }

        //hack
        if (method.isNative()) {
            if ("registerNatives".equals(method.name())) {
                thread.popFrame();
            } else {
                throw new RuntimeException("native method " + method.name());
            }
        }
    }

}
