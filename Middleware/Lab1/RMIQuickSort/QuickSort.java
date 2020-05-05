/*************************************************************************
	> File Name: QuickSort.java
	> Author: 
	> Mail: 
	> Created Time: Fri 28 Feb 2020 03:38:39 PM CST
 ************************************************************************/

package RMIQuickSort;

import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.*;

public interface QuickSort extends Remote {

	void mySort(int[] arr, int low, int high) throws RemoteException;

	Map<String, String> getInfo() throws RemoteException;
}
