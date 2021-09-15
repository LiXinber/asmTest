package org.lix.asmtest.use;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author lix-bi
 * @date 2021/9/15 14:34
 **/
public class MainApplication {
    public static void main(String[] args) {
        //创建ClassWriter对象，定义一个你所创建class的全限定名、父类、权限等
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES|ClassWriter.COMPUTE_MAXS);
        cw.visit(V1_8, ACC_PUBLIC, "org/lix/asmtest/use/BigDecimalTest1", null, "java/lang/Object", null);
        //生成默认的构造方法
        cw.visitSource("BigDecimalTest1.java", null);
        MethodVisitor mv;
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(9, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lorg/lix/asmtest/use/BigDecimalTest1;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "add", "()Ljava/math/BigDecimal;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(11, l0);
            mv.visitTypeInsn(NEW, "java/math/BigDecimal");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("3");
            mv.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitTypeInsn(NEW, "java/math/BigDecimal");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("4");
            mv.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/math/BigDecimal", "add", "(Ljava/math/BigDecimal;)Ljava/math/BigDecimal;", false);
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lorg/lix/asmtest/use/BigDecimalTest1;", null, l0, l1, 0);
            mv.visitMaxs(4, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "multiply", "()Ljava/math/BigDecimal;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(14, l0);
            mv.visitTypeInsn(NEW, "java/math/BigDecimal");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("3");
            mv.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitTypeInsn(NEW, "java/math/BigDecimal");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("4");
            mv.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/math/BigDecimal", "multiply", "(Ljava/math/BigDecimal;)Ljava/math/BigDecimal;", false);
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lorg/lix/asmtest/use/BigDecimalTest1;", null, l0, l1, 0);
            mv.visitMaxs(4, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "divide", "()Ljava/math/BigDecimal;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(17, l0);
            mv.visitTypeInsn(NEW, "java/math/BigDecimal");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("3");
            mv.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitTypeInsn(NEW, "java/math/BigDecimal");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("4");
            mv.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/math/BigDecimal", "divide", "(Ljava/math/BigDecimal;)Ljava/math/BigDecimal;", false);
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lorg/lix/asmtest/use/BigDecimalTest1;", null, l0, l1, 0);
            mv.visitMaxs(4, 1);
            mv.visitEnd();
        }
        cw.visitEnd();

        byte[] code = cw.toByteArray();
        GeneratorClassLoader generatorClassLoader=new GeneratorClassLoader();
        Class clazz= generatorClassLoader.defineClassFromCode(code);
        try{
            Object obj = clazz.newInstance();
            Method addMethod = clazz.getMethod("add");
            Object addResult= addMethod.invoke(obj);
            System.out.println(addResult);
            Method multiplyMethod = clazz.getMethod("multiply");
            Object multiplyResult= multiplyMethod.invoke(obj);
            System.out.println(multiplyResult);
            Method divideMethod = clazz.getMethod("divide");
            Object divideResult= divideMethod.invoke(obj);
            System.out.println(divideResult);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static class GeneratorClassLoader extends ClassLoader{
        public Class defineClassFromCode(byte[] code){
            //直接将二进制流加载到内存中 name可以传入null
            /*注意：defineClass是ClassLoader的protected方法<本类、子类、同包类中才可以调用>，所以自己想办法吧*/
            Class<?> clazz=defineClass("org.lix.asmtest.use.BigDecimalTest1", code, 0, code.length);
            return clazz;
        }
    }
}
