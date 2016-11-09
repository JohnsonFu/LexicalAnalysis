/**
 * Created by fulinhua on 2016/10/24.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Analysis {
    private static char[] data=new char[200];//获得每一行的字符串
    private static char[] word;
    private static int code;
    private static ArrayList<Token> list;
    private static int num;
    private static char ch;
    private static String[] reserveword = {"package","class", "public","static","void","main","args","protected", "int", "bool","char", "float", "Double",
            "String", "if", "else", "do", "while", "try", "catch", "switch",
            "case" ,"for" };
    private static int p,sp;


    private static void readFile() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileaddr = "src/input.txt";
        BufferedReader br2 = new BufferedReader(new InputStreamReader(
                new FileInputStream(new File(fileaddr))));
        String fileline = null;
        char[] tmp = null;
        int j = 0;
        while ((fileline = br2.readLine()) != null) {
            tmp = fileline.toCharArray();
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i] == ' '|| tmp[i]=='\t'||tmp[i]=='\n')
                    continue;
                data[p++] = tmp[i];
            }
            data[p++] = '\n';
            data[p]='@';
        }

        br2.close();
    }

    public static  boolean isUpper(char a){//判断是否是大写字母
        if(a>='A'&&a<='Z'){
            return true;
        }else{
            return false;
        }
    }
    public static boolean isLower(char a){
        if(a>='a'&&a<='z'){
            return true;
        }else{
            return false;
        }
    }
    public static boolean isDigit(char a){
        if(a>='0'&&a<='9'){
            return true;
        }else{
            return false;
        }
    }

    public static String chartostring(char[] a){
        int len=0;
        for(int i=0;i<a.length;i++){
            if(a[i]!='\0')
                len++;
        }
        return String.valueOf(a).substring(0,len);
    }

    public static int isReserveword(String a){
        for(int i=0;i<reserveword.length;i++){
            if(a.equals(reserveword[i])){
                return i;
            }
        }

            return -1;

    }

     public static String toType(int code){
       if(code>=1&&code<=22){
           return "关键字";
       }
       if(code>=23&&code<=40){
           return code+"";
       }
       if(code>=41&&code<=51){
           return "界符号";
       }
       if(code==56){
           return "id";
       }
       if(code==77){
           return "不合法的标识符";
       }
     else  return "未识别";
     }

    public static void Scanner(){
        word = new char[20];
        ch = data[p++];

        if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {// 可能是保留字或变量名（保留字优先）
            sp = 0;
            while ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')) {
                word[sp++] = ch;
                ch = data[p++];
                word[sp] = '\0';
                for (int i = 0; i < reserveword.length; i++) {
                    if (chartostring(word).equals(reserveword[i])) {
                        code = i + 1;
                        p --;
                        return;
                    }
                }
            }
            word[sp++] = '\0';
            p--; // 放回多读的
            code = 56;

        } else if (ch >= '0' && ch <= '9') { // 可能是正常数
            num = 0;
            while (ch >= '0' && ch <= '9') {
                num = num * 10 + ch - '0';
                ch = data[p++];
            }
            p--;
            code = 57;
            if (num < 0)
                code = -2;// 正数超过最大值变成负数，报错
        } else { // 其他字符
            sp = 0;
            word[sp++] = ch;
            switch (ch) {
                case '+':
                    ch = data[p++];
                    if (ch == '=') {// +=
                        code = 23;
                        word[sp++] = ch;
                    } else {// +
                        code = 22;
                        p--;
                    }
                    break;
                case '-':
                    ch = data[p++];
                    if (ch == '=') {// -=
                        code = 25;
                        word[sp++] = ch;
                    } else {// -
                        code = 24;
                        p--;
                    }
                    break;
                case '*':
                    ch = data[p++];
                    if (ch == '=') {// *=
                        code = 27;
                        word[sp++] = ch;
                    } else if (ch == '/') {// */
                        code = 44;
                        word[sp++] = ch;
                    } else {// *
                        code = 26;
                        p--;
                    }
                    break;
                case '/':
                    ch = data[p++];
                    if (ch == '=') {// /=
                        code = 29;
                        word[sp++] = ch;
                    } else if (ch == '/') {// //
                        code = 42;
                        word[sp++] = ch;
                    } else if (ch == '*') {// /*
                        code = 26;
                        word[sp++] = ch;
                    } else {// /
                        code = 28;
                        p--;
                    }
                    break;
                case '=':
                    ch = data[p++];
                    if (ch == '=') { // ==
                        code = 31;
                        word[sp++] = ch;
                    } else { // =
                        code = 30;
                        p--;
                    }
                    break;
                case '<':
                    ch = data[p++];
                    if (ch == '=') { // <=
                        code = 39;
                        word[sp++] = ch;
                    } else { // <
                        code = 38;
                        p--;
                    }
                    break;
                case '>':
                    ch = data[p++];
                    if (ch == '=') { // >=
                        code = 41;
                        word[sp++] = ch;
                    } else { // >
                        code = 40;
                        p--;
                    }
                    break;
                case '&':
                    ch = data[p++];
                    if (ch == '&') { // &&
                        code = 33;
                        word[sp++] = ch;
                    } else { // &
                        code = 32;
                        p--;
                    }
                    break;
                case '^':
                    ch = data[p++];
                    if (ch == '|') { // ||
                        code = 35;
                        word[sp++] = ch;
                    } else { // |
                        code = 34;
                        p--;
                    }
                    break;
                case '!':
                    ch = data[p++];
                    if (ch == '=') { // !=
                        code = 37;
                        word[sp++] = ch;
                    } else { // !
                        code = 36;
                        p--;
                    }
                    break;

                case '(':code = 45; break;
                case ')':code = 46; break;
                case '[':code = 47; break;
                case ']':code = 48; break;
                case '{':code = 49; break;
                case '}':code = 50; break;
                case ',':code = 51; break;
                case ':':code = 52; break;
                case ';':code = 53; break;
                case '\'':code = 54; break;
                case '\"':code = 55; break;
                case '\n':code = -1; break;
                default:code = -3; break;
            }
        }
 }


 public ArrayList<Token> test() throws IOException{
     readFile();
     p = 0;
     list = new ArrayList<Token>();
     do {
         Scanner();
         switch (code) {
             case 57:
                 //  System.out.println("<数字,"+num+">");
                 Token token = new Token(57, num + "");
                 list.add(token);
                 break;
             case -1:
                 break;
             default:
                 //  System.out.println("<"+toType(code)+","+chartostring(word)+">");
                 Token token2 = new Token(code, chartostring(word));
                 list.add(token2);
         }
     } while (data[p] != '@');
   //  for (int i = 0; i < list.size(); i++) {
     //    list.get(i).PrintToken();
     //}
return list;
    }

//    public static void main(String[] args) throws IOException {
//       readFile();
//        p=0;
//        list=new ArrayList<Token>();
//      do {
//          Scanner();
//          switch(code){
//              case 57:
//                //  System.out.println("<数字,"+num+">");
//                  Token token=new Token(57,num+"");
//                  list.add(token);
//                  break;
//              case -1:
//                  break;
//              default:
//                //  System.out.println("<"+toType(code)+","+chartostring(word)+">");
//                  Token token2=new Token(code,chartostring(word));
//                  list.add(token2);
//          }
//      }while(data[p]!='@');
//        for(int i=0;i<list.size();i++){
//            list.get(i).PrintToken();
//        }
//    }

}
