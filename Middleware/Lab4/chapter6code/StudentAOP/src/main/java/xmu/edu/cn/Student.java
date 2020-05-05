package xmu.edu.cn;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
@Aspect
public class Student {
	//切点就定义了“何处”
  @Pointcut("execution(** xmu.edu.cn.Teacher.serve(..))")   //切入点 pointcut
  public void giveLecture() {}
  
  //通知定义了“什么”和“何时”
  @Before("giveLecture()")  // 连接点  Joinpoint
  public void silenceCellPhones() {  //通知 advice 
	  System.out.println("Silencing cell phones...");
  } 
 
 @Before("giveLecture()")
  public void takeSeats() {
    System.out.println("Taking seats...");
  }
  @AfterReturning("giveLecture()")
  public void askQuestion() {
    System.out.println("Class is over, is there any Questions?");
  }
  @AfterThrowing("giveLecture()")
  public void haveClassAccident() {
    System.out.println("A teaching accident, ask for an investigation... ");
  }
}