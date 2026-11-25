import task1.Task1;
import task2.Task2;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Choose task to run - ");
        Scanner scanner = new Scanner(System.in);
        int task = scanner.nextInt();
        if(task == 1)
            Task1.task1();
        else if(task == 2)
            Task2.task2();
    }
}