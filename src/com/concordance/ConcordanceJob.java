package com.concordance;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ConcordanceJob {
    public void applyConcordance(String file_content) throws IOException {
        //1. remove any know short forms from the string here its i.e.
        //2. make all the sentences into different line and remove punctuations
        //3. put all the words in the hash map and keep a counter for each work
        //4. keep a main counter to see the sentance number for the bonus point


        //Using treemap so it sorts the map based on key
        TreeMap<String,List<Integer>> word_counts = new TreeMap<>();
        int line_number = 1;

        //Removing punctuations
        List<String> all_words = Arrays.asList(file_content.replaceAll("[^a-zA-Z\\. ]", "").toLowerCase().split(" "));

        //Concordance logic
        for (String word: all_words) {
            //Sanity check that we dont include any space as a word
            if(!word.trim().equals("")){
                //as soon as we see the fullstop we need to change the sentence nummber
                if(word.trim().endsWith(".") && !word.equals("i.e.")){
                    line_number++;

                }

                    if(word_counts.containsKey(word) && !word.equals(".")){
                        List<Integer> values = word_counts.get(word);
                        List<Integer> updated_values = new ArrayList<>(values);
                        updated_values.set(0,values.get(0)+1);
                        updated_values.add(line_number);
                        word_counts.put(word, updated_values);
                    }else if(!word.equals(".")){
                        word_counts.put(word,Arrays.asList(1,line_number));
                    }

              }
        }
        //Writing the file
        String aggFileName = "output.txt";
        FileWriter fstream = new FileWriter("src/output.txt");
        BufferedWriter out = new BufferedWriter(fstream);

        for (Map.Entry<String, List<Integer>> entry : word_counts.entrySet()) {
            List<Integer> value = entry.getValue();
            StringBuilder commaSeparated = new StringBuilder();
            for(int i = 1;i<value.size();i++){
                commaSeparated.append(value.get(i)).append(",");
            }
            String commaSeparatedString = commaSeparated.substring(0, commaSeparated.length()-1);
            System.out.println(entry.getKey() + "\t" + "{" + value.get(0) + ":" + commaSeparatedString + "}");
            out.write(entry.getKey() + "\t" + "{" + value.get(0) + ":" + commaSeparatedString + "}" + "\n");
            out.flush();   // Flush the buffer and write all changes to the disk
        }

        out.close();    // Close the file
    }
}
