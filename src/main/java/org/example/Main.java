package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        String input = "";
        String saying = "";
        String author = "";

        int count = 0;


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
            else if(input.equals("등록"))
            {
                System.out.print("명언 : ");
                saying = sc.nextLine();

                System.out.print("작가 : ");
                author = sc.nextLine();

                count ++;

                System.out.printf("%d 번 명언이 등록되었습니다. \n", count);


            }


        }

    }


}