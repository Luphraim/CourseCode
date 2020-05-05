package RMIQuickSort;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import RMIQuickSort.QuickSortImpl;

public class QuickSortServer {

    public static void main(String[] args) {
        try {
            Naming.bind("rmi://localhost:1099/QuickSortServer", new QuickSortImpl());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
