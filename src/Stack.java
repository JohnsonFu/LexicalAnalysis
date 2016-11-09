/**
 * Created by fulinhua on 2016/11/8.
 */
import java.util.ArrayList;
public class Stack {
    private ArrayList<String> list;
    public Stack(){
        list=new ArrayList<String>();
        list.add("$");
    }
    public void push(String s){
        list.add(s);
    }
    public void pop(){
        list.remove(list.size()-1);
    }
    public String get(){
        return list.get(list.size()-1);
    }
    public void printStack(){
        System.out.print("栈:");
        for(int i=list.size()-1;i>=0;i--){
            System.out.print(list.get(i)+"");
        }
        System.out.print("      ");
    }
    public int getSize(){
        return list.size();
    }

}
