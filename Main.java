//Program written by Jalay Shukla
//Program description in README file
import java.io.FileWriter;
import java.lang.Math;
import java.util.Scanner;
import java.io.FileReader;
import java.io.*;
public class Main {
	/**
	 * @param auditorium (array)
	 * @param rowNum (the number of rows in the auditorium)
	 * @param columnNum (the number of columns in the auditorium)
	 */
	public static void displayAuditorium (char [][]auditorium, int rowNum, int columnNum) { //function displays the auditorium along with the column header(A,B,C...) and row header(1,2,3...) and shows a '.' if seat is reserved and a '#' if not reserved
        char [] headerArray = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        System.out.print("  ");
        for(int i = 0; i < columnNum; i++) { //this loop prints the letters of the columns only up until the number of columns in the auditorium is reached
	        System.out.print(headerArray[i]);
	    }
        System.out.println("");
        for (int i = 0; i < rowNum; i++) {//displays the auditorium
        	System.out.print(i + 1 + " "); //prints the row number
            for (int j = 0; j < columnNum; j++) {
                if (auditorium[i][j] != '.') {
	                System.out.print('#');
	            }
	            else {
                    System.out.print('.');
	            }
            }
            System.out.println("");
        }
    }
	/**
	 * @param auditorium (array)
	 * @param row (the row the user picked)
	 * @param seat (the integer value of the seat that the user picked)
	 * @param adultTix (the number of adult tickets the user wants)
	 * @param childTix (the number of child tickets the user wants)
	 * @param seniorTix (the number of senior tickets the user wants)
	 * @return
	 */
	public static boolean seatAvailability(char [][] auditorium, int row, int seat, int adultTix, int childTix, int seniorTix) { //boolean function to check if chosen seats are available
        for(int i = 1; i <= adultTix + childTix + seniorTix; i++) {
            if (auditorium[row - 1][seat] != '.') {
                return false;
            }
            seat++;
        }
        return true;
    }
	/**
	 * @param auditorium (array)
	 * @param row (the row that the user picked their seat to be in)
	 * @param seat (the integer value of the seat that the user picked)
	 * @param adultTix (the number of adult tickets the user wants to book)
	 * @param childTix (the number of child tickets the user wants to book)
	 * @param seniorTix (the number of senior tickets the user wants to book)
	 */
	public static void reserveSeats(char [][]auditorium, int row, int seat, int adultTix, int childTix, int seniorTix) { //function that reserves the seat(s) the user wants
        for (int i = 1; i <= adultTix; i++) {
            auditorium[row - 1][seat] = 'A';
            seat++;
        }
        for (int j = 1; j <= childTix; j++) {
            auditorium[row - 1][seat] = 'C';
            seat++;
        }
        for (int k = 1; k <= seniorTix; k++) {
            auditorium[row - 1][seat] = 'S';
            seat++;
        }
    }
	/**
	 * @param auditorium (array)
	 * @param rowNum number of rows in the auditorium
	 * @param columnNum number of columns in auditorium
	 */
	public static int ticketsCounter(char [][]auditorium, int rowNum, int columnNum, char seatType) { //counts the number of tickets of each type(child, adult, senior)
		int tickets = 0;
		for (int i = 0; i <= rowNum; i++) {
			for(int j = 0; j <= columnNum; j++) {
				if (auditorium[i][j] == seatType) {
					tickets++;
				}
            }
        }
		return tickets;
    }
	/**
	 * @param auditorium (array)
	 * @param rowNum number of rows in the auditorium
	 * @param columnNum number of columns in auditorium
	 */
	public static void reportDisplay(char [][]auditorium, int rowNum, int columnNum) { //displays the report, which contains the number of total seats in the auditorium, the amount of tickets sold(total and of each type), as well as the total sales
        int adultTickets = 0;
        int childTickets = 0;
        int seniorTickets = 0;
        adultTickets = ticketsCounter(auditorium, rowNum, columnNum, 'A');
        childTickets = ticketsCounter(auditorium, rowNum, columnNum, 'C');
        seniorTickets = ticketsCounter(auditorium, rowNum, columnNum, 'S');
        System.out.println("Total Seats: " + (rowNum * columnNum));
        System.out.print("Total Tickets: ");
        System.out.println(adultTickets + childTickets + seniorTickets);
        System.out.println("Adult Tickets: " + adultTickets);
        System.out.println("Child Tickets: " + childTickets);
        System.out.println("Senior Tickets: " + seniorTickets);
        System.out.println("Total Sales: $" + String.format("%.2f", 10 * adultTickets + 5 * childTickets + 7.5 * seniorTickets));
    }
	/**
	 * @param auditorium (array) 
	 * @param rowCounter number of rows in auditorium
	 * @param columnCounter number of columns in auditorium
	 */
	public static void writeAuditoriumToFile (char [][]auditorium, int rowNum, int columnNum) { //writes the current status of the auditorium to AuditoriumFinal.txt
		try {
		      FileWriter myWriter = new FileWriter("AuditoriumFinal.txt");
		      for (int i = 0; i < rowNum; i++) {
		            for(int j = 0; j < columnNum; j++) {
		                myWriter.write(auditorium[i][j]);
		            }
		            myWriter.write('\n');
		        }
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	/**
	 * @param auditorium (array)
	 * @param row (is the row choice that the user picked their seat in)
	 * @param columnCounter (the number of columns in the auditorium)
	 * @param adultTix (the number of adult tickets the user wanted to book)
	 * @param childTix (the number of child tickets the user wanted to book)
	 * @param seniorTix (the number of senior tickets the user wanted to book)
	 * @param scnr (object passed into the function for input on whether user wants to books best available seats)
	 */
	public static void bestAvailableSeats (char [][]auditorium, int row, int columnCounter, int adultTix, int childTix, int seniorTix, Scanner scnr) { //finds the best available seats in the row that the user wanted if it exists.
		double rowCenter = (columnCounter - 1) / 2;
		double distance = 0;
		double seatCenter = 0;
		double shortestDistance = rowCenter;
		int bestSeatStart = 0;
		int bestSeatEnd = 0;
		boolean available = false;
		char userAnswer = ' ';
		for(int i = 0; i < columnCounter; i++) {
			if(seatAvailability(auditorium, row, i, adultTix, childTix, seniorTix)) { // if the seat is available
				seatCenter = (i + i + adultTix + childTix + seniorTix - 1) / 2;
				distance = Math.abs(rowCenter - seatCenter); //absolute value is used in case rowCenter - seatCenter is negative
				if (distance < shortestDistance) { 
					shortestDistance = distance;
					bestSeatStart = i;
					bestSeatEnd = i + adultTix + childTix + seniorTix - 1;
				}
				available = true;
			}
		}
		 if (!available) {
			 System.out.println("no seats available");
		 }
		 else if (available) {
			 if (adultTix + childTix + seniorTix == 1) { //in case the user only wants one seat
				 System.out.print("" + row);
				 System.out.println((char)(bestSeatStart + 65)); // adding 65 because ascii for A is 65
			 }
			 else {//empty string at the beginning is so that the entire print statement is a string and the numberToSeat(bestSeatStart) does not get converted to ascii
				 System.out.println("" + row + (char)(bestSeatStart + 65) + " - " + row + (char)(bestSeatEnd + 65));
			 }
			 System.out.println("Would you like to reserve these seats? (Y/N)");
			 userAnswer = scnr.next().charAt(0);
			 if (userAnswer == 'Y') {
				 reserveSeats(auditorium, row, bestSeatStart, adultTix, childTix, seniorTix);
			 }
			 else if (userAnswer == 'N') {
				 System.out.print("You chose no.");
			 }
		 }		 
	}

	public static void main(String[] args) { //main function
	    char [][] auditoriumArray = new char [10][26];
	    int ch;
	    int column = 0;
	    int columnCounter = 0;
	    int rowCounter = 0;
	    int userInput = 0;
	    int rowChoice = 0;
	    char userSeat = ' ';
	    int userSeatNum = 0;
	    int adultTix = 0;
	    int childTix = 0;
	    int seniorTix = 0;
		Scanner scnr = new Scanner(System.in);
		System.out.println("What is the file name?");
		String fileInput = scnr.nextLine();
		FileReader fr;
		boolean endLoop = false;
		try {
    		fr = new FileReader(fileInput);
    		for(int row = 0; row < 10 && !endLoop;) { //!endLoop is to exit both loops
    		    for(int numOfColumns = 0; numOfColumns > -1; numOfColumns++) {
    		        ch = fr.read(); //gets the decimal number of the character

    		        if(ch == 10) { //10 is the decimal character of an endline 
    		            row++; //row iterates every time an endline is detected
    		            rowCounter++;
    		            column = 0; //column is reset for the writing of the character to the array
    		        }
    		        else if(ch == -1) { // -1 is the decimal character for EOF
    		        	rowCounter++;
    		        	fr.close();
    		        	endLoop = true;
    		            break;
    		        }
    		        else {
    		            auditoriumArray[row][column]= (char)ch;
    		            if(row == 0) {
    		            	columnCounter++;
    		            }
    		            column++;
    		        }
    		    }
    		}
		}
		catch (Exception e) {
			//If It is Invalid File Name Exception, print error message
			//If not, print the stacktrace.
			//e.printStackTrace();
			scnr.close();
		    System.out.println("Invalid File name");
		    return;
		};
		do {
			System.out.println("1. Reserve Seats\n" + "2. Exit\n"); //menu is a do while loop
			userInput = scnr.nextInt();
			if (userInput == 1) {
				displayAuditorium(auditoriumArray, rowCounter, columnCounter);
				System.out.println("Which row?");
		        do {
		            rowChoice = scnr.nextInt();
	   	            if (rowChoice <= 0 || rowChoice > rowCounter) {
			            System.out.println("Invalid Selection. Please select a valid row");
	   	            }
		        } while (rowChoice <= 0 || rowChoice > rowCounter);
		        System.out.println("Which seat?");
		        do {
		            userSeat = scnr.next().charAt(0);
		            userSeatNum = userSeat - 65; //converts the char input into integer. -65 is because ascii for A is 65
	   	            if (userSeatNum < 0 || userSeatNum > columnCounter - 1) { 
			            System.out.println("Invalid Selection. Please select a valid seat");
	   	            }
		        } while (userSeatNum < 0 || userSeatNum > columnCounter - 1);
		        do {
		           System.out.println("How many adult tickets?");
	               adultTix = scnr.nextInt();
		        } while (adultTix < 0);
	            do {
		           System.out.println("How many child tickets?");
	               childTix = scnr.nextInt();
		        } while (childTix < 0);
	            do {
		           System.out.println("How many senior tickets?");
	               seniorTix = scnr.nextInt();
		        } while (seniorTix < 0);
	            if(seatAvailability(auditoriumArray, rowChoice, userSeatNum, adultTix, childTix, seniorTix)) { //if the seats are available, book it
					reserveSeats(auditoriumArray, rowChoice, userSeatNum, adultTix, childTix, seniorTix);
				}
				else {
						bestAvailableSeats(auditoriumArray, rowChoice, columnCounter, adultTix, childTix, seniorTix, scnr); //otherwise show best available
					}
			}
			writeAuditoriumToFile(auditoriumArray, rowCounter, columnCounter); //write the current state of the auditorium to AuditoriumFinal.txt
		} while (userInput != 2);
		reportDisplay(auditoriumArray, rowCounter, columnCounter); //displays the report at the end of the program
	}
}
