
/* Alfonso Pruneda
 * 80542182
 * MW 4:30-5:50PM
 * Lab 1 - The purpose of the class is to use one-dimensional arrays to list:
 * total calories consumed in a day, list of days where calories went pasted the required of each day,
 *  and the average calories consumed overall in each of the three meals.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Calories {

	public static void main(String[] args) throws FileNotFoundException { //Throws exception if file not found
		
		int[] breakfast = new int[7];
		int[] lunch = new int[7];
		int[] dinner = new int[7];
		int counter = 0;
		
		File inFile = new File("input.txt"); //importing the file
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
			
			breakfast[counter] = Integer.parseInt(temp2[0]); //converts string to int and adds variable to the breakfast array
			lunch[counter] = Integer.parseInt(temp2[1]);    // converts string to int and adds variable to the lunch array
			dinner[counter] = Integer.parseInt(temp2[2]);  //  converts string to int and adds variable to the dinner array
			counter++;
			if(counter == 7) counter++; // used to check to see if # of lines is greater than 7
		}
		if(counter < 7){ // used to check to see if # of lines is less than 7
			System.out.println("Input file does not have enough lines, please try again");
			System.exit(0);
		}

		totalCalEachDay(breakfast, lunch, dinner);
		System.out.println();
		listOfDays(breakfast, lunch, dinner);
		System.out.println();
		averageConsumed(breakfast, lunch, dinner);
	}
	static void totalCalEachDay(int[] b, int[] l, int[] d){ //method for checking the total Calories eaten in each day
		int total = 0;
		String[] dayOfTheWeek = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		for(int i = 0; i < b.length; i++){
			total = b[i] + l[i] + d[i];
			System.out.printf("Your total Calories for %s is: %d", dayOfTheWeek[i], total);
			System.out.println();
		}
	}
	static void listOfDays(int[] b, int[] l, int[] d){     //method for checking which days have the calories
		String[] dayOfTheWeek = {"Monday ","Tuesday ","Wednesday ","Thursday ","Friday ","Saturday ","Sunday"};
		String totalDays = "";
		for(int i = 0; i < b.length; i++){
			if((b[i] + l[i] + d[i]) > 2250){
				totalDays += dayOfTheWeek[i];
			}
		}
		System.out.printf("You have eaten more calories than needed on these days:\n%s", totalDays);
		System.out.println();	
	}
	static void averageConsumed(int[] b, int[] l, int[] d){ //method for getting the average calories within each meal time
		int total1 = 0;
		int total2 = 0;
		int total3 = 0;
		for(int i = 0; i < b.length; i++){
			total1 += b[i];
			total2 += l[i];
			total3 += d[i];
		}
		double average1 = (double)total1 / b.length; // parsing the int totals' to double to get a more accurate average
		double average2 = (double)total2 / l.length;
		double average3 = (double)total3 / d.length;
		System.out.printf("The average for all your Breakfast meals is: %f\n", average1);
		System.out.printf("The average for all your Lunch meals is: %f\n", average2);
		System.out.printf("The average for all your Dinner meals is: %f\n", average3);

	}
}
