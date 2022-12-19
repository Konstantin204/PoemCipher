package com.company;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Main {
        public static void main(String[] args) throws IOException {
            List<String> data = new ArrayList<String>();
            Scanner userInput = new Scanner(System.in);
            String message = userInput.nextLine();
            int k = message.length();
            Map<String, Character> helperMap = new HashMap<>();
            String numberFirst;
            String numberSecond;
            List<String> crypted = new ArrayList<>();
            List<String> letters = new ArrayList<>();
            Random rand = new Random();


            BufferedReader bf = new BufferedReader(
                    new FileReader("lotr.txt"));
            String line = bf.readLine();
            while (line != null) {
                data.add(line.replace(" ", ""));
                line = bf.readLine();
            }
            bf.close();

            for(int i = 0; i < data.size(); i++){
                for(int j = 0; j < data.get(i).length(); j++){
                    numberFirst = Integer.toString(i);
                    numberSecond = Integer.toString(j);
                    helperMap.put(numberFirst+" "+numberSecond, data.get(i).charAt(j));
                }
            }

            for(int i = 0; i < message.length(); i++){
                for(Map.Entry<String, Character> entry: helperMap.entrySet()) {
                    if(entry.getValue() == message.charAt(i)) {
                        crypted.add(entry.getKey());
                    }
                }
                letters.add(crypted.get(rand.nextInt(crypted.size())));
                crypted.clear();
            }
            for (String element:letters
                 ) {
                System.out.println(element);
            }

        }



}
