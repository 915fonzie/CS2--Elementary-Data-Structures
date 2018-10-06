/*Alfonso Pruneda
 * CS 2401 MW 4:30-5:50PM
 * Lab assignment #2 part 2
 * In this program, we are given data from a text file and are asked to give
 * the averages of both the days and the meals. The difference between this one and 
 * part 1, the text file given has different set of numbers of meals in each day and
 * we provide a different way of storing that info and outputting the averages.
 */

import java.io.*;
import java.util.Scanner;

public class Calories2 {
	private static String[] dayOfTheWeek = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};//private variable to use in methods if needed

	public static void main(String[] args) throws FileNotFoundException {
		int [][] meals = new int[7][];
		int counter = 0;
		
		File inFile = new File("input2.txt"); //importing the file
		Scanner input = new Scanner(inFile); //reading the file
		
		while(input.hasNextLine()){  //checking to see if the file has another line to copy
			if(counter == 8){ //used to check to see if # of lines is greater than 7
				System.out.println("Input file has more than 7 lines, please try again");
				System.exit(0);
			}
			String temp = input.nextLine(); //copy's the current line
			String[] temp2 = temp.split(" "); //separates variables whenever a space is present
			if(temp2.length == 0){
				System.out.println("Input file does not have anything in one of the lines");
				System.exit(0);
			}
			int[] convert= new int[temp2.length]; //using an int array to parse the string array and adding it to the ragged array
			for(int i = 0; i < temp2.length; i++){
				convert[i] = Integer.parseInt(temp2[i]);
			}
			meals[counter] = convert;
			counter++;
			if(counter == 7) counter++; // used to check to see if # of lines is greater than 7
		}
		if(counter < 7){ // used to check to see if # of lines is less than 7
			System.out.println("Input file does not have enough lines, please try again");
			System.exit(0);
		}
		
		averageConsumedEachDay(meals);
		System.out.println();
		averageConsumedMeals(meals);
	}
	
	static void averageConsumedEachDay(int[][] m){  //provides the average calories consumed in each day
		double[] average = new double[m.length];
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[i].length; j++){
				average[i] += (double)m[i][j];
			}
			average[i] = average[i] / m[i].length;
			System.out.printf("The average on %s: %f\n",dayOfTheWeek[i],average[i]);
		}
		
	}
	static void averageConsumedMeals(int[][] m){  //provides the average calories consumed in each meal
		int mostMealsInADay = 0;
		for(int i = 0; i < m.length; i++){    //used to find the highest number of meals taken in a day
			if(mostMealsInADay < m[i].length){
				mostMealsInADay = m[i].length;
			}
		}
		double[] sumMeals = new double[mostMealsInADay];  //created two arrays for the total of each meal and
		int[] whatevs = new int[mostMealsInADay];   // how many meals were taken each day

		for(int i = 0; i < m.length; i++){
			for(int j = 0; j <m[i].length; j++){
				sumMeals[j] += (double)m[i][j];
				whatevs[j]++;
			}
		}
		for(int i = 0; i < mostMealsInADay; i++){	
			sumMeals[i] = sumMeals[i]/whatevs[i];
			System.out.printf("The average for meal %d: %f\n", i+1 , sumMeals[i]);
		}
	}
}
