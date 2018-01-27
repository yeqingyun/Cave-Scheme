package yeqy.cave.scheme;

import yeqy.cave.scheme.paser.TokenPaser;
import yeqy.cave.scheme.structure.Environment;
import yeqy.cave.scheme.structure.SExpression;
import yeqy.cave.scheme.type.BaseType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by yeqy on 2017/12/25.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Cave Scheme Version 0.1");
        System.out.println("made by yeqy, Let's make a joy.\n");
        try (BufferedReader codeReader = new BufferedReader(new InputStreamReader(System.in))) {
            Environment rootEnv = new Environment();
            while (true) {
                try {
                    System.out.print(">> ");
                    String code = codeReader.readLine();
                    //词法分析
                    String[] token = TokenPaser.parseToken(code);
                    //语法分析 构建AST
                    SExpression sExpression = new SExpression(token);
                    // 语义分析 eval apply
                    BaseType baseType = sExpression.eval(rootEnv);

                    if (baseType != null)
                        System.out.println(baseType);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("see you next time.");
            }
        });

    }
}
