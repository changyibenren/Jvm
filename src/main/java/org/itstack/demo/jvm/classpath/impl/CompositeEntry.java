package org.itstack.demo.jvm.classpath.impl;

import org.itstack.demo.jvm.classpath.Entry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompositeEntry implements Entry{
    private final List<Entry> entryList = new ArrayList<>();

    //包含多个路径的情况。在构造方法中将路径分隔开，在readClass()方法中读取不同路径下的文件的字节码并返回。
    public CompositeEntry(String pathList){
        //String[] paths = pathList.split(File.separator);
        String[] paths = pathList.split(File.pathSeparator);
        for(String path:paths){
            entryList.add(Entry.create(path));
        }
    }


    @Override
    public byte[] readClass(String className) throws IOException {
        for (Entry entry : entryList) {
            try {
                return entry.readClass(className);
            } catch (Exception ignored) {
                //ignored
            }
        }
        throw new IOException("class not found " + className);
    }

    @Override
    public String toString(){
        String[] strs = new String[entryList.size()];
        for(int i = 0; i < strs.length; i++ ){
            strs[i] = entryList.get(i).toString();
        }
        return String.join(File.separator,strs);
    }
}
