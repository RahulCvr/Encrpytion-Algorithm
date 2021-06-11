import java.util.*;
import java.lang.Math;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
        
public class Main {

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        String text;
        int i;
        System.out.println("Enter the text");
        text = inp.nextLine();
        String[] ch = new String[text.length()];
        
        for (i = 0; i < text.length(); i++) {
            ch[i] = Character.toString(text.charAt(i));
        }
        String[] Etext = new String[text.length()];
        
        System.out.print(text + " After encryption : ");
        int count=0;
        for(i=0;i<text.length();i++){
            Etext[i] = Encrypt(ch[i]);
            System.out.print(Etext[i] + " ");
            count = count + Etext[i].length();
        }
        System.out.println("\nCount :"+count);
        
        System.out.println("Key : ");
        alphaNum(text);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println(dtf.format(now));
    }
    
    public static String Encrypt(String s){
        String Es,Res;
        int EncryptKey;
        
        byte[] bytes = s.getBytes();
        
        EncryptKey = secNum();
        
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
         {
            int val = b;
            for (int j = 0; j < 8; j++)
            {
               binary.append((val & 128) == 0 ? 0 : 1);
               val <<= 1;
            }
            binary.append(' ');
         }
        
        Es = binary.toString();
        Es = Es.replace('0','a');
        Es = Es.replace('1','b');
        Es = Es.replace('a','1');
        Es = Es.replace('b','0');
        Es = Es.replace(" ","");
        long l=Long.parseLong(Es);
        
        int val=0,k=0;
        while (l > 0) {
            val+= Math.pow(2, k++) * (l % 10);
            l /= 10;
        }
        val = val*EncryptKey;
        Res = Integer.toHexString(val);
        Res = Res.toUpperCase();
        
        return Res;
    }
    
    public static int secNum(){
        int num1,fNum;
        int num2 = (int) (new Date().getTime()/1000);
        num1 = num2 % 1000000;
        num1 = num1 + num2%100000;
        fNum = num2/num1;
        
        return fNum;
    }
    
    public static void alphaNum(String str){
        str = str.toUpperCase();
        char[] chalp  = str.toCharArray();
        System.out.println("-------------");
        for(char c : chalp){
            int temp = (int)c;
            int temp_integer = 64; //for upper case
            if(temp<=90 & temp>=65)
                System.out.print(temp-temp_integer + " ");
            
        }
        System.out.println("\n-------------");
    }
}
        
