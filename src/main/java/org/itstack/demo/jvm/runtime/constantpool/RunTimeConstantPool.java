package org.itstack.demo.jvm.runtime.constantpool;


import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;
import org.itstack.demo.jvm.runtime.methodarea.Class;
import org.apache.commons.collections.IteratorUtils;

import java.util.List;


/**
 * 运行时常量池
 */
public class RunTimeConstantPool {

    private Class clazz;
    private java.lang.Object[] constants;

    //主要作用是将class文件中的常量池转换为运行时常量池
    public RunTimeConstantPool(Class clazz, ConstantPool constantPool) throws ConstantPoolException {
       // int cpCount = constantPool.getConstantInfos().length;
        int cpCount = constantPool.size();
        this.clazz = clazz;
        this.constants = new Object[cpCount];

        //ConstantPool.CPInfo[] constantInfos = (ConstantPool.CPInfo[]) IteratorUtils.toList(constantPool.entries().iterator()).toArray();
        List<ConstantPool.CPInfo> list = IteratorUtils.toList(constantPool.entries().iterator());
        ConstantPool.CPInfo[] constantInfos = new ConstantPool.CPInfo[1];
        //ConstantPool.CPInfo[] constantInfos = (ConstantPool.CPInfo[]) (IteratorUtils.toList(constantPool.entries().iterator()).toArray());
        constantInfos = list.toArray(constantInfos);
        for (int i = 0; i < cpCount-1; i++) {
            ConstantPool.CPInfo constantInfo = constantInfos[i];

            switch (constantInfo.getTag()) {
                case ConstantPool.CONSTANT_Integer:
                    ConstantPool.CONSTANT_Integer_info integerInfo = (ConstantPool.CONSTANT_Integer_info) constantInfo;
                    this.constants[i] = integerInfo.value;
                    break;
                case ConstantPool.CONSTANT_Float:
                    ConstantPool.CONSTANT_Float_info floatInfo = (ConstantPool.CONSTANT_Float_info) constantInfo;
                    this.constants[i] = floatInfo.value;
                    break;
                //Long 和 Double 在转换结束之后，都要进行 i++,以适配 class 文件中常量池的索引
                case ConstantPool.CONSTANT_Long:
                    ConstantPool.CONSTANT_Long_info longInfo = (ConstantPool.CONSTANT_Long_info) constantInfo;
                    this.constants[i] = longInfo.value;
                    i++;
                    break;
                case ConstantPool.CONSTANT_Double:
                    ConstantPool.CONSTANT_Double_info doubleInfo = (ConstantPool.CONSTANT_Double_info) constantInfo;
                    this.constants[i] = doubleInfo.value;
                    i++;
                    break;
                //在对字符串引用进行转换的时候，转为字符串直接引用
                case ConstantPool.CONSTANT_String:
                    ConstantPool.CONSTANT_String_info stringInfo = (ConstantPool.CONSTANT_String_info) constantInfo;
                    this.constants[i] = stringInfo.getString();
                    break;
                case ConstantPool.CONSTANT_Class:
                    //ref 类中真正需要的是 传入上面的 clazz
                    ConstantPool.CONSTANT_Class_info classInfo = (ConstantPool.CONSTANT_Class_info) constantInfo;
                    this.constants[i] = ClassRef.newClassRef(this, classInfo);
                    break;
                case ConstantPool.CONSTANT_Fieldref:
                    this.constants[i] = FieldRef.newFieldRef(this, (ConstantPool.CONSTANT_Fieldref_info) constantInfo);
                    //this.constants[i] = FieldRef.newFieldRef(this, (ConstantPool.CPRefInfo) constantInfo);
                    break;
                case ConstantPool.CONSTANT_InterfaceMethodref:
                    this.constants[i] = InterfaceMethodRef.newInterfaceMethodRef(this, (ConstantPool.CONSTANT_InterfaceMethodref_info) constantInfo);
                    //this.constants[i] = InterfaceMethodRef.newInterfaceMethodRef(this, (ConstantPool.CPRefInfo) constantInfo);
                    break;
                case ConstantPool.CONSTANT_Methodref:
                    this.constants[i] = MethodRef.newMethodRef(this, (ConstantPool.CONSTANT_Methodref_info) constantInfo);
                    break;
                default:
            }
        }
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public java.lang.Object getConstants(int id) {
        return this.constants[id];
    }

}
