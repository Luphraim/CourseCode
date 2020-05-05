package aspect;

import model.UserDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Aspect
public class SafetyVerification {

    @Pointcut("execution(* controller.InfoController.search())")
    public void searchPoint() {

    }

    @Pointcut("execution(* controller.InfoController.update())")
    public void updatePoint() {

    }

    @Pointcut("execution(* controller.InfoController.aggregate())")
    public void aggregate() {

    }

    @Before("searchPoint()")
    public void beforeSearch(ProceedingJoinPoint joinPoint) throws Throwable {
        // 拿到session并从session中拿到user信息
        HttpSession session = (HttpSession) HttpServletRequest.class.getMethod("getSession").invoke(joinPoint.getArgs()[0]);
        UserDTO user = (UserDTO) session.getAttribute("userDetail");

        if (user == null) {
            // 未登录，重定向到登录界面
            System.out.println("Not Login!");
            HttpServletResponse.class.getMethod("sendRedirect", String.class).invoke(joinPoint.getArgs()[1], "/login");
        } else if (user.getPrivilege() >= 1) {

            joinPoint.proceed();
        }
    }

    @Before("updatePoint()")
    public void beforeUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 拿到session并从session中拿到user信息
        HttpSession session = (HttpSession) HttpServletRequest.class.getMethod("getSession").invoke(joinPoint.getArgs()[0]);
        UserDTO user = (UserDTO) session.getAttribute("userDetail");

        if (user == null) {
            // 未登录，重定向到登录界面
            System.out.println("Not Login!");
            HttpServletResponse.class.getMethod("sendRedirect", String.class).invoke(joinPoint.getArgs()[1], "/login");
        } else if (user.getPrivilege() != 2) {
            // 权限不足，重定向到错误页面
            System.out.println("No Permission!");
            HttpServletResponse.class.getMethod("sendRedirect", String.class).invoke(joinPoint.getArgs()[1], "/error.html");
        } else {
            joinPoint.proceed(joinPoint.getArgs());
        }
    }

    @Before("aggregate()")
    public void beforeAggregate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 拿到session并从session中拿到user信息
        HttpSession session = (HttpSession) HttpServletRequest.class.getMethod("getSession").invoke(joinPoint.getArgs()[0]);
        UserDTO user = (UserDTO) session.getAttribute("userDetail");

        if (user == null) {
            // 未登录，重定向到登录界面
            System.out.println("Not Login!");
            HttpServletResponse.class.getMethod("sendRedirect", String.class).invoke(joinPoint.getArgs()[1], "/login");
        } else if (user.getPrivilege() != 3) {
            // 权限不足，重定向到错误页面
            System.out.println("No Permission!");
            HttpServletResponse.class.getMethod("sendRedirect", String.class).invoke(joinPoint.getArgs()[1], "/error.html");
        } else {
            joinPoint.proceed();
        }
    }
}
