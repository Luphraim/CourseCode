/**
 * PerSymbol类
 *
 * @author Jason
 */
public class PerSymbol {

    /**
     * 表示常量、变量或过程
     */
    private final int type;
    /**
     * 表示常量或变量的值
     */
    private int value;
    /**
     * 嵌套层次
     */
    private final int level;
    /**
     * 相对于所在嵌套过程基地址的地址
     */
    private final int address;
    /**
     * 表示常量，变量，过程所占的大小(这一项其实默认为0， 并没有用到)
     */
    private final int size;
    /**
     * 变量、常量或过程名
     */
    private final String name;

    public PerSymbol(int type, int value, int level, int address, int size, String name) {
        this.type = type;
        this.value = value;
        this.level = level;
        this.address = address;
        this.size = size;
        this.name = name;
    }

    public PerSymbol(int type, int level, int address, int size, String name) {
        //专为变量声明和过程声明写的构造函数
        //变量和过程声明时没有初始值
        this.type = type;
        this.level = level;
        this.address = address;
        this.size = size;
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public int getLevel() {
        return level;
    }

    public int getAddress() {
        return address;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
