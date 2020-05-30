package org.itstack.demo.jvm.runtime.methodarea;

import com.sun.tools.classfile.*;
import org.apache.commons.collections.IteratorUtils;

import java.util.List;


public class JField extends ClassMember {

    public int constValueIndex;
    public int slotId;



    //把类文件的字段转化为自定义的字段
    public JField[] newFields(Class clazz, Field[] cfFields,ConstantPool constantPool) throws ConstantPoolException, Descriptor.InvalidDescriptor {
        JField[] jfields = new JField[cfFields.length];
        for (int i = 0; i < cfFields.length; i++) {
           /* List<ConstantPool.CPInfo> list = IteratorUtils.toList(classFile.constant_pool.entries().iterator());
            ConstantPool.CPInfo[] constantInfos = new ConstantPool.CPInfo[1];
            //ConstantPool.CPInfo[] constantInfos = (ConstantPool.CPInfo[]) (IteratorUtils.toList(constantPool.entries().iterator()).toArray());
            constantInfos = list.toArray(constantInfos);
            ConstantPool constantPool = new ConstantPool(constantInfos);
            CopyValue(constantPool,jfields[i].constantPool);*/
            jfields[i] = new JField();
            jfields[i].constantPool = constantPool;
            jfields[i].clazz = clazz;
            jfields[i].copyFieldInfo(cfFields[i]);
            //jfields[i].copyAttributes(cfFields[i]);
            Attributes attributes = cfFields[i].attributes;
            for (Attribute attribute : attributes) {
                if (attribute instanceof ConstantValue_attribute){
                    ConstantValue_attribute constantValue_attribute = (ConstantValue_attribute)attribute;
                    this.constValueIndex = constantValue_attribute.constantvalue_index;
                }
            }
        }
        return jfields;
    }

    /*public void copyAttributes(Field field) {
        Attribute valAttr = field.attributes.get(i);
        if (null != valAttr) {
            this.constValueIndex = valAttr.constantvalue_index;
        }
    }*/

    /*public JField[] newFields(Class clazz, MemberInfo[] cfFields) throws ConstantPool.InvalidIndex, ConstantPool.UnexpectedEntry {
        JField[] fields = new JField[cfFields.length];
        for (int i = 0; i < cfFields.length; i++) {
            fields[i] = new JField();
            fields[i].clazz = clazz;
            fields[i].copyMemberInfo(cfFields[i]);
            fields[i].copyAttributes(cfFields[i]);
        }
        return fields;
    }

    public void copyAttributes(MemberInfo cfField) {
        ConstantValue_attribute valAttr = cfField.ConstantValueAttribute();
        if (null != valAttr) {
            this.constValueIndex = valAttr.constantvalue_index;
        }
    }*/

    public boolean isVolatile() {
        return 0 != (this.accessFlags & AccessFlags.ACC_VOLATILE);
    }

    public boolean isTransient() {
        return 0 != (this.accessFlags & AccessFlags.ACC_TRANSIENT);
    }

    public boolean isEnum() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ENUM);
    }

    public int constValueIndex() {
        return this.constValueIndex;
    }

    public int slotId() {
        return this.slotId;
    }

    public boolean isLongOrDouble() {
        return this.descriptor.equals("J") || this.descriptor.equals("D");
    }




}
