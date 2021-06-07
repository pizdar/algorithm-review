package com.zxbayne.algorithm.review.stack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Calculator {
    private static final String MULTIPLY_CHARACTERS = "*x×X";
    private static final String DIVIDE_CHARACTERS = "/÷";
    private static final String SUBTRACT_CHARACTERS = "-";
    private static final String PLUS_CHARACTERS = "+";

    private Calculator() {

    }

    /**
     * 计算运算符优先级，数值越高优先级越大
     *
     * @param op 运算符
     * @return 运算符的优先级，若不是运算符则返回 -1
     */
    public static int priority(char op) {
        if (isMultiplyOperation(op) || isDivideOperation(op)) {
            return 1;
        } else if (isPlusOperation(op) || isSubtractOperation(op)) {
            return 0;
        }
        return -1;
    }

    /**
     * 计算 left op right 的值 e.g. left = 8; right = 4; op = '/'; 返回 2
     *
     * @param left  左操作数
     * @param right 右操作数
     * @param op    运算符
     * @return 返回计算结果
     * @throws IllegalArgumentException 输入的操作符不合法时抛出
     */
    public static int nativeCal(int left, int right, char op) throws IllegalArgumentException {
        if (isPlusOperation(op)) {
            return left + right;
        } else if (isSubtractOperation(op)) {
            return left - right;
        } else if (isMultiplyOperation(op)) {
            return left * right;
        } else if (isDivideOperation(op)) {
            return left / right;
        } else {
            throw new IllegalArgumentException("未知操作符: " + op);
        }
    }

    private static boolean charIn(String str, char c) {
        for (char strChar : str.toCharArray()) {
            if (strChar == c) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMultiplyOperation(char op) {
        return charIn(MULTIPLY_CHARACTERS, op);
    }

    public static boolean isDivideOperation(char op) {
        return charIn(DIVIDE_CHARACTERS, op);
    }

    public static boolean isPlusOperation(char op) {
        return charIn(PLUS_CHARACTERS, op);
    }

    public static boolean isSubtractOperation(char op) {
        return charIn(SUBTRACT_CHARACTERS, op);
    }

    public static boolean isNumber(char op) {
        return charIn("0123456789", op);
    }

    public static boolean isOperator(char op) {
        String concat = PLUS_CHARACTERS.concat(SUBTRACT_CHARACTERS).concat(MULTIPLY_CHARACTERS)
                .concat(DIVIDE_CHARACTERS);
        return charIn(concat, op);
    }

    /**
     * 中缀表达式计算器 仅支持非负整数的加减乘除运算<br>
     * 本实现有小毛病，计算如 11-4*2-2的表达式有错误😟
     * 完美的实现请参考 {@link #infixToPostfix(String)}
     *
     * @param infix 中缀表达式，如 '1+2+3*6/3'，不允许其他的字符
     * @return 计算结果
     * @throws IllegalArgumentException 输入的表达式有误时抛出
     * @see #infixToPostfix(String)
     */
    public static int testEvalInfixExpression(String infix) throws IllegalArgumentException {
        Deque<Integer> numberStack = new LinkedList<>();
        Deque<Character> operatorStack = new LinkedList<>();
        char[] charArray = infix.toCharArray();
        StringBuilder numberKeeper = new StringBuilder();

        for (int i = 0; i < charArray.length; i++) {
            char current = charArray[i];
            if (isNumber(current)) {
                // 如果当前字符是数字，那么就从这里开始遍历，直到碰到的不是数字
                int j;
                // 遍历字符中的数字
                for (j = i; j < charArray.length; j++) {
                    // 如果碰到不是数字的字符，就退出循环
                    if (!isNumber(charArray[j])) {
                        break;
                    }
                    numberKeeper.append(charArray[j]);
                }
                // 读取到的多位数字压栈
                numberStack.push(Integer.parseInt(numberKeeper.toString()));
                // 清空 numberKeeper
                numberKeeper = new StringBuilder();
                // 外层循环跳过数字位
                i = j - 1;
            } else if (isOperator(current)) {
                // 如果当前符号栈为空，则将当前符号压栈并继续下轮循环
                // 在这种情况下说明没有右操作数，无法进行运算，只能 continue到下一轮循环
                if (operatorStack.isEmpty()) {
                    operatorStack.push(current);
                    continue;
                }

                // 符号栈不为空，则对比当前操作符与符号栈栈顶操作符的优先级
                // 如果当前的优先级高，则入栈并进行下一轮循环，读取操作数去
                if (priority(current) > priority(operatorStack.peek())) {
                    operatorStack.push(current);
                    continue;
                }


                // 当前读取到是操作符并且优先级比栈里的低，则取出栈里的操作符进行计算
                int right = numberStack.pop();  // 先 pop 出来的是右操作数
                int left = numberStack.pop();
                char op = operatorStack.pop();
                int result = nativeCal(left, right, op);
                // 计算完毕，将当前这个优先级低的操作符入栈，并将本次计算结果入栈
                operatorStack.push(current);
                numberStack.push(result);

            } else {
                // 当前字符既不是数字也不是操作符
                throw new IllegalArgumentException(
                        "无法解析的表达式: " + infix + "， index: " + i + "，" + "unknown character: " + current);
            }
        }
        // 遍历完表达式后，可能还存在由于符号优先级低而放在栈里头还没计算的操作符
        // 顺序地从数栈和符号栈中pop出相应的数和符号，并运算。
        while (!operatorStack.isEmpty()) {
            int right = numberStack.pop();
            int left = numberStack.pop();
            char op = operatorStack.pop();
            int result = nativeCal(left, right, op);
            numberStack.push(result);
        }
        return numberStack.pop();
    }

    /**
     * 中缀表达式转后缀表达式
     *
     * @param infix 中缀表达式
     * @return 后缀表达式，集合中每一个 element 要么是非负整数数字，要么是运算符
     */
    public static List<String> infixToPostfix(String infix) {
        char[] chars = infix.toCharArray();
        Deque<Character> operatorStack = new LinkedList<>();
        StringBuilder numberKeeper = new StringBuilder();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < chars.length; i++) {
            char current = chars[i];

            if (isNumber(current)) {
                // 如果当前字符是数字，那么就从这里开始遍历，直到碰到的不是数字
                int j;
                // 遍历字符中的数字
                for (j = i; j < chars.length; j++) {
                    // 如果碰到不是数字的字符，就退出循环
                    if (!isNumber(chars[j])) {
                        break;
                    }
                    numberKeeper.append(chars[j]);
                }
                // 读取到的多位数字保存到结果
                result.add(numberKeeper.toString());
                // 清空 numberKeeper
                numberKeeper = new StringBuilder();
                // 外层循环跳过数字位
                i = j - 1;
            } else if (isOperator(current)) {
                // 当前符号栈不为空 && 当前字符的优先级小于等于栈顶字符 && 栈顶字符不是右括号
                // 则弹出栈顶字符，放到结果中
                while (!operatorStack.isEmpty()
                        && (priority(operatorStack.peek()) >= priority(current))
                        && operatorStack.peek() != '(') {
                    result.add(operatorStack.pop().toString());
                }
                // 完成以上步骤后，将当前字符压栈
                operatorStack.push(current);
            } else if (current == '(') {
                // 左括号直接压栈
                operatorStack.push(current);
            } else if (current == ')') {
                // 当前字符为右括号，则找到离栈顶最近的左括号，并将那个左括号到栈顶之间的运算符全放到结果中
                // 依次弹出栈元素并放入结果，直到遇到左括号
                Character topOperator = operatorStack.pop();
                while (topOperator != '(') {
                    result.add(topOperator.toString());
                    topOperator = operatorStack.pop();
                }
            } else {
                throw new IllegalArgumentException(
                        "无法解析的表达式: " + infix + "， index: " + i + "，" + "unknown character: " + current);
            }
        }

        // 遍历完当前字符后，如果符号栈不为空，则弹出符号栈并放到结果中
        while (!operatorStack.isEmpty()) {
            result.add(operatorStack.pop().toString());
        }
        return result;
    }


    /**
     * 用户输入中缀表达式，内部进行转换成后缀表达式后，返回计算结果
     * 支持非负整数的加减乘除运算，以及小括号
     *
     * @param infix 中缀表达式
     * @return 计算结果
     * @see #infixToPostfix(String)
     */
    public static int evaluate(String infix) {
        // 中缀转后缀
        List<String> postfix = infixToPostfix(infix);
        Deque<Integer> numberStack = new LinkedList<>();

        for (String item : postfix) {
            if (item.matches("\\d+")) { // 用正则表达式判断是否是数字

                // 是数字则直接将其放入栈
                numberStack.push(Integer.parseInt(item));
            } else { // 不是数字，则是运算符
                // string 转 char
                char op = item.toCharArray()[0];
                // 计算结果，先弹出来的是右操作数
                int right = numberStack.pop();
                int left = numberStack.pop();
                int result = nativeCal(left, right, op);
                // 结果入栈
                numberStack.push(result);
            }
        }
        // 最后留在 numberStack里的元素就是结果
        return numberStack.pop();
    }

}
