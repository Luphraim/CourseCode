package xmu.edu.cn;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class StudentMain {
	public static void main(String[] args) throws Exception {
  	  ApplicationContext context = 
			  new AnnotationConfigApplicationContext(ClassRoomConfig.class);
  
  
  Service teacher = context.getBean(Service.class);
   teacher.serve();  
}
	
}


// Silencing cell phones...
// Taking seats...
// Lai : Giving a lecture...
// Class is over， is there any Questions?


