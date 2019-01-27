//**************************************************************************************
//Assignment #4
//Written by: Jordan Hubscher, ID: 7019696
//For Comp 248 Section EE -Fall 2013
//**************************************************************************************

// This program will randomly generate a diver's score from 7 judges.

import java.util.Scanner;

public class Question1 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int no_judges = 7; // There are 7 judges giving scores.
		
		double[] judgesArr = new double[no_judges]; // An array containing 7 reference locations for each judge.
		
		double difficulty = 0;
		double score = 0;
		double totalScore = 0; // Total score from all 7 judges.
		double max = judgesArr[0];
		double min = judgesArr[0];
		
		System.out.println("Welcome to the Olympic Games diving finals!\n");
		System.out.println("*******************************************\n");
		
		while (true) {
			System.out.print("Please enter a degree of difficulty between 1.2 and 3.8 for the diver's scoring process: ");
			difficulty = input.nextDouble();
			if (difficulty <= 1.2 || difficulty >= 3.8)
				System.out.println("\nInvalid input, please re-enter degree of difficulty.");
			else 
				break;
		}
		
		System.out.println("\n");
		
		// Input is collected form user for each judge's score.
		for(int i = 0; i < judgesArr.length; i++) {
			System.out.print("Please enter a judge's score (between 0.0 and 10.0) for judge number \"" + (i+1) + "\": ");
			score = input.nextDouble();
			judgesArr[i] = score;
			totalScore += array[i];
			
			if (score < 0 | score > 10)	{
				i -= 1;//if score is greater than 10 or less than 0, it will restart the loop where it left off.
				System.out.println("Invalid Input, please try again :D");
				continue;
			}
		}
		
		System.out.println("\nTotal score: " + totalScore);
		
		// Declaring and initializing max and min here because before there were no values assigned 
		// to the respective indices in the array.
		
		// Gathering the largest and smallest score from the input.
		for(int j = 0; j < no_judges; j++) {
			if (judgesArr[j] >= max)
				max = judgesArr[j];
			if (judgesArr[j] <= min)
				min = judgesArr[j];
		}
		
		double newScore = (totalScore-max-min);	
		double difficultyScore = (difficulty*newScore);
		double finalScore = (difficultyScore*0.6);
		
		System.out.println("Greatest Score: " + max + "\nLowest score: " + min);
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("Total score after greatest and lowest scores have been subtracted: " + newScore);
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("Degree of difficulty is: " + difficulty);
		System.out.println("New total score after multiplication with the degree of difficulty: " + difficultyScore);
		System.out.println("-----------------------------------------------------------------------------");
		System.out.print("Judges' final score is: " + finalScore);
	
		input.close();
	}
}
