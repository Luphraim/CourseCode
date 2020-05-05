package xmu.edu.cn;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.*;


/**
 * {@link Service} with hard-coded input data.
 */
@Component
public class Student {
	//private String name;
     private Service service;

     @Autowired 
     public Student(Service s){
    	// name="";
    	 service=s;
     }
     
//     @Autowired 
//     public Student(Teacher s){
//    	 name="";
//    	 service=s;
//     }
//     @Autowired 
//     public Student(String n, Service s){
//    	 name=n;
//    	 service=s;
//     }
//     @Autowired 
//     public void setService(Service s){
//    	 service=s;
//     }
     public void learn(){
    	 	 service.serve();
    	 	//System.out.println("Have consumed a service!\n");
     }
}
