import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {
	static public void main(String[] args) throws Throwable {
		   RealSubject rs = new RealSubject(); // 在这里指定被代理类
		   InvocationHandler ds = new DynamicSubject(rs);
		   
		   Class realCls = rs.getClass();

		   // 以下是一次性生成代理
		   Subject subject = (Subject) Proxy.newProxyInstance(				   
				   //Client.class.getClassLoader(),
				  // ds.getClass().getClassLoader(),
				   realCls.getClassLoader(),
				   realCls.getInterfaces(),
				   ds);
		   
		   subject.request();
		  }
}
