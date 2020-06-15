import java.util.ArrayList;
import java.util.List;


/**
 * 符号表
 *
 * @author Jason
 */
public class AllSymbol {

	List<PerSymbol> allSymbol;

	/**
	 * 常量类型用1表示
	 */
	private final int con = 1;
	/**
	 * 变量类型用2表示
	 */
	private final int var = 2;
	/**
	 * 过程类型用3表示
	 */
	private final int proc = 3;

	private int ptr = 0;

	public AllSymbol() {
		allSymbol = new ArrayList<>();
	}

	/**
	 * 向符号表中插入常量
	 *
	 * @param name    name
	 * @param level   level
	 * @param value   value
	 * @param address address
	 */
	public void enterConst(String name, int level, int value, int address) {
		allSymbol.add(new PerSymbol(con, value, level, address, 0, name));
		ptr++;
	}

	/**
	 * 向符号表中插入变量
	 *
	 * @param name    name
	 * @param level   level
	 * @param address address
	 */
	public void enterVar(String name, int level, int address) {
		allSymbol.add(new PerSymbol(var, level, address, 0, name));
		ptr++;
	}

	/**
	 * 向符号表中插入过程
	 *
	 * @param name    name
	 * @param level   level
	 * @param address address
	 */
	public void enterProc(String name, int level, int address) {
		allSymbol.add(new PerSymbol(proc, level, address, 0, name));
		ptr++;
	}

	/**
	 * 在符号表当前层查找变量是否存在,暴搜
	 *
	 * @param name  name
	 * @param level level
	 * @return bool
	 */
	public boolean isNowExists(String name, int level) {
		for (PerSymbol perSymbol : allSymbol) {
			if (perSymbol.getName().equals(name) && perSymbol.getLevel() == level) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 在符号表之前层查找符号是否存在,暴搜
	 *
	 * @param name  name
	 * @param level level
	 * @return bool
	 */
	public boolean isPreExists(String name, int level) {
		for (PerSymbol perSymbol : allSymbol) {
			if (perSymbol.getName().equals(name) && perSymbol.getLevel() <= level) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 按名称查找变量
	 *
	 * @param name name
	 * @return var
	 */
	public PerSymbol getSymbol(String name) {
		for (int i = allSymbol.size() - 1; i >= 0; i--) {
			if (allSymbol.get(i).getName().equals(name)) {
				return allSymbol.get(i);
			}
		}
		return null;
	}

	/**
	 * 查找当前层所在的过程
	 *
	 * @return int
	 */
	public int getLevelProc() {
		for (int i = allSymbol.size() - 1; i >= 0; i--) {
			if (allSymbol.get(i).getType() == proc) {
				return i;
			}
		}
		return -1;
	}

	public List<PerSymbol> getAllSymbol() {
		return allSymbol;
	}

	public int getPtr() {
		return ptr;
	}

	public int getCon() {
		return con;
	}

	public int getVar() {
		return var;
	}

	public int getProc() {
		return proc;
	}
}
