/**
 * Created by fulinhua on 2016/11/8.
 */
import java.util.ArrayList;
public class Queue {
private ArrayList<Token> list;
    public Queue(ArrayList<Token> list){
        this.list=list;
        this.list.add(new Token(101,"$"));
    }
    public Token get(){
        return list.get(0);
    }
    public void dequeue(){
        this.list.remove(0);
    }
    public void enqueue(Token token){
        this.list.add(token);
    }


    public void printQueue(){
        System.out.print("输入队列:");
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i).getName()+" ");
        }
    }

    public boolean isEmpty(){
        if(list.size()==0){
            return true;
        }
        else{
            return false;
        }
    }
}
