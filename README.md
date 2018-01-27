一个带REPL的scheme解释器，可称为Cave Scheme

语法:

关键字

`define`  
`if`  
`lambda`  

`(define inc (lambda (x) (+ 1 x)))`  
`(define dec (lambda (x) (- x 1)))`  
`(define (inditity x) (- (inc x) 1))`  
`(define (add x y) (if (= 0 y) x (add (inc x) (dec y))))`  
`(inc 3)`  
`(inditity 4)`  
`add 1 3`

支持省略最外层括号

`inc 3`  
`imditity 4`
   
运行方式 
1.  `mvn clean package`
2.  `java -jar Cave-Scheme.jar`

待完善....