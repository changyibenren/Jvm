package org.itstack.demo.jvm.runtime;


import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Descriptor;
import org.itstack.demo.jvm.classpath.Classpath;
import org.itstack.demo.jvm.runtime.constantpool.RunTimeConstantPool;
import org.itstack.demo.jvm.runtime.methodarea.JField;
import org.itstack.demo.jvm.runtime.methodarea.Class;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
class names:
    - primitive types: boolean, byte, int ...
    - primitive arrays: [Z, [B, [I ...
    - non-array classes: java/lang/Object ...
    - array classes: [Ljava/lang/Object; ...
*/
public class ClassLoader {

   /* private Path classpath;
    private Map<String, Class> classMap;

    public ClassLoader(Path classpath) {
        this.classpath = classpath;
        this.classMap = new HashMap<>();
    }

    public Class loadClass(String className) {
        Class clazz = classMap.get(className);
        if (null != clazz) return clazz;
        try {
            return loadNonArrayClass(className);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class loadNonArrayClass(String className) throws Exception {
        byte[] data = this.classpath.readClass(className);
        if (null == this.classpath) {
            throw new ClassNotFoundException(className);
        }
        Class clazz = defineClass(this.classpath);
        link(clazz);
        return clazz;
    }

    private void link(Class clazz) {
        verify(clazz);
        prepare(clazz);
    }

    private void prepare(Class clazz) {
        calcInstanceFieldSlotIds(clazz);
        calcStaticFieldSlotIds(clazz);
        allocAndInitStaticVars(clazz);
    }

    private void allocAndInitStaticVars(Class clazz) {
        clazz.staticVars = new Slots(clazz.staticSlotCount);
        for (JField field : clazz.fields) {
            if (field.isStatic() && field.isFinal()) {
                initStaticFinalVar(clazz, field);
            }
        }
    }

    private void initStaticFinalVar(Class clazz, JField field) {
        Slots staticVars = clazz.staticVars;
        RunTimeConstantPool constantPool = clazz.runTimeConstantPool;
        int cpIdx = field.constValueIndex();
        int slotId = field.slotId();

        if (cpIdx > 0) {
            switch (field.descriptor()) {
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    Object val = constantPool.getConstants(cpIdx);
                    staticVars.setInt(slotId, (Integer) val);
                case "J":
                    staticVars.setLong(slotId, (Long) constantPool.getConstants(cpIdx));
                case "F":
                    staticVars.setFloat(slotId, (Float) constantPool.getConstants(cpIdx));
                case "D":
                    staticVars.setDouble(slotId, (Double) constantPool.getConstants(cpIdx));
                case "Ljava/lang/String;":
                    System.out.println("todo");
            }
        }

    }

    private void calcStaticFieldSlotIds(Class clazz) {
        int slotId = 0;
        for (JField field : clazz.fields) {
            if (field.isStatic()) {
                field.slotId = slotId;
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.staticSlotCount = slotId;
    }

    private void calcInstanceFieldSlotIds(Class clazz) {
        int slotId = 0;
        if (clazz.superClass != null) {
            slotId = clazz.superClass.instanceSlotCount;
        }
        for (JField field : clazz.fields) {
            if (!field.isStatic()) {
                field.slotId = slotId;
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.instanceSlotCount = slotId;
    }

    private void verify(Class clazz) {
        // 校验字节码，尚未实现
    }

    private Class defineClass(Path path) throws Exception {
        Class clazz = parseClass(path);
        clazz.loader = this;
        resolveSuperClass(clazz);
        resolveInterfaces(clazz);
        this.classMap.put(clazz.name, clazz);
        return clazz;
    }

    private void resolveInterfaces(Class clazz) throws Exception {
        int interfaceCount = clazz.interfaceNames.length;
        if (interfaceCount > 0) {
            clazz.interfaces = new Class[interfaceCount];
            for (int i = 0; i < interfaceCount; i++) {
                clazz.interfaces[i] = clazz.loader.loadClass(clazz.interfaceNames[i]);
            }
        }
    }

    private void resolveSuperClass(Class clazz) throws Exception {
        if (!clazz.name.equals("java/lang/Object")) {
            clazz.superClass = clazz.loader.loadClass(clazz.superClassName);
        }
    }

    public Class parseClass(Path path) throws IOException, ConstantPoolException, Descriptor.InvalidDescriptor {
        ClassFile classFile = ClassFile.read(path);
        return new Class(classFile);
    }*/
    private Classpath classpath;
    //作为缓存，之前加载过这个类，那么就将其class引用保存到map中，后面再用到这个类的时候，直接用map中取
    private Map<String, Class> classMap;

    public ClassLoader(Classpath classpath) {
        this.classpath = classpath;
        this.classMap = new HashMap<>();
    }

    public Class loadClass(String className) {
        Class clazz = classMap.get(className);
        if (null != clazz) return clazz;
        try {
            return loadNonArrayClass(className);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class loadNonArrayClass(String className) throws Exception {
        byte[] data = this.classpath.readClass(className);
        if (null == data) {
            throw new ClassNotFoundException(className);
        }
        Class clazz = defineClass(data);
        link(clazz);
        return clazz;
    }

    private void link(Class clazz) {
        verify(clazz);
        prepare(clazz);
    }

    //给类变量分配空间并赋予初始值
    private void prepare(Class clazz) {
        calcInstanceFieldSlotIds(clazz);
        calcStaticFieldSlotIds(clazz);
        allocAndInitStaticVars(clazz);
    }

    // 为静态变量申请空间,注意:这个申请空间的过程,就是将所有的静态变量赋值为0或者null;
    // 如果是 static final 的基本类型或者 String，其值会保存在ConstantValueAttribute属性中
    // 而ConstantValueAttribute属性中保存的值又是在常量池中！
    private void allocAndInitStaticVars(Class clazz) {
        clazz.staticVars = new Slots(clazz.staticSlotCount);
        for (JField field : clazz.fields) {
            if (field.isStatic() && field.isFinal()) {
                initStaticFinalVar(clazz, field);
            }
        }
    }

    //为static final修饰的成员赋值，这种类型的成员是CONSTANT_XXX_info类型的，该info中包含真正的值在运行时常量池中。
    private void initStaticFinalVar(Class clazz, JField field) {
        Slots staticVars = clazz.staticVars;
        RunTimeConstantPool constantPool = clazz.runTimeConstantPool;
        int cpIdx = field.constValueIndex();
        int slotId = field.slotId();

        if (cpIdx > 0) {
            switch (field.descriptor()) {
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    java.lang.Object val = constantPool.getConstants(cpIdx);
                    staticVars.setInt(slotId, (Integer) val);
                case "J":
                    staticVars.setLong(slotId, (Long) constantPool.getConstants(cpIdx));
                case "F":
                    staticVars.setFloat(slotId, (Float) constantPool.getConstants(cpIdx));
                case "D":
                    staticVars.setDouble(slotId, (Double) constantPool.getConstants(cpIdx));
                case "Ljava/lang/String;":
                    System.out.println("todo");
            }
        }

    }

    //计算类的静态成员变量所需的空间，不包含父类，同样也只是计算和分配 slotId，不申请空间
    private void calcStaticFieldSlotIds(Class clazz) {
        int slotId = 0;
        for (JField field : clazz.fields) {
            if (field.isStatic()) {
                field.slotId = slotId;
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.staticSlotCount = slotId;
    }

    // 计算new一个对象所需的空间,单位是clazz.instanceSlotCount,主要包含了类的非静态成员变量(包含父类的)
    // 但是这里并没有真正的申请空间，只是计算大小，同时为每个非静态变量关联 slotId
    private void calcInstanceFieldSlotIds(Class clazz) {
        int slotId = 0;
        if (clazz.superClass != null) {
            slotId = clazz.superClass.instanceSlotCount;
        }
        for (JField field : clazz.fields) {
            if (!field.isStatic()) {
                field.slotId = slotId;
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.instanceSlotCount = slotId;
    }

    private void verify(Class clazz) {
        // 校验字节码，尚未实现
    }

    private Class defineClass(byte[] data) throws Exception {
        Class clazz = parseClass(data);
        clazz.loader = this;
        resolveSuperClass(clazz);
        resolveInterfaces(clazz);
        this.classMap.put(clazz.name, clazz);
        return clazz;
    }

    private void resolveInterfaces(Class clazz) throws Exception {
        int interfaceCount = clazz.interfaceNames.length;
        if (interfaceCount > 0) {
            clazz.interfaces = new Class[interfaceCount];
            for (int i = 0; i < interfaceCount; i++) {
                clazz.interfaces[i] = clazz.loader.loadClass(clazz.interfaceNames[i]);
            }
        }
    }

    private void resolveSuperClass(Class clazz) throws Exception {
        if (!clazz.name.equals("java/lang/Object")) {
            clazz.superClass = clazz.loader.loadClass(clazz.superClassName);
        }
    }

    private Class parseClass(byte[] data) throws Descriptor.InvalidDescriptor, ConstantPoolException, IOException {
       // Path path =
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ClassFile classFile = ClassFile.read(byteArrayInputStream);
        return new Class(classFile);
    }





}
