package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        String input;


        System.out.println("== 명언 앱 ==");

        while(true)
        {
            System.out.print("명령) ");
            input = sc.nextLine();
            if(input.equals("종료"))
            {
                System.out.println("명언 앱을 종료합니다.");
                break;
            }


        }

    }


}