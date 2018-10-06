/*Alfonso Pruneda
 * CS 2401 MW 4:30-5:50pm
 * Lab assignment #2 part 1
 * In this program, we basically try to do the same thing we did in lab 1, the difference
 * is using a multidimensional array instead of separate arrays. We also add on by looking
 * for the maximum Calorie intake within a day, and within each meal period.
 */

import java.io.*;
import java.util.Scanner;


public class Calories1 {
	
	private static String[] dayOfTheWeek = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
	
	public static void main(String[] args)  throws FileNotFoundException { //Throws exception if file not found
		
		int [][] meals = new int[7][3];
		int counter = 0;
		
		File inFile = new File("input1.txt"); //importing the file
		Scanner input = new Scanner(inFile); //reading the file
		
		while(input.hasNextLine()){  //checking to see if the file has another line to copy
			if(counter == 8){ //used to check to see if # of lines is greater than 7
				System.out.println("Input file has more than 7 lines, please try again");
				System.exit(0);
			}
			String temp = input.nextLine(); //copy's the current line
			String[] temp2 = temp.split(" "); //separates variables whenever a space is present
			
			if(temp2.length != 3){ //check to see if file has 3 calorie counts in each line
				System.out.println("Input file does not have three numbers in one of the lines, please try again");
				System.exit(0);
			}
			meals[counter][0] = Integer.parseInt(temp2[0]); //converts string to int and adds variable to the breakfast part of meals
			meals[counter][1] = Integer.parseInt(temp2[1]);    // converts string to int and adds variable to the lunch part of meals
			meals[counter][2] = Integer.parseInt(temp2[2]);  //  converts string to int and adds variable to the dinner part of meals
			counter++;
			if(counter == 7) counter++; // used to check to see if # of lines is greater than 7
		}
		if(counter < 7){ // used to check to see if # of lines is less than 7
			System.out.println("Input file does not have enough lines, please try again");
			System.exit(0);
		}
		totalCalEachDay(meals);
		System.out.println();
		listOfDays(meals);
		System.out.println();
		averageConsumed(meals);
		System.out.println();
		maximumForEachDay(meals);
		System.out.println();
		maximumForEachMealPeriod(meals);
	}
	static void totalCalEachDay(int[][] m){ //method for checking the total Calories eaten in each day
		int total = 0;
		for(int i = 0; i < m.length; i++){
			total = m[i][0] + m[i][1] + m[i][2];
			System.out.printf("Your total Calories for %s is: %d", dayOfTheWeek[i], total);
			System.out.println();
		}
	}
	static void listOfDays(int[][] m){     //method for getting the average of each day
		double average = 0.0;
		for(int i = 0; i < m.length; i++){
			average = (m[i][0] + m[i][1] + m[i][2]) / 3.0;
			System.out.printf("Average Calories for %s: %f\n", dayOfTheWeek[i], average);	
		}
	}
	static void averageConsumed(int[][] m){ //method for getting the average calories within each meal time
		int total1 = 0;
		int total2 = 0;
		int total3 = 0;
		for(int i = 0; i < m.length; i++){
			total1 += m[i][0];
			total2 += m[i][1];
			total3 += m[i][2];
		}
		double average1 = (double)total1 / 7; // parsing the int totals to double to get a more accurate average
		double average2 = (double)total2 / 7;
		double average3 = (double)total3 / 7;
		System.out.printf("The average for all your Breakfast meals is: %f\n", average1);
		System.out.printf("The average for all your Lunch meals is: %f\n", average2);
		System.out.printf("The average for all your Dinner meals is: %f\n", average3);
	}
	static void maximumForEachDay(int[][] m) { //method to get the maximum calorie count from each day
		for(int i = 0; i < m.length; i++) {
			int max = m[i][0];
			if(max < m[i][1]) max = m[i][1];
			if(max < m[i][2]) max = m[i][2];
			System.out.printf("Maximum Calorie count for %s: %d\n", dayOfTheWeek[i], max);
		}
	}
	static void maximumForEachMealPeriod(int[][] m) {//method to get the maximum calorie count from each meal period
		int maxBreakfast = 0;
		int maxLunch = 0;
		int maxDinner = 0;
		for(int i = 0; i < m.length; i++) {
			if(maxBreakfast < m[i][0]) maxBreakfast = m[i][0];
			if(maxLunch < m[i][1]) maxLunch = m[i][1];
			if(maxDinner < m[i][2]) maxDinner = m[i][2];
		}
		System.out.printf("Maximum Calories for all meals in\nBreakfast: %d\nLunch: %d\nDinner: %d\n", maxBreakfast, maxLunch, maxDinner);
	}

}
