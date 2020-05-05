package RMIQuickSort;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import java.util.*;

import RMIQuickSort.QuickSort;

public class QuickSortClient {

    public static void main(String[] args) {
        try {
            QuickSort quickSort = (QuickSort) Naming.lookup("rmi://localhost:1099/QuickSortServer");
            Map<String, String> info = quickSort.getInfo();
            System.out.println("Init of Array: " + info.get("OldArray"));
            System.out.println("Result of QuickSort: " + info.get("Array"));
            System.out.println("Run Time: " + info.get("RunTime") + "ms");
            System.out.println("Used Mem: " + info.get("UsedMem") + "Byte");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
