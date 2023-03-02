package com.ll;

import java.io.*;
import java.util.*;

import org.json.JSONObject;
import org.json.JSONException;

public class Main {
    public static void main(String[] args) throws IOException, JSONException {

        Scanner sc = new Scanner(System.in);

        File file = new File("c:\\32app\\32app.txt");
        file.createNewFile();

        FileReader fr = new FileReader(file);

        Map<String, String> map = new HashMap<>();

        File jsonFile = new File("c:\\32app\\app32.json");

        JSONObject jo = new JSONObject();

        String input;
        String saying;
        String author;
        String input_saying;
        String input_author;

        int del_num;
        int crt_num;
        int count = 0;

        List<Wise_saying> wise_saying = new ArrayList<>();

        System.out.println("== 명언 앱 ==");

        while(true)
        {
            System.out.print("명령) ");
            input = sc.nextLine();

            if(input.equals("종료"))
            {

                try {
                    System.out.println("명언 앱을 종료합니다.");

                    FileWriter fw = new FileWriter(file);

                    fw.write("번호 / 작가 / 명언\n");
                    fw.write("-".repeat(30) + "\n");

                    for (int i = wise_saying.size(); i > 0; i--) {
                        System.out.printf("%d / %s / %s \n", wise_saying.get(i-1).number, wise_saying.get(i-1).author, wise_saying.get(i-1).saying);

                        fw.write(wise_saying.get(i-1).number + " / " + wise_saying.get(i-1).author + " / " + wise_saying.get(i-1).saying + "\n");

                    }

                    fw.close();
                    sc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
//                System.out.println("번호 / 작가 / 명언");
//                System.out.println("-".repeat(30));
//
//                for(int i = wise_saying.size(); i > 0; i--)
//                {
//                    System.out.printf("%d / %s / %s \n", wise_saying.get(i-1).number, wise_saying.get(i-1).author, wise_saying.get(i-1).saying);
//                }

                //적어놓은 txt 파일로 읽어오는 것으로 변경
                try{

                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String str;

                    while((str = br.readLine()) != null)
                    {
                        System.out.println(str);
                    }
                    br.close();
                } catch(FileNotFoundException e) {
                    e.printStackTrace();
                } catch(IOException e) {
                    e.printStackTrace();
                }


            }
            else if(input.startsWith("삭제"))
            {
                String[] commandBits = input.split("\\?", 2);
                String actionCode = commandBits[0];
                Map<String, String> params = new HashMap<>();
                String[] paramsBits = commandBits[1].split("&");

                for(String paramStr : paramsBits) {
                    String[] paramStrBits = paramStr.split("=", 2);
                    String key = paramStrBits[0];
                    String value = paramStrBits[1];

                    params.put(key, value);
                }

                del_num = Integer.parseInt(params.get("id"));

                //유효한 삭제인지 검사
                for(int i=0; i<=wise_saying.size(); i++)
                {
                    if(del_num == wise_saying.get(i).number)
                    {
                        wise_saying.remove(i);
                        count--;

                        System.out.printf("%d 번 명언이 삭제되었습니다.\n",del_num);
                        break;
                    }

                    if(i == wise_saying.size()-1)
                    {
                        System.out.printf("%d 번 명언은 존재하지 않습니다. \n", del_num);
                        break;
                    }

                }
            }
            else if(input.startsWith("수정"))
            {
                String[] commandBits = input.split("\\?", 2);
                String actionCode = commandBits[0];
                Map<String, String> params = new HashMap<>();
                String[] paramsBits = commandBits[1].split("&");

                for(String paramStr : paramsBits) {
                    String[] paramStrBits = paramStr.split("=", 2);
                    String key = paramStrBits[0];
                    String value = paramStrBits[1];

                    params.put(key, value);
                }

                crt_num = Integer.parseInt(params.get("id"));

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
                        System.out.printf("%d 번 명언은 존재하지 않습니다. \n", crt_num);
                        break;
                    }

                }

            }
            else if(input.equals("빌드"))
            {

                for(int i=wise_saying.size(); i>0; i--)
                {
                    map.put("author", wise_saying.get(i-1).author);
                    map.put("content", wise_saying.get(i-1).saying);
                    map.put("id", String.valueOf(wise_saying.get(i-1).number));

                    jo.put("id", map.get("id"));
                    jo.put("content", map.get("content"));
                    jo.put("author", map.get("author"));
                }

                String jsonStr = jo.toString();
                try {
                    FileWriter jsfw = new FileWriter(jsonFile);

                    jsfw.write(jsonStr);

                    jsfw.close();

                    System.out.println("json파일로 갱신이 완료되었습니다.");
                } catch (IOException e) {
                    e.printStackTrace();
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