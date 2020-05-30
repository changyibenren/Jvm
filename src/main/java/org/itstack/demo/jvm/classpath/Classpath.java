package org.itstack.demo.jvm.classpath;


import org.itstack.demo.jvm.classpath.impl.WildcardEntry;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Classpath {

    private Entry bootstrapClasspath; //启动类路径,默认对应jre\lib目录，Java标准库(大部分在rt.jar里)位于该路径
    private Entry extensionClasspath; //扩展类路径,默认对应jre\lib\ext目录，使用Java扩展机制的类位于这个路径
    private Entry userClasspath;      //用户类路径,自己实现的类，以及第三方类库则位于用户类路径

    //构造方法中使用bootstrapAndExtensionClasspath()方法
    //解析启动类路径和扩展类路径，使用parseUserClasspath()方法解析用户类路径。
    public Classpath(String jreOption,String cpOption){
        bootstrapAndExtensionClasspath(jreOption);
        parseUserClasspath(cpOption);
    }

    //这里参数传进来的是: D:\Java\jdk1.8.0_151\jre
    private void bootstrapAndExtensionClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);
        //可能出现的情况是: jre/lib/*
        String jreLibPath = Paths.get(jreDir,"lib") + File.separator + "*";
        bootstrapClasspath = new WildcardEntry(jreLibPath);
        //可能出现的情况是: jre/lib/ext/*
        String jreExtPath = Paths.get(jreDir,"lib","ext") + File.separator + "*";
        extensionClasspath = new WildcardEntry(jreExtPath);

    }

    //优先使用用户输入的-Xjre选项作为jre目录。如果没有输入该选项，则在当前目录下寻找jre目录。如果找不到，尝试使用JAVA_HOME环境变量。最终返回bootClasspath和extClasspath。
    private static String getJreDir(String jreOption){
        if(jreOption != null && Files.exists(Paths.get(jreOption))){
            return jreOption;
        }
        if(Files.exists(Paths.get("./jre"))){
            return "./jre";
        }
        String jh = System.getenv("JAVA_HOME");
        if(jh != null){
            return Paths.get(jh,"jre").toString();
        }
        throw new RuntimeException("Can not find JRE folder!");
    }

    //根据-cp的值得到一个userClasspath
    private void parseUserClasspath(String cpOption) {
        if(cpOption == null){
            cpOption = ".";
        }
        userClasspath = Entry.create(cpOption);
    }

    public byte[] readClass(String className) throws Exception {
        className = className + ".class";

        //[readClass]启动类路径
        try {
            return bootstrapClasspath.readClass(className);
        } catch (Exception ignored) {
            //ignored
        }

        //[readClass]扩展类路径
        try {
            return extensionClasspath.readClass(className);
        } catch (Exception ignored) {
            //ignored
        }

        //[readClass]用户类路径
        return userClasspath.readClass(className);
    }
}

