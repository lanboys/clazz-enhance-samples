package com.bing.lan.obj;

/**
 * Created by lanbing at 2022/7/28 10:24
 */

public class Student extends Person {

  @Override
  public void say() {
    System.out.println("I am a student.");
  }

  @Override
  public void run() {
    System.out.println("I run fast ...");
  }
}
