package org.itstack.demo.jvm.classpath.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class WildcardEntry extends org.itstack.demo.jvm.classpath.impl.CompositeEntry {
    public WildcardEntry(String path){
        super(toPathList(path));
    }

    //遍历该路径下的所有以.jar结尾的文件
    private static String toPathList(String wildcardPath) {
        String baseDir = wildcardPath.replace("*","");
        try{
            return Files.walk(Paths.get(baseDir)).filter(Files::isRegularFile).map(Path::toString).
                    filter(p -> p.endsWith(".jar") || p.endsWith(".JAR")).collect(Collectors.joining(File.pathSeparator));
        }catch (IOException e){
            return "";
        }
    }
}
