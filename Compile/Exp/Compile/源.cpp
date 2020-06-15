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

// ȫ�ֱ���
char ch;                    // ÿ��Ҫ�ȶԵ��ַ�

// �ж��Ƿ�Ϊ������
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

// �ж��Ƿ�Ϊ�ؼ���
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

// �����ʶ��
void Idn(char buf[]) {
    printf("IDN       %s\n", buf);
}

// �жϵ�ǰ�ַ��Ƿ�Ϊ��ĸ
bool isAlphabet() {
    return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
}

// �жϵ�ǰ�ַ��Ƿ�Ϊ����
bool isDigit() {
    return ch >= '0' && ch <= '9';
}

// �ж��Ƿ�ΪС����
bool isDecimal() {
    return ch == '.';
}

// ʮ������
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

// ʮ����
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

// �˽���
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

// ��������
int scan() {

    int cnt = 0;
    char buf[1024];        // ÿ��Ҫ�����ĵ���

    // ����ǰ�ַ�Ϊ�գ�����Ϊ�Ʊ����9������Ϊ��ҳ����12����ȡ��һ���ַ���ֱ����ǰ�ַ��Ϸ�
    ch = getchar();
    while (ch == ' ' || ch == 9 || ch == 12) {
        ch = getchar();
    }

    // ����ǰ�ַ�Ϊ���з���10�����򷵻�0����������
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
        string str = "";    // ��ȡǰ��λ�ַ����ж�
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