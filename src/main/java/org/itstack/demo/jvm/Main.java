package org.itstack.demo.jvm;


import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Descriptor;
import com.sun.tools.classfile.Method;
import org.itstack.demo.jvm.Cmd;
import org.itstack.demo.jvm.classpath.Classpath;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.LocalVars;
import org.itstack.demo.jvm.runtime.OperandStack;
import org.itstack.demo.jvm.runtime.methodarea.JMethod;
import org.itstack.demo.jvm.runtime.methodarea.Class;
import org.itstack.demo.jvm.runtime.ClassLoader;

import javax.tools.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ConstantPoolException, Descriptor.InvalidDescriptor, NoSuchFieldException, ClassNotFoundException, IOException {
       Cmd cmd = Cmd.parse(args);
       //System.out.println(cmd.classpath);
        if(!cmd.ok || cmd.helpFlag){
            System.out.println("Usage: <main class> [-options] class [args...]");
            return;
        }
        if(cmd.versionFlag){
            System.out.println("java version \"1.8.0\"");
            return;
        }
        //compiler();
        startJVM(cmd);
    }

    private static void startJVM(Cmd cmd) throws ConstantPoolException, Descriptor.InvalidDescriptor, NoSuchFieldException, ClassNotFoundException, IOException {
        Classpath cp = new Classpath(cmd.jre,cmd.classpath);
        System.out.printf("classpath:%s class:%s args:%s\n",cp,cmd.getMainClass(),cmd.getAppArgs());
        String className = cmd.getMainClass().replace(".","/");
        // Frame frame = new Frame(100,100);
        //  test_localVars(frame.localVars());
        // test_operandStack(frame.operandStack());

        try{
            byte[] classData = cp.readClass(className);
            System.out.println("classData: ");
            for(byte b : classData){
                System.out.println(String.format("%02x",b & 0xff) + "");
            }
        }catch(Exception e){
            System.out.println("Could not find or load main class" + cmd.getMainClass());
            e.printStackTrace();

        }
       /* Path path = Paths.get("D:\\IdeaProjects\\changyi5\\target\\classes\\HelloWorld.class");
        ClassFile classFile = loadClass(className,path);
        Method mainMethod = getMainMethod(classFile);
        //JMethod jMethod =
        /*assert classFile != null;
        printClassInfo(classFile);
        new Interpret(mainMethod);*/
        // Path path = Paths.get("D:\\IdeaProjects\\changyi5\\target\\classes\\");
        /*ClassLoader classLoader = new ClassLoader(cp);
        // System.out.println();
        // ClassFile classFile = loadClass(className,path);
        Class mainClass = classLoader.loadClass(className);
        //classLoader
        //找到主函数方法入口
        JMethod mainMethod = mainClass.getMainMethod();
        //mainMethod.constantPool = classFile.constant_pool;
        //mainMethod.constantPool =
        if (null == mainMethod) {
            throw new RuntimeException("Main method not found in class " + cmd.getMainClass());
        }
        System.out.println(cmd.verboseClassFlag);
        //new Interpret(mainMethod);
        new Interpret(mainMethod, cmd.verboseClassFlag);*/
    }



    private static ClassFile loadClass(String className, Path path){
        try {
            //byte[] classData = classpath.readClass(className);
            return ClassFile.read(path);
        } catch (Exception e) {
            System.out.println("Could not find or load main class " + className);
            return null;
        }
    }

    //找到主函数入口方法
    private static Method getMainMethod(ClassFile cf) throws ConstantPoolException, Descriptor.InvalidDescriptor {
        if (null == cf) return null;
        Method[] methods = cf.methods;
        for (Method m : methods) {
            // System.out.println(m.getName(cf.constant_pool));
            //System.out.println(m.descriptor.getFieldType(cf.constant_pool));
            /*if ("main".equals(m.getName(cf.constant_pool)) && "([Ljava/lang/String;)V".equals(m.descriptor.toString())) {
                return m;
            }*/
            if ("main".equals(m.getName(cf.constant_pool)) && "(java.lang.String[]), void".equals(m.descriptor.getFieldType(cf.constant_pool))) {
                // System.out.print("hhh");
                return m;
            }
        }
        return null;
    }
    private static void test_localVars(LocalVars vars){
        vars.setInt(0,100);
        vars.setInt(1,-100);
        System.out.println(vars.getInt(0));
        System.out.println(vars.getInt(1));
    }

    private static void test_operandStack(OperandStack ops){
        ops.pushInt(100);
        ops.pushInt(-100);
        ops.pushRef(null);
        System.out.println(ops.popRef());
        System.out.println(ops.popInt());
    }


    private static void printClassInfo(ClassFile classFile){
        System.out.println("version:" + classFile.major_version + "." + classFile.minor_version);
        System.out.println("constants count：" + classFile.constant_pool);
        System.out.format("access flags：%s\n",classFile.access_flags.getClassFlags());
        /*System.out.println("this class：" + classFile.getName());
        System.out.println("super class：" + classFile.getSuperclassName());
        System.out.println("interfaces：" + Arrays.toString(classFile.getInterfaceName()));*/
        System.out.println("fields count：" + classFile.fields.length);
    }

    private static void compiler() throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // 建立DiagnosticCollector对象
        DiagnosticCollector diagnostics = new DiagnosticCollector();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

        // 建立源文件对象，每个文件被保存在一个从JavaFileObject继承的类中
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays
                .asList("D:\\IdeaProjects\\changyi5\\src\\main\\test\\HelloWorld.java"));

        // options命令行选项
        Iterable<String> options = Arrays.asList("-d",
                "D:\\IdeaProjects\\changyi5\\target\\classes\\");// 指定的路径一定要存在，javac不会自己创建文件夹
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null,
                compilationUnits);

        // 编译源程序
        boolean success = task.call();
        fileManager.close();
        System.out.println((success) ? "编译成功" : "编译失败");

        // 打印信息
        for (Object object : diagnostics.getDiagnostics()) {
            Diagnostic diagnostic = (Diagnostic) object;
            System.out.printf("Code: %s%n" + "Kind: %s%n" + "Position: %s%n" + "Start Position: %s%n"
                            + "End Position: %s%n" + "Source: %s%n" + "Message: %s%n", diagnostic.getCode(),
                    diagnostic.getKind(), diagnostic.getPosition(), diagnostic.getStartPosition(),
                    diagnostic.getEndPosition(), diagnostic.getSource(), diagnostic.getMessage(null));
        }
    }


}

