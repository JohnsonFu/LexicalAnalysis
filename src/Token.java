/**
 * Created by fulinhua on 2016/11/8.
 */
public class Token {

private int code;
    private String name;
    public Token(int code,String name){
        this.code=code;
        this.name=name;
    }
    public void PrintToken(){
        System.out.println("<"+this.code+","+this.name+">");
    }
    public int getCode(){
        return this.code;
    }
    public String getName(){
        return this.name;
    }
}
