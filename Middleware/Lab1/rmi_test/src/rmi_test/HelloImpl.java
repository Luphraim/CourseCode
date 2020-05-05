package rmi_test;

import java.rmi.*;
import java.rmi.server.*;

public class HelloImpl implements Hello{
    public HelloImpl(){}
    public String sayHello(String name){
        return "Hello,"+name+"!";
    }
}
