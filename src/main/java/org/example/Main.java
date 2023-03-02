package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        String input;
        String saying;
        String author;
        String input_saying;
        String input_author;

        int del_num = 0;
        int crt_num = 0;
        int count = 0;

        List<Wise_saying> wise_saying = new ArrayList<>();


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

                wise_saying.add(new Wise_saying(count, saying, author));

                System.out.printf("%d 번 명언이 등록되었습니다. \n", count);

            }
            else if(input.equals("목록"))
            {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("-----------------------");

                for(int i = count-1; i >= 0; i--)
                {
                    System.out.printf("%d / %s / %s \n", wise_saying.get(i).number, wise_saying.get(i).author, wise_saying.get(i).saying);

                }
            }
            else if(input.equals("삭제"))
            {
                System.out.print("?id=");

                input = sc.nextLine();
                del_num = Integer.parseInt(input);

                //유효한 삭제인지 검사
                for(int i=0; i<=wise_saying.size(); i++)
                {
                    if(del_num == wise_saying.get(i).number)
                    {
                        del_num--;

                        wise_saying.remove(del_num);
                        count--;

                        System.out.printf("%d 번 명언이 삭제되었습니다.\n",del_num+1);
                        break;
                    }

                    if(i == wise_saying.size()-1)
                    {
                        System.out.printf("%d 번 명언은 존재하지 않습니다. \n", del_num);
                        break;
                    }

                }
            }
            else if(input.equals("수정"))
            {
                System.out.print("?id=");
                input = sc.nextLine();
                crt_num = Integer.parseInt(input);

                for(int i=0; i<=wise_saying.size(); i++)
                {
                    //수정할 명언을 찾은 경우
                    if(crt_num == wise_saying.get(i).number)
                    {

                        System.out.printf("명언(기존) : %s \n", wise_saying.get(crt_num-1).saying);
                        System.out.print("명언 : ");
                        input_saying = sc.nextLine();

                        System.out.printf("작가(기존) : %s \n", wise_saying.get(crt_num-1).author);
                        System.out.print("작가 : ");
                        input_author = sc.nextLine();

                        wise_saying.set(crt_num-1, new Wise_saying(crt_num, input_saying, input_author));

                        break;
                    }

                    //수정할 명언을 못찾은 경우
                    if(i == wise_saying.size()-1)
                    {

                    }

                }



            }

        }

    }

}

class Wise_saying{

    int number;
    String saying;
    String author;

    public Wise_saying(int number, String saying, String author) {
        this.number = number;
        this.saying = saying;
        this.author = author;
    }

}