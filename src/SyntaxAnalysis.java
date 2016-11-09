import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by fulinhua on 2016/11/8.
 */
public class SyntaxAnalysis {

    private static String[] productions={"E->TE'","E'->AE'","E'->#","A->+T","A->-T","T->FT'","T'->BT'","T'->#",
"B->*F","B->/F","F->GF'","F'->^GF'","F'->#","G->(E)","G->id","G->num"};
private static Stack stack;
    private static Queue queue;
    public static void main(String[] args)throws IOException{
        Analysis sis=new Analysis();
        ArrayList<Token>input=sis.test();
         queue=new Queue(input);
         stack=new Stack();
        stack.push("E");
boolean t=true;
     while(stack.getSize()!=0&&t) {
             String state = stack.get();

             int n = queue.get().getCode();
         stack.printStack();
         queue.printQueue();
             t=predict(state, n);
         }

    }

    public static int StrToCode(String s){
        if(s=="num"){
            return 57;
        }
        if(s=="id"){
            return 56;
        }
        if(s==")"){
            return 46;
        }
        if(s=="("){
            return 45;
        }
        if(s=="+"){
            return 22;
        }
        if(s=="-"){
            return 24;
        }
        if(s=="*"){
            return 26;
        }
        if(s=="/"){
            return 28;
        }
        if(s=="^"){
            return 34;
        }else {
            return 0;
        }
    }




   public static boolean predict(String nt,int t ) {

       if (nt == "num" || nt == "id" || nt == "+" || nt == "-" || nt == "*" || nt == "/" || nt == "(" || nt == ")"||nt=="^") {
           int m = queue.get().getCode();
          int code=StrToCode(nt);
           if (m == code) {
               stack.pop();
               queue.dequeue();
               System.out.println("      匹配:"+nt);
               return true;
           } else {
               System.out.println("输入不合法,分析已停止");
               return false;
           }
       } else {
           if (nt == "E") {
               if (t == 45 || t == 56 || t == 57) {
                   stack.pop();
                   stack.push("E'");
                   stack.push("T");
                   System.out.println("      动作:"+productions[0]);
                   return true;
               }
               else {
                   System.out.println("输入不合法,分析已停止");
                   return false;
               }
           }
           if (nt == "E'") {
               if (t == 22 || t == 24) {
                   stack.pop();
                   stack.push("E'");
                   stack.push("A");
                   System.out.println("      动作:"+productions[1]);
                   return true;
               }
               if (t == 46 || t == 101) {
                   stack.pop();
                   System.out.println("      动作:"+productions[2]);
                   return true;
               }
               else {
                   System.out.println("输入不合法,分析已停止");
                   return false;
               }
           }
           if (nt == "T") {
               if (t == 45 || t == 56 || t == 57) {
                   stack.pop();
                   stack.push("T'");
                   stack.push("F");
                   System.out.println("      动作:"+productions[5]);
                   return true;
               }
               else {
                   System.out.println("输入不合法,分析已停止");
                   return false;
               }
           }
           if (nt == "A") {
               if (t == 22) {
                   stack.pop();
                   stack.push("T");
                   stack.push("+");
                   System.out.println("      动作:"+productions[3]);
                   return true;
               }
               if (t == 24) {
                   stack.pop();
                   stack.push("T");
                   stack.push("-");
                   System.out.println("      动作:"+productions[4]);
                   return true;
               }
               else {
                   System.out.println("输入不合法,分析已停止");
                   return false;
               }
           }
           if (nt == "T'") {
               if (t == 22 || t == 24 || t == 46 || t == 101) {
                   stack.pop();
                   System.out.println("      动作:"+productions[7]);
                   return true;
               }
               if (t == 26 || t == 28) {
                   stack.pop();
                   stack.push("T'");
                   stack.push("B");
                   System.out.println("      动作:"+productions[6]);
                   return true;
               }
               else {
                   System.out.println("输入不合法,分析已停止");
                   return false;
               }

           }
           if (nt == "F") {
               if (t == 45 || t == 56 || t == 57) {
                   stack.pop();
                   stack.push("F'");
                   stack.push("G");
                   System.out.println("      动作:"+productions[10]);
                   return true;
               }
               else {
                   System.out.println("输入不合法,分析已停止");
                   return false;
               }
           }
           if (nt == "B") {
               if (t == 26) {
                   stack.pop();
                   stack.push("F");
                   stack.push("*");
                   System.out.println("      动作:"+productions[8]);
                   return true;
               }
               if (t == 28) {
                   stack.pop();
                   stack.push("F");
                   stack.push("/");
                   System.out.println("      动作:"+productions[9]);
                   return true;
               }
               else {
                   System.out.println("输入不合法,分析已停止");
                   return false;
               }
           }
           if (nt == "F'") {
               if (t == 34) {
                   stack.pop();
                   stack.push("F'");
                   stack.push("G");
                   stack.push("^");
                   System.out.println("      动作:"+productions[11]);
                   return true;
               }
               if (t == 22 || t == 24 || t == 26 || t == 28 || t == 101||t==46) {
                   stack.pop();
                   System.out.println("      动作:"+productions[12]);
                   return true;
               }
               else {
                   System.out.println("输入不合法,分析已停止");
                   return false;
               }
           }


           if (nt == "G") {
               if (t == 45) {
                   stack.pop();
                   stack.push(")");
                   stack.push("E");
                   stack.push("(");
                   System.out.println("      动作:"+productions[13]);
                   return true;
               }
               if (t == 56) {
                   stack.pop();
                   stack.push("id");
                   System.out.println("      动作:"+productions[14]);
                   return true;
               }
               if (t == 57) {
                   stack.pop();
                   stack.push("num");
                   System.out.println("      动作:"+productions[15]);
                   return true;
               }
               else {
                   System.out.println("输入不合法,分析已停止");
                   return false;
               }
           }

           if (nt == "$") {
              if(t==101) {
                  stack.pop();
                  System.out.println("分析完毕");
              }else{
                  stack.pop();
                  System.out.println("输入不合法,分析已停止");
              }
           }




           return true;
       }
   }
}
