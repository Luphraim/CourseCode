/*************************************************************************
	> File Name: QuickSortImpl.java
	> Author: 
	> Mail: 
	> Created Time: Fri 28 Feb 2020 03:47:23 PM CST
 ************************************************************************/

package RMIQuickSort;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.*;

public class QuickSortImpl extends UnicastRemoteObject implements QuickSort {

	public QuickSortImpl() throws RemoteException {
		super();
	}
        
	public void mySort(int[] arr, int low, int high) throws RemoteException {

		if (low >= high) {
			return;
		}

		int pivot = arr[low];
		int i = low;
		int j = high;

		while (i < j) {
			while (arr[j] >= pivot && i < j) {
				j--;
			}
			while (arr[i] <= pivot && i < j) {
				i++;
			}
			if (i < j) {
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
        }
		arr[low] = arr[i];
		arr[i] = pivot;
		mySort(arr, low, i - 1);
		mySort(arr, i + 1, high);
	}

	public Map<String, String> getInfo() throws RemoteException {
		MemoryMXBean bean = ManagementFactory.getMemoryMXBean();
        long startMem = bean.getNonHeapMemoryUsage().getUsed();
        int[] arr = {6, 4, 3, 2, 7, 9, 1, 8, 5};
        String oldArr = Arrays.toString(arr);
        long startTime = System.currentTimeMillis();
		mySort(arr, 0, arr.length - 1);
        //因为数组太短，快排结束时间变化看不出来就Sleep()一下，以显示获取运行时间的操作是正常运行的
        try {
                Thread.sleep(60);
                   
        } catch (InterruptedException ex) {
                ex.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long endMem = bean.getNonHeapMemoryUsage().getUsed();
		Map<String, String> info = new HashMap<>();
		info.put("OldArray", oldArr);
		info.put("Array", Arrays.toString(arr));
		info.put("RunTime", Long.toString(endTime - startTime));
        info.put("UsedMem", Long.toString(endMem - startMem));
		return info;
	}
}
