package org.itstack.demo.jvm.classpath;

import org.itstack.demo.jvm.classpath.impl.CompositeEntry;
import org.itstack.demo.jvm.classpath.impl.DirEntry;
import org.itstack.demo.jvm.classpath.impl.WildcardEntry;
import org.itstack.demo.jvm.classpath.impl.ZipEntry;

import java.io.File;
import java.io.IOException;

public interface Entry {
    /**
     * 负责寻找和加载class文件
     *
     * @param className class文件的相对路径，路径之间用斜线 / 分隔，文件名有.class后缀
     */
    byte[] readClass(String className) throws IOException;
    /**
     * 根据传入的path的形式不同,
     * @param path 命令行得到的路径字符串
     * @return 创建具体的Entry
     */
    static Entry create(String path){
        if(path.contains(File.pathSeparator)){
            return new CompositeEntry(path);
        }

        if(path.endsWith("*")){
            return new WildcardEntry(path);
        }

        if(path.endsWith(".jar")||path.endsWith(".JAR")||path.endsWith(".zip")||path.endsWith(".ZIP")){
            return new ZipEntry(path);
        }

        return new DirEntry(path);
    }
}
