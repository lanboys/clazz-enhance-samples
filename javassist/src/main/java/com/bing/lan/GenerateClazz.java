package com.bing.lan;

import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.Modifier;

/**
 * Created by lanbing at 2022/07/28 9:25
 */
public class GenerateClazz {

  /**
   *
   * 要生成的类
   *
   * public class HelloWorld {
   *
   *   public static void main(String[] var0) {
   *     System.out.println("javassist say hello world !");
   *   }
   *
   *   public HelloWorld() {
   *   }
   * }
   */

  public static void main(String[] args) throws Exception {

    ClassPool pool = ClassPool.getDefault();

    // 创建类 路径和名称
    CtClass ctClass = pool.makeClass("gen.com.bing.lan.HelloWorld");

    // 添加方法
    CtMethod mainMethod = new CtMethod(CtClass.voidType, "main",
        new CtClass[]{pool.get(String[].class.getName())}, ctClass);

    mainMethod.setModifiers(Modifier.PUBLIC + Modifier.STATIC);
    mainMethod.setBody("{System.out.println(\"javassist say hello world !\");}");
    ctClass.addMethod(mainMethod);

    // 创建无参数构造方法
    CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
    ctConstructor.setBody("{}");
    ctClass.addConstructor(ctConstructor);

    // 输出.class字节码到本地文件
    ctClass.writeFile();

    // 测试调用
    Class<?> clazz = ctClass.toClass();
    Object obj = clazz.getDeclaredConstructor().newInstance();

    Method main = clazz.getDeclaredMethod("main", String[].class);
    main.invoke(obj, (Object) new String[1]);
  }
}