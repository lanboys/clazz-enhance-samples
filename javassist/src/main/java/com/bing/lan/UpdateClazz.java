package com.bing.lan;

import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

/**
 * Created by lanbing at 2022/7/28 9:54
 */

public class UpdateClazz {

  public static void main(String[] args) throws Exception {
    ClassPool pool = ClassPool.getDefault();
    CtClass cc = pool.get("com.bing.lan.PersonService");

    CtMethod personFly = cc.getDeclaredMethod("personFly");
    personFly.insertBefore("System.out.println(\"起飞之前准备降落伞\");");
    personFly.insertAfter("System.out.println(\"成功落地。。。。\");");

    //新增一个方法
    CtMethod ctMethod = new CtMethod(CtClass.voidType, "joinFriend", new CtClass[]{}, cc);
    ctMethod.setModifiers(Modifier.PUBLIC);
    ctMethod.setBody("{System.out.println(\"i want to be your friend\");}");
    cc.addMethod(ctMethod);

    Object person = cc.toClass().newInstance();
    // 调用 personFly 方法
    Method personFlyMethod = person.getClass().getMethod("personFly");
    personFlyMethod.invoke(person);
    //调用 joinFriend 方法
    Method execute = person.getClass().getMethod("joinFriend");
    execute.invoke(person);

    System.out.println("=================");

    new PersonService().personFly();
  }
}
