package com.concordance;

import java.io.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        String file_name = "input.txt";
        String file_content = readFile(file_name);
        ConcordanceJob concordance = new ConcordanceJob();
        concordance.applyConcordance(file_content);

    }

    private static String readFile(String file_name) {
        InputStream in = Main.class.getResourceAsStream("../../input.txt");
        return new BufferedReader(new InputStreamReader(in))
                .lines().collect(Collectors.joining("\n"));
    }
}
