/**
 * 单句Pcode语句类
 *
 * @author Jason
 */
public class PerPcode {

	private final Operator f;
	private final int l;
	private int a;

	PerPcode(Operator f, int l, int a) {
		this.f = f;
		this.l = l;
		this.a = a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public Operator getF() {
		return f;
	}

	public int getL() {
		return l;
	}

	public int getA() {
		return a;
	}
}
