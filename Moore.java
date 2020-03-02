import java.util.*;
/**

*Description: This program is a Moore Machine. Enter A, B, C, or D and this program will find what your final state would be

*Class: Fall - COSC 2425.5340

*Lab3: Moore Machine

*Date: 02/20/2020

*@author  Justin Rapczynski

*@version 1.0.0

*/ 
 public class Moore {
	
    private char state;
    private String output;
    int zero;
    int one;
    
    public Moore(char state,String output,int zero,int one) {
        this.state = state;
        this.output = output;
        this.one = one;
        this.zero = zero;
    }
    static boolean checkstate(char s)
    {
        for(int i=0;i<4;i++)
        {
            if(s == 'A' || s == 'B' || s == 'C' || s == 'D')
                return true;
        }
       
        return false;
    }
    
    static boolean checkstring(String s)
    {
        for(int i=0;i<s.length();i++)
            {
                if(!(s.charAt(i)=='0'||s.charAt(i)=='1'))
                    return false;
            }
       
        return true;  
    }
    
    char getState(){
        return this.state;
    }
    private static void extracted() {
		return;
	}
    String getOutput(){
        return this.output;
    }
    public  static void main(String args[])throws java.lang.Exception
    {
     
        
        Moore[] mooreStates = new Moore[5];
        
        mooreStates[0] = new Moore(' ',"",0,0);
        mooreStates[1] = new Moore('A',"00 ",2,4);
        mooreStates[2] = new Moore('B',"01 ",2,3);
        mooreStates[3] = new Moore('C',"11 ",4,3);
        mooreStates[4] = new Moore('D',"10 ",3,1);
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the initial state:");
        
        char user = scan.next().charAt(0);
      
        
        if(!checkstate(user))
        {
            System.out.println("ERROR: you must enter A, B, C, or D. TERMINATING PROGRAM");
            	extracted();
			
        }
        
        System.out.println("Please enter the input:");
        String input = scan.next();
        
        if(!checkstring(input))
        {
            System.out.println("ERROR: you must enter a series of 1's and 0's. TERMINATING PROGRAM");
            	extracted();
			
        }
        
        
        int strt = 0;
        
        System.out.println("\nThe output is:");
        
        for(int i=0;i<mooreStates.length;i++)
        {
            if(mooreStates[i].getState() == user)
            {
                strt = i;
                System.out.print(mooreStates[strt].getOutput());
                break;
            }
        }
       
        for(int i=0;i<input.length();i++)
        {
            char ip = input.charAt(i);
            if(ip == '0') {
                strt = mooreStates[strt].zero;
            }
                else if(ip == '1') {
                strt = mooreStates[strt].one;
                }
            else{
                {System.out.println("Invalid input character.");break;}
            }
            System.out.print(mooreStates[strt].getOutput());
        }
        
        System.out.println("\nThe final state is: " + mooreStates[strt].getState());
        scan.close();
    }
	
}