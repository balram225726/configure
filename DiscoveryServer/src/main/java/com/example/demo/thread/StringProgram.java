package com.example.demo.thread;

import java.util.Scanner;

public class StringProgram {

	public static void main(String args[]) {
		
//		Scanner sc = new Scanner(System.in);
//        System.out.println("Enter input string to be cleaned from white spaces...!");
//        String inputString = sc.nextLine();
//        char[] charArray = inputString.toCharArray();
//        String stringWithoutSpaces = "";
//        for (int i = 0; i < charArray.length; i++) 
//        {
//            if ( (charArray[i] != ' ') && (charArray[i] != '\t') )
//            {
//                stringWithoutSpaces = stringWithoutSpaces + charArray[i];
//            }
//        }
//         
//        System.out.println("Input String : "+inputString);
//         
//        System.out.println("Input String Without Spacebs : "+stringWithoutSpaces);
//         
//        sc.close();
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter string now");
		String inputString = sc.nextLine();
		
		char[] charArray = inputString.toCharArray();
		String finalString="";
		
		for(int i=0;i<charArray.length;i++) {
			if(charArray[i]!=' ' && charArray[i]!='\t'){
				finalString =finalString+charArray[i];
			}
			
		}
		
		System.out.println(finalString);                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	}
	

}
