#include<iostream>
#include<fstream>
#include<stdlib.h>
#include<string>
#include<cmath>

using namespace std;

constexpr auto IDN = 1;
constexpr auto ASG = 2;
constexpr auto KEY = 3;
constexpr auto ERROR = -1;

// 全局变量
char ch;                    // 每个要比对的字符

// 判断是否为操作符
bool Opt() {
    if (ch == '+') {
        cout << "+         _" << endl;
        return true;
    }
    else if (ch == '-') {
        cout << "-         _" << endl;
        return true;
    }
    else if (ch == '*') {
        cout << "*         _" << endl;
        return true;
    }
    else if (ch == '/') {
        cout << "/         _" << endl;
        return true;
    }
    else if (ch == '>') {
        cout << ">         _" << endl;
        return true;
    }
    else if (ch == '<') {
        cout << "<         _" << endl;
        return true;
    }
    else if (ch == '=') {
        cout << "=         _" << endl;
        return true;
    }
    else if (ch == '(') {
        cout << "(         _" << endl;
        return true;
    }
    else if (ch == ')') {
        cout << ")         _" << endl;
        return true;
    }
    else if (ch == ';') {
        cout << ";         _" << endl;
        return true;
    }
    return false;
}

// 判断是否为关键字
bool Key(char buf[]) {
    if (strcmp(buf, "if") == 0) {
        cout << "IF        _" << endl;
        return true;
    }
    else if (strcmp(buf, "then") == 0) {
        cout << "THEN      _" << endl;
        return true;
    }
    else if (strcmp(buf, "else") == 0) {
        cout << "ELSE      _" << endl;
        return true;
    }
    else if (strcmp(buf, "while") == 0) {
        cout << "WHILE     _" << endl;
        return true;
    }
    else if (strcmp(buf, "do") == 0) {
        cout << "DO        _" << endl;
        return true;
    }
    return false;
}

// 输出标识符
void Idn(char buf[]) {
    printf("IDN       %s\n", buf);
}

// 判断当前字符是否为字母
bool isAlphabet() {
    return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
}

// 判断当前字符是否为数字
bool isDigit() {
    return ch >= '0' && ch <= '9';
}

// 判断是否为小数点
bool isDecimal() {
    return ch == '.';
}

// 十六进制
void _Hex(char buf[]) {
    bool isInt = true;
    int site = 0;
    for (int i = 2; buf[i]; i++) {
        if (buf[i] == '.') {
            isInt = false;
            site = i;
            break;
        }
    }
    if (isInt) {
        long sum = 0;
        for (int i = 2, t; buf[i]; i++) {
            if (buf[i] <= '9') {
                t = buf[i] - '0';
            }
            else {
                t = buf[i] - 'a' + 10;
            }
            sum = sum * 16 + t;
        }
        cout << "INT16     " << sum << endl;
    }
    else {
        double sum = 0;
        for (int i = 2, t; buf[i]; i++) {
            if (i < site) {
                if (buf[i] <= '9') {
                    t = buf[i] - '0';
                }
                else {
                    t = buf[i] - 'a' + 10;
                }
                sum = sum * 16 + t;
            }
            else if (i == site) {
                continue;
            }
            else {
                if (buf[i] <= '9') {
                    t = buf[i] - '0';
                }
                else {
                    t = buf[i] - 'a' + 10;
                }
                sum += t * pow(16, site - i);
            }
        }
        cout << "INT16     " << sum << endl;
    }
}

// 十进制
void _Dec(char buf[]) {
    bool isInt = true;
    int site = 0;
    for (int i = 0; buf[i]; i++) {
        if (buf[i] == '.') {
            isInt = false;
            site = i;
            break;
        }
    }
    if (isInt) {
        long sum = 0;
        for (int i = 0; buf[i]; i++) {
            sum = sum * 10 + buf[i] - '0';

        }
        cout << "INT10     " << sum << endl;
    }
    else {
        long sum1 = 0, sum2 = 0;
        for (int i = 0; buf[i]; i++) {
            if (i < site) {
                sum1 = sum1 * 10 + buf[i] - '0';
            }
            else if (i == site) {
                continue;
            }
            else {
                sum2 = sum2 * 10 + buf[i] - '0';
            }
        }
        cout << "REAL10    " << sum1 << "." << sum2 << endl;
    }
}

// 八进制
void _Oct(char buf[]) {
    bool isInt = true;
    int site = 0;
    for (int i = 2; buf[i]; i++) {
        if (buf[i] == '.') {
            isInt = false;
            site = i;
            break;
        }
    }
    if (isInt) {
        long sum = 0;
        for (int i = 2; buf[i]; i++) {
            sum = sum * 8 + buf[i] - '0';
        }
        cout << "INT8      " << sum << endl;
    }
    else {
        double sum = 0;
        for (int i = 2; buf[i]; i++) {
            if (i < site) {
                sum = sum * 8 + buf[i] - '0';
            }
            else if (i == site) {
                continue;
            }
            else {
                sum += (buf[i] - '0') * pow(8, site - i);
            }
        }
        cout << "INT8      " << sum << endl;
    }
}

// 分析函数
int scan() {

    int cnt = 0;
    char buf[1024];        // 每个要分析的单词

    // 若当前字符为空，或者为制表符（9）或者为换页符（12）则取下一个字符，直到当前字符合法
    ch = getchar();
    while (ch == ' ' || ch == 9 || ch == 12) {
        ch = getchar();
    }

    // 若当前字符为换行符（10），则返回0，结束程序
    if (ch == 10) {
        return 0;
    }
    else if (Opt()) {}
    else if (isAlphabet()) {
        buf[cnt++] = ch;
        ch = getchar();
        while (isAlphabet() || isDigit()) {
            buf[cnt++] = ch;
            ch = getchar();
        }
        ungetc(ch, stdin);
        buf[cnt] = '\0';

        if(Key(buf)){}
        else { Idn(buf); }
    }
    else if (isDigit()) {
        string str = "";    // 获取前两位字符以判断
        str += ch;
        buf[cnt++] = ch;
        ch = getchar();
        str += ch;
        while (isAlphabet() || isDigit() || isDecimal()) {
            buf[cnt++] = ch;
            ch = getchar();
        }
        ungetc(ch, stdin);
        buf[cnt] = '\0';

        if (str == "0o") {
            _Oct(buf);
        }
        else if (str == "0x") {
            _Hex(buf);
        }
        else {
            _Dec(buf);
        }
    }
}

int main() {
    while (1) {
        ch = scan();
        if (ch == 0) {
            break;
        }
        if (ch == ERROR) {
            cout << "Input error" << endl;
            system("pause");
            break;
        }
    }
    system("pause");
    return 0;
}