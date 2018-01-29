package yeqy.cave.scheme;

import yeqy.cave.scheme.paser.TokenPaser;
import yeqy.cave.scheme.structure.Environment;
import yeqy.cave.scheme.structure.SExpression;
import yeqy.cave.scheme.type.BaseType;

import java.io.BufferedReader;
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

            new SExpression(TokenPaser.parseToken("(define (abs x) (if (> x 0) x (- 0 x))")).eval(rootEnv);
            new SExpression(TokenPaser.parseToken("(define (expt-iter x y r) (if (= 0 y) r (expt-iter x (- y 1) (* r x))))")).eval(rootEnv);
            new SExpression(TokenPaser.parseToken("(define (expt x y) (expt-iter x y 1))")).eval(rootEnv);
            new SExpression(TokenPaser.parseToken("(define (remainder a b) (- a (* (/ a b) b))")).eval(rootEnv);
            new SExpression(TokenPaser.parseToken("(define (cons a b) (* (expt 2 a) (expt 3 b)))")).eval(rootEnv);
            new SExpression(TokenPaser.parseToken("(define (car z) (if (= (remainder z 2) 0) (+ 1 (car (/ z 2))) 0))")).eval(rootEnv);
            new SExpression(TokenPaser.parseToken("(define (cdr z) (if (= (remainder z 3) 0) (+ 1 (cdr (/ z 3))) 0))")).eval(rootEnv);

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
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("see you next time.");
            }
        });

    }
}
