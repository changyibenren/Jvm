package org.itstack.demo.jvm.runtime.methodarea;

import java.util.ArrayList;
import java.util.List;


public class MethodDescriptor {

    public List<String> parameterTypes = new ArrayList<>();
    public String returnType;

    public void addParameterType(String type){
        this.parameterTypes.add(type);
    }

}
