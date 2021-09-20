package org.lix.cddcTest.use;

import org.objectweb.asm.*;

import java.math.BigDecimal;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author lix-bi
 * @date 2021/9/20 12:42
 **/
public class MainFucApplication {
    public static void main(String[] args) {
        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(52, ACC_PUBLIC + ACC_SUPER, "org/lix/cddcTest/use/BigDecimalFucTest", null, "org/lix/cddcTest/use/CDDCFunction", null);

        cw.visitSource("BigDecimalFucTest.java", null);

        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(9, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "org/lix/cddcTest/use/CDDCFunction", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lorg/lix/cddcTest/use/BigDecimalFucTest;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "apply", "(Lorg/lix/cddcTest/use/CDDCContext;)Lorg/lix/cddcTest/use/CDDCResult;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(12, l0);
            mv.visitTypeInsn(NEW, "org/lix/cddcTest/use/CDDCResult");
            mv.visitInsn(DUP);
            mv.visitTypeInsn(NEW, "java/math/BigDecimal");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("1");
            mv.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitLdcInsn("ABC");
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/lix/cddcTest/use/CDDCContext", "getVarBigDecimalValue", "(Ljava/lang/String;)Ljava/math/BigDecimal;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/math/BigDecimal", "add", "(Ljava/math/BigDecimal;)Ljava/math/BigDecimal;", false);
            mv.visitMethodInsn(INVOKESPECIAL, "org/lix/cddcTest/use/CDDCResult", "<init>", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lorg/lix/cddcTest/use/BigDecimalFucTest;", null, l0, l1, 0);
            mv.visitLocalVariable("cddcContext", "Lorg/lix/cddcTest/use/CDDCContext;", null, l0, l1, 1);
            mv.visitMaxs(5, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "apply", "(Ljava/lang/Object;)Ljava/lang/Object;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(9, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(CHECKCAST, "org/lix/cddcTest/use/CDDCContext");
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/lix/cddcTest/use/BigDecimalFucTest", "apply", "(Lorg/lix/cddcTest/use/CDDCContext;)Lorg/lix/cddcTest/use/CDDCResult;", false);
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lorg/lix/cddcTest/use/BigDecimalFucTest;", null, l0, l1, 0);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        cw.visitEnd();
        // 获取生成的class文件对应的二进制
        byte[] code = cw.toByteArray();
        GeneratorClassLoader generatorClassLoader=new GeneratorClassLoader();
        Class clazz= generatorClassLoader.defineClassFromCode(code);
        try {
            CDDCContext cddcContext = CDDCContext.getDefault();
            cddcContext.setVar("ABC", new BigDecimal("103"));
            CDDCFunction obj = (CDDCFunction)clazz.newInstance();
            CDDCResult result = obj.apply(cddcContext);
            System.out.println(result.getValue());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static class GeneratorClassLoader extends ClassLoader{
        public Class defineClassFromCode(byte[] code){
            //直接将二进制流加载到内存中 name可以传入null
            /*注意：defineClass是ClassLoader的protected方法<本类、子类、同包类中才可以调用>，所以自己想办法吧*/
            Class<?> clazz=defineClass("org.lix.cddcTest.use.BigDecimalFucTest", code, 0, code.length);
            return clazz;
        }
    }
}
