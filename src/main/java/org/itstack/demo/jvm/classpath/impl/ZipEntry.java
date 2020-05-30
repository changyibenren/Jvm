package org.itstack.demo.jvm.classpath.impl;

import org.itstack.demo.jvm.classpath.Entry;

import java.io.IOException;
import java.nio.file.*;

public class ZipEntry implements Entry {
    private Path absolutePath;

    public ZipEntry(String path){
        this.absolutePath = Paths.get(path).toAbsolutePath();
    }

    //把zip文件当成文件夹
    @Override
    public byte[] readClass(String className) throws IOException {
        try (FileSystem zipFs = FileSystems.newFileSystem(absolutePath, null)) {
            return Files.readAllBytes(zipFs.getPath(className));
        }
    }

    @Override
    public String toString(){
        return this.absolutePath.toString();
    }
}
