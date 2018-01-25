package yeqy.cave.scheme;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by yeqy on 2017/12/25.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Cave Scheme Version 0.1");
        System.out.println("made by yeqy, Let's make a Joy.\n");
        System.out.print(">> ");
        try (BufferedReader codeReader = new BufferedReader(new InputStreamReader(System.in))){
            while (true) {
                System.out.println(codeReader.readLine());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
