import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 词法分析程序
 *
 * @author Jason
 */

public class LexAnalysis {

    private final String[] keyWords = {
            "begin", "end", "if", "then", "else", "const", "procedure", "var", "do", "while", "call", "read", "write", "odd", "repeat", "until"
    };

    /**
     * 存放所有分析出来的token
     */
    private List<Token> allToken;
    /**
     * 当前字符
     */
    private char ch = ' ';
    /**
     * 指向当前字符的指针
     */
    private int searchPtr = 0;
    /**
     * 存放所有源代码
     */
    private char[] buffer;
    /**
     * 当前行
     */
    private int line = 1;

    public LexAnalysis(File file) {
        init();
        BufferedReader bf;
        try {
            bf = new BufferedReader(new FileReader(file));
            String temp1;
            StringBuilder temp2 = new StringBuilder();
            while ((temp1 = bf.readLine()) != null) {
                temp2.append(temp1).append('\n');
            }
            buffer = temp2.toString().toCharArray();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        doAnalysis();
    }

    public List<Token> getAllToken() {
        return allToken;
    }

    private void doAnalysis() {
        while (searchPtr < buffer.length) {
            allToken.add(analysis());
        }
    }

    private Token analysis() {
        //当前正在进行词法分析的字符串
        StringBuilder strToken = new StringBuilder();
        getChar();
        while ((ch == ' ' || ch == '\n' || ch == '\t' || ch == '\0') && searchPtr < buffer.length) {
            if (ch == '\n') {
                line++;
            }
            getChar();
        }
        //到达文件末尾
        if (ch == '$' && searchPtr >= buffer.length) {
            return new Token(SymType.EOF, line, "-1");
        }
        //首位为字母，可能为保留字或者变量名
        if (isLetter()) {
            while (isLetter() || isDigit()) {
                strToken.append(ch);
                getChar();
            }
            retract();
            for (int i = 0; i < keyWords.length; i++) {
                //说明是保留字
                if (strToken.toString().equals(keyWords[i])) {
                    return new Token(SymType.values()[i], line, "-");
                }
            }
            //不是保留字，则为标识符，需要保存值
            return new Token(SymType.IDN, line, strToken.toString());
        } else if (isDigit()) {
            //首位为数字，即为整数
            while (isDigit()) {
                strToken.append(ch);
                getChar();
            }
            retract();
            return new Token(SymType.CONST, line, strToken.toString());
        } else if (ch == '=') {
            return new Token(SymType.EQU, line, "-");
        } else if (ch == '+') {
            return new Token(SymType.ADD, line, "-");
        } else if (ch == '-') {
            return new Token(SymType.SUB, line, "-");
        } else if (ch == '*') {
            return new Token(SymType.MUL, line, "-");
        } else if (ch == '/') {
            return new Token(SymType.DIV, line, "-");
        } else if (ch == '<') {
            getChar();
            if (ch == '=') {
                return new Token(SymType.LESE, line, "-");
            } else if (ch == '>') {
                return new Token(SymType.NEQE, line, "-");
            } else {
                retract();
                return new Token(SymType.LES, line, "-");
            }
        } else if (ch == '>') {
            getChar();
            if (ch == '=') {
                return new Token(SymType.LARE, line, "-");
            } else {
                retract();
                return new Token(SymType.LAR, line, "-");
            }
        } else if (ch == ',') {
            return new Token(SymType.COMMA, line, "-");
        } else if (ch == ';') {
            return new Token(SymType.SEMI, line, "-");
        } else if (ch == '.') {
            return new Token(SymType.POI, line, "-");
        } else if (ch == '(') {
            return new Token(SymType.LBR, line, "-");
        } else if (ch == ')') {
            return new Token(SymType.RBR, line, "-");
        } else if (ch == ':') {
            getChar();
            if (ch == '=') {
                return new Token(SymType.CEQU, line, "-");
            } else {
                retract();
                return new Token(SymType.COL, line, "-");
            }
        }
        return new Token(SymType.EOF, line, "-");
    }

    private void init() {
        allToken = new ArrayList<>();
    }

    private void getChar() {
        if (searchPtr < buffer.length) {
            ch = buffer[searchPtr];
            searchPtr++;
        } else {
            ch = '$';
        }
    }

    private void retract() {
        searchPtr--;
        ch = ' ';
    }

    private boolean isLetter() {
        return Character.isLetter(ch);
    }

    private boolean isDigit() {
        return Character.isDigit(ch);
    }
}
