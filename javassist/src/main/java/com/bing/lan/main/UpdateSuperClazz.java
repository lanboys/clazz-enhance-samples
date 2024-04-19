package com.bing.lan.main;

import com.bing.lan.obj.Student;
import com.bing.lan.obj.Teacher;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * Created by lanbing at 2022/7/28 10:17
 */

public class UpdateSuperClazz {

  public static void main(String[] args) throws Exception {
    ClassPool pool = ClassPool.getDefault();
    CtClass cc = pool.get("com.bing.lan.obj.Person");

    // 修改父类方法
    CtMethod run = cc.getDeclaredMethod("run");
    run.insertBefore("System.out.println(\"起飞之前准备降落伞\");");
    run.insertAfter("System.out.println(\"成功落地。。。。\");");

    // 转换字节码
    cc.toClass();

    Student student = new Student();
    // 覆盖父类方法
    student.run();

    System.out.println("---------");

    Teacher teacher = new Teacher();
    teacher.run();
  }
}
