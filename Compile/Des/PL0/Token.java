/**
 * token类
 *
 * @author Jason
 */
public class Token {

	/**
	 * token的类别
	 */
	private final SymType st;
	/**
	 * token所在行，错误处理使用
	 */
	private final int line;
	/**
	 * token的值，只有标识符和常量有值
	 */
	private final String value;

	public Token(SymType st, int line, String value) {
		this.st = st;
		this.line = line;
		this.value = value;
	}

	public SymType getSt() {
		return st;
	}

	public int getLine() {
		return line;
	}

	public String getValue() {
		return value;
	}
}
