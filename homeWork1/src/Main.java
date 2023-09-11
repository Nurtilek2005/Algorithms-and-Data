/* ------------------------------------------------------------------------------------------
### Задача №1 ###
---------------------------------------------------------------------------------------------
Дано целое число N из отрезка [1; 1000]. Также даны N целых чисел Ai из отрезка [1; 1000000].
Требуется для каждого числа Ai вывести количество различных делителей этого числа.
В этой задаче несколько верных решений, попробуйте найти наиболее оптимальное.
Для полученного решения укажите сложность в О-нотации.
------------------------------------------------------------------------------------------ */

import java.util.NoSuchElementException;
import java.util.Scanner;
    
public class Main {
    public static void main(String[] args) {
        Main program = new Main();
        program.run();
    }

    public void run() {
        String message = "Введите число N: ";
        int number1 = this.promptNumber(message);
        this.run(number1);
    }

    public void run(int number1) {
        int[] divisorsCount = new int[1000001];
        for (int i = 1; i <= 1000000; i++) {
            for (int j = i; j <= 1000000; j += i) {
                divisorsCount[j]++;
            }
        }

        for (int i = 0; i < number1; i++) {
            int Ai = this.promptNumber();
            System.out.println(divisorsCount[Ai]);
        }
    }


    public int promptNumber() {
        return this.promptNumber("Enter number or enter 'exit': ");
    }

    public int promptNumber(String message) {
        Scanner iScanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            String line = iScanner.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                throw new NoSuchElementException();
            }
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException exception) {
                System.out.println("Incorrect number!");
            }
        }
    }
}