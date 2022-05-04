package StreamDemoPack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class WordCount {
    String line = null;
    List<String> wordList = new ArrayList<>();


    void URLread() {
        try {
            URL url = new URL("https://github.com/dwyl/english-words/blob/master/words.txt?raw=true");
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(url.openStream()));
            {
                while ((line = bufIn.readLine()) != null) {
                    wordList.add(line);

                }
                bufIn.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(wordList.size());
        int maxString = wordList.stream().map(String::length).max(Integer::compareTo).get();

        int intStr[] = new int[maxString];
        for (int i = 0; i < maxString; i++) {
            for (String s : wordList) {
                if (s.length() == i + 1) {
                    intStr[i] = intStr[i] + i;
                }
            }
        }
        int noChar = 1;
        for (int i : intStr) {
            if (i != 0) {
                System.out.println("Number of words with " + noChar + " signs " + i);
            }
            noChar++;
        }
    }
    }

/*
    ArrayList<String> sort(ArrayList<String> str, int n) {
        TreeMap<Integer, ArrayList<String>> map
                = new TreeMap<Integer, ArrayList<String>>();

        for (int i = 0; i < n; i++) {

            map.putIfAbsent(str.get(i).length(),
                    new ArrayList<String>());
            map.get(str.get(i).length()).add(str.get(i));
        }

        int temp = 0;

        for (Map.Entry<Integer, ArrayList<String>>
                e : map.entrySet()) {

            for (int i = 0; i < e.getValue().size(); i++) {

                str.set(temp, e.getValue().get(i));
                temp++;
            }
        }
        return str;
    }

    // Function to print the sorted array of string
    void printArraystring(List<String> str, int n) {
        int noChar = 0;
        for (String s : str) {
            if (s != null) {
                System.out.println("Number of words with " + noChar + " signs " + 1);
            }
        }

        noChar++;
    }
       for (int i = 0; i < n; i++)
            System.out.print(str.get(i) + " ");

        */

