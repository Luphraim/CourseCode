#!/usr/bin/env python
# coding=utf-8

from jpype import *

import os

if __name__=="__main__":
    # 启动JVM
    # 注意使用了-D选项指定了jar的目标位置
    # convertStrings参数是为了去掉烦人的Warning
    startJVM(getDefaultJVMPath(),"-ea","-Djava.class.path=%s"%(os.getcwd()+"/AESUtils.jar"))

    # 加载自定义的java class
    JDClass = JClass("AESUtil")
    jd = JDClass()

    # 获取java标准库打印函数
    jprint = java.lang.System.out.println
    # 调用自定义class中的函数并输出返回值
    print("Enter the string to be encrypted(Not Chinese):")
    src = input()
    encrypted = jd.encrypt(src)
    jprint("Encrypted: " + encrypted)
    jprint("Decrypted: " + jd.decrypt(encrypted))

    # 关闭JVM
    shutdownJVM()
