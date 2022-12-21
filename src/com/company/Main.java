package com.company;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main{
        public static List<String> returnData() throws IOException {
            List<String> data = new ArrayList<>();
            BufferedReader bf = new BufferedReader(
                    new FileReader("lotr.txt"));
            String line = bf.readLine();
            while (line != null) {
                data.add(line.replace(" ", ""));
                line = bf.readLine();
            }
            bf.close();

            return data;
        }
        public static List<String> encrypting(String message) throws IOException{

            List<String> data = returnData();
            HashMap<String, Character> helperMap = new HashMap<>();
            List<String> crypted = new ArrayList<>();
            List<String> letters = new ArrayList<>();
            Random rand = new Random();


            textToMap(data, helperMap);

            for (int i = 0; i < message.length(); i++) {
                for (Map.Entry<String, Character> entry : helperMap.entrySet()) {
                    if (entry.getValue() == message.charAt(i)) {
                        crypted.add(entry.getKey());
                    }
                }
                letters.add(crypted.get(rand.nextInt(crypted.size())));
                crypted.clear();
            }

            return letters;
        }
        public static String decrypting(List<String> letters) throws IOException{

            List<String> data = returnData();
            HashMap<String, Character> helperMap = new HashMap<>();
            String numberFirst;
            String numberSecond;
            String message = "";


            textToMap(data, helperMap);

            for(int i = 0; i < letters.size(); i++){
                message+=helperMap.get(letters.get(i)).toString();
            }

            return message;
    }

    public static HashMap<String, Character> textToMap(List<String> data, HashMap<String, Character> helperMap) {
        String numberFirst;
        String numberSecond;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).length(); j++) {
                numberFirst = Integer.toString(i+1);
                numberSecond = Integer.toString(j+1);
                helperMap.put(numberFirst + " " + numberSecond, data.get(i).charAt(j));
            }
        }
        return helperMap;
    }


    public static void main(String[] args) throws IOException {
            String decoded;
            Scanner userInput = new Scanner(System.in);

            System.out.println("Choose option 1/2 -> encrypting/decrypting");
            Scanner userChoice = new Scanner(System.in);
            String userCh = userChoice.nextLine();
            List<String> letters = new ArrayList<>();

            if(userCh.equals("1")){
                System.out.println("Enter text for Poem Code:");
                String message = userInput.nextLine();
                letters = encrypting(message);
                System.out.println(letters);
            }
            if (userCh.equals("2")){
                while(true){
                    System.out.println("Do you want ton enter more text? Y/N");
                    String msgch = userInput.nextLine();
                    if(msgch.equals("N")) break;
                    System.out.println("Enter text for decrypting:");
                    String decmsg = userInput.nextLine();
                    letters.add(decmsg);
                }

                if(letters.isEmpty()) System.out.println("No message for encryption provided");
                else{
                    decoded = decrypting(letters);
                    System.out.println(decoded);
                }
            }

        }
}
