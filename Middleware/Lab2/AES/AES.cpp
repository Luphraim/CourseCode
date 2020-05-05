/*************************************************************************
	> File Name: AES.cpp
	> Author: Jason
	> Mail: 24320172203182@stu.xmu.edu.cn
	> Created Time: Fri 13 Mar 2020 10:38:13 AM CST
 ************************************************************************/

#include<iostream>
#include<stdlib.h>
#include<jni.h>

using namespace std;

int main(){

	/* 接下来，声明所有希望在程序中使用的变量。 
	JavaVMOption options[] 具有用于 JVM 的各种选项设置。 
	当声明变量时，确保所声明的JavaVMOption options[] 数组足够大，以便能容纳你希望使用的所有选项。 
	在本例中，我们使用的唯一选项就是类路径选项。 
	因为在本示例中，我们所有的文件都在同一目录中，所以将类路径设置成当前目录。 
	可以设置类路径，使它指向任何希望使用的目录结构。*/  
    char opt[] = "-Djava.class.path=./";

    JavaVMOption options[1];
    options[0].optionString = opt; options[0].extraInfo = NULL;

    JavaVMInitArgs jargv;
    jargv.version = JNI_VERSION_1_8;
    jargv.nOptions = 1;
    jargv.options = options;
    jargv.ignoreUnrecognized = JNI_TRUE;

    JavaVM *jvm;
    JNIEnv *jenv;

	/*创建 JVM 
	处理完所有设置之后，现在就准备创建 JVM 了。先从调用方法开始 
	如果成功，则这个方法返回零，否则，如果无法创建 JVM，则返回JNI_ERR。*/ 
    jint ret = JNI_CreateJavaVM(&jvm,(void**)&jenv,&jargv);
    if(ret==JNI_ERR){
        cout<<"Create VM ERROR!"<<endl;
        return -1;
    }

    /* 查找并装入 Java 类 
	一旦创建了 JVM 之后，就可以准备开始在本机应用程序中运行 Java 代码。 
	首先，需要使用FindClass() 函数查找并装入 Java 类，如下所示： 
	cls 变量存储执行FindClass() 函数后的结果,如果找到该类，则 cls 变量表示该Java 类的句柄, 
	如果不能找到该类，则 cls 将为零。*/  
    jclass cls = jenv->FindClass("AESUtilc");
    if(cls == 0){
        cout<<"Find Class ERROR!"<<endl;
        return -1;
    }

    // 调用java方法
    cout<<"1.Encrypt(E/e)"<<endl
		<<"2.Decrypt(D/d)"<<endl
		<<"Please choose operation('q' to quit):"<<endl;
	char opera;
	cin>>opera;
    while(opera!='q'){
        if(opera=='E'||opera=='e'){
            string str;
            cout<<"Enter the string to be encrypted(Not Chinese):";
            cin>>str;
            char *strc = const_cast<char *>(str.c_str());
            jstring jstr = jenv->NewStringUTF(strc);
            jmethodID mid = jenv->GetStaticMethodID(cls,"encrypt","(Ljava/lang/String;)V");
            jenv->CallStaticVoidMethod(cls,mid,jstr);
        }
        else if(opera=='D'||opera=='d'){
            string str;
            cout<<"Enter the string to be decrypted(Not Chinese):";
            cin>>str;
            char *strc = const_cast<char *>(str.c_str());
            jstring jstr = jenv->NewStringUTF(strc);
            jmethodID mid = jenv->GetStaticMethodID(cls,"decrypt","(Ljava/lang/String;)V");
            jenv->CallStaticVoidMethod(cls,mid,jstr);
        }
        else{
            cout<<"Invalid Input! ";
        }
        cout<<"Please enter the new operation('q' to quit): ";
        cin>>opera;
        continue;
    }
    jvm->DestroyJavaVM();

    return 0;
}
