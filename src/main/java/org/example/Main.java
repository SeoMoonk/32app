package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        String input = "";
        String saying = "";
        String author = "";

        int count = 0;

        List<String> saying_arr = new ArrayList<>();
        List<String> author_arr = new ArrayList<>();




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
                saying_arr.add(count, saying);

                System.out.print("작가 : ");
                author = sc.nextLine();
                author_arr.add(count, author);

                count ++;

                System.out.printf("%d 번 명언이 등록되었습니다. \n", count);

            }
            else if(input.equals("목록"))
            {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("-----------------------");

                for(int i = count-1; i >= 0; i--)
                {
                    System.out.printf("%d / %s / %s \n", i+1, author_arr.get(i), saying_arr.get(i));

                }
            }

        }

    }


}