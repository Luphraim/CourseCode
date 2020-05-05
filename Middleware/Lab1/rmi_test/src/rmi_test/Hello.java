package rmi_test;

import java.rmi.*;

public interface Hello extends java.rmi.Remote{
    String sayHello(String name) throws java.rmi.RemoteException;
}
