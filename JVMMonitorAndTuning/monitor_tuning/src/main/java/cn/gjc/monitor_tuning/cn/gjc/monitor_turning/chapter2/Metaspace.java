package cn.gjc.monitor_tuning.cn.gjc.monitor_turning.chapter2;

import jdk.internal.org.objectweb.asm.Opcodes;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.List;

/***
 *@ClassName Metaspace //https://blog.csdn.net/bolg_hero/article/details/78189621
 *@Description 继承ClassLoader是为了方便调用defineClass方法，因为该方法的定义为protected
 *@Auther gjc
 *@Date 2018/9/19 10:17
 *
 **/

public class Metaspace extends ClassLoader {

    public static List<Class<?>> createClasss() {
        //类持有
        List<Class<?>> classes = new ArrayList<Class<?>>();
        //循环1000w次，生成10000000个不同的类
        for (int i = 0; i < 10000000; ++i) {
            ClassWriter cw = new ClassWriter(0);
            //定义一个类名称为Class{i},它的访问域为public，父类为java.lang.Object,不实现任何接口
            cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
            //定义构造函数<init>方法
            MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            //第一个指令为调用父类Object的构造函数
            mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
            //第三条指令为return
            mw.visitInsn(Opcodes.RETURN);
            mw.visitMaxs(1, 1);
            mw.visitEnd();

            Metaspace test = new Metaspace();
            byte[] code = cw.toByteArray();
            //定义类
            Class<?> exampleClass = test.defineClass("Class" + i, code, 0, code.length);
            classes.add(exampleClass);
        }
        return classes;
    }

}
