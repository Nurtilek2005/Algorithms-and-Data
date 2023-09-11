/* --------------------------------------------------------------
### Задача №2 ###
-----------------------------------------------------------------
Реализовать алгоритм пирамидальной сортировки (сортировка кучей).
-------------------------------------------------------------- */

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main program = new Main();
//        program.run(new int[]{32, 45, 21, 91, 18, 8});
        program.run();
    }

    public void run() {
        int[] array = this.promptArray();
        this.run(array);
    }

    public void run(int[] array) {
        System.out.println("Input array: " + Arrays.toString(array));
        System.out.println("Heap sorted array: " + Arrays.toString(this.heapSort(array)));
    }


    public int[] promptArray() {
        return this.promptArray("Enter array values");
    }


    public int[] promptArray(String message) {
        return this.promptArray(message, -1);
    }

    public int[] promptArray(int size) {
        return this.promptArray("Enter array values", size);
    }

    public int[] promptArray(String message, int size) {
        int index = 1;
        int[] array = new int[]{};
        System.out.println(message);
        while (size >= index || size == -1) {
            try {
                int number = this.promptNumber("Enter value (%d/%s) or enter 'exit': ".formatted(index, size));
                array = Arrays.copyOf(array, index);
                array[index - 1] = number;
                index++;
            } catch (NoSuchElementException exception) {
                break;
            }
        }
        return array;
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

    public int[] heapSort(int[] arr) {
        int size = arr.length;
        int[] sorted = Arrays.copyOf(arr, size);
        for (int i = size / 2 - 1; i >= 0; i--) {
            this.heapify(sorted, size, i);
        }
        for (int i = size - 1; i >= 0; i--) {
            int temp = sorted[0];
            sorted[0] = sorted[i];
            sorted[i] = temp;
            this.heapify(sorted, i, 0);
        }
        return sorted;
    }

    public void heapify(int[] arr, int size, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < size && arr[left] > arr[largest])
            largest = left;

        if (right < size && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            this.heapify(arr, size, largest);
        }
    }
}