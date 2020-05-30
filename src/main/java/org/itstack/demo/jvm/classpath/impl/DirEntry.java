package org.itstack.demo.jvm.classpath.impl;

import org.itstack.demo.jvm.classpath.Entry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirEntry implements Entry {
    private Path absolutePath;

    public DirEntry(String path){
        this.absolutePath = Paths.get(path).toAbsolutePath();
    }

    //在readClass()方法中将绝对路径和className拼接起来，读取其中的字节码，并返回
    @Override
    public byte[] readClass(String className) throws IOException {
        return Files.readAllBytes(absolutePath.resolve(className));
    }

    @Override
    public String toString(){
        return this.absolutePath.toString();
    }
}
