package org.learining.hadoop;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Demo {
    public static void main(String args[]) throws IOException {
        long startTime = System.nanoTime();
        File input = new File(args[0]);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
        String str = null;
        HashMap<String,Integer> countMap = new HashMap<>();
        while ((str = br.readLine()) != null){
            StringTokenizer tokenizer = new StringTokenizer(str," ");
            while (tokenizer.hasMoreTokens()){
                String token = tokenizer.nextToken();
                if(countMap.containsKey(token)){
                    countMap.put(token,countMap.get(token) + 1);
                }
                else{
                    countMap.put(token,1);
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
        bw.write(countMap.toString());
        bw.flush();
        bw.close();

        long endTime = System.nanoTime();
        System.out.println("Time taken = "+ (endTime-startTime));
        System.out.println(countMap);
    }
}
