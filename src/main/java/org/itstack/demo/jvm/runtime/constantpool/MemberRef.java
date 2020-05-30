package org.itstack.demo.jvm.runtime.constantpool;

import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;

import java.util.Map;

public class MemberRef extends SymRef {

    public String name;
    public String descriptor;

    public void copyFieldRefInfo(ConstantPool.CONSTANT_Fieldref_info refInfo) throws ConstantPoolException {
        this.className = refInfo.getClassName();
        //Map<String, String> map = refInfo.getNameAndTypeInfo().toString();
        this.name = refInfo.getNameAndTypeInfo().getName();
        this.descriptor = refInfo.getNameAndTypeInfo().getType();
    }

    public void copyInterfaceMethodRefInfo(ConstantPool.CONSTANT_InterfaceMethodref_info refInfo) throws ConstantPoolException {
        this.className = refInfo.getClassName();
        this.name = refInfo.getNameAndTypeInfo().getName();
        this.descriptor = refInfo.getNameAndTypeInfo().getType();
    }

    public void copyMethodRefInfo(ConstantPool.CONSTANT_Methodref_info refInfo) throws ConstantPoolException {
        this.className = refInfo.getClassName();
        this.name = refInfo.getNameAndTypeInfo().getName();
        this.descriptor = refInfo.getNameAndTypeInfo().getType();
    }
    /*public void copyMemberRefInfo(ConstantPool.CPRefInfo refInfo) throws ConstantPoolException {
        this.className = refInfo.getClassName();
       // Map<String, String> map = refInfo.nameAndDescriptor();
        this.name = refInfo.getNameAndTypeInfo().getName();
        this.descriptor = refInfo.getNameAndTypeInfo().getType();
    }*/

    public String name(){
        return this.name;
    }

    public String descriptor(){
        return this.descriptor;
    }

}
