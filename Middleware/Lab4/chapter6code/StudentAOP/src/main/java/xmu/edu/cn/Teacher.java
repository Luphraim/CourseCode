package xmu.edu.cn;

public class Teacher implements Service {
	private String name;
		public void serve() {
		// TODO Auto-generated method stub
       System.out.println(name+" : Giving a lecture...");
 	}
		public void setName(String sr){
			name=sr;
		}
//	public static void main(String[] args){
		
//	}
}

