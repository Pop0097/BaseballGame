import java.util.Scanner;

public class BaseballGame {

	static Scanner input = new Scanner(System.in);

	public static void main(String args[]) {
		String team1 = "", team2 = "";
		int[] players = {0, 0, 0, 0, 0};
		int[] counter = {0, 0, 0, 0, 0};
		int innings = 1, extraInnings;
		int team1Score = 0, team2Score = 0;
		int bats, outs, difference;
		int hit = 0;
		int i;

		//asks for the team names
		System.out.println("Enter the name of the first team:");
		team1 = input.nextLine();
		System.out.println("Enter the name of the second team:");
		team2 = input.nextLine();
		System.out.println("\nRules: \n If the player hits a single, type \"1.\"\n If the player hits a double, type \"2.\"\n If the player hits a tripple, type \"3.\"\n If the player hits a home run, type \"4.\" \n If the player is sent out, type \"0.\"");
		System.out.println();

		//This is where the user will determine the results for each team
		do {
			System.out.println("Current score:"); //presents scores
			System.out.println(team1 + ": " + team1Score);
			System.out.println(team2 + ": " + team2Score);
			System.out.println();

			bats = 1; //resets to bats to 1 for each inning

			while (bats <= 2) { //this while loop represents one inning.
				i = 0;
				outs = 0;
				for(int j = 0; j < 5; j++){ //resets the players positions to 0
					players[j] = 0;
					counter[j] = 0;
				}

				if(innings<=3) {
					System.out.println("Inning " + innings + ":\n");
				}
				else{ //if there is an extra inning, this if statement will tell the user there is one.
					extraInnings = innings - 3;
					System.out.println("Extra Inning " + extraInnings + ":\n" );
				}

				if(bats == 1){ //if it is the first bat, team one will go
					System.out.println(team1 + " is now at bat:" );
				}else {
					System.out.println(team2 + " is now at bat:" );
				}

				do {
					if(i>4) {
						i = 0;
					}
					hit = getUserInput(); //calls method to get user input
					if (hit >=1 && hit <=4) { //if the number means that the ball was hit, the code in this if statement runs
						players[i] += hit; //the number inputed is added, making the player go to the corresponding base. 1 is first base. 2 is second base. 3 is third base. 4 is home.
						for (int c = 0; c < 5; c++) {//loop repeated four times in order to ensure all players move
							//These if statements check if the players need to move.
							if(players[0]<= players[1] && players[1] != 0 && players[0] != 0 && counter[0] == counter[1]) {  //Checks if a player passes the player that bat before them
								/*
								 * Since there are only four players.
								 * If player 1 (players[0]) scores a point, and bats again (on his second run) when player 2 (players[1]) is still on his first run, the program will think player 1 is behind player 2 when he is in fact in front.
								 * The counters check to see if the players are still on the same run, and not on different ones. If they are on different runs, then the program acknowledges that Player 1 is ahead of Player 2
								 */
								difference = players[1] - players[0] + 1; //the difference between the two players is checked (remember, the value 1,2,3,4, etc.) is the base the player is at. One is added in case they are on the same base
								players[0] += difference; //the difference is added to the player who is passed, so they proceed to the base after the player that bats after them
							}
							//the comments in the if statement above apply to all statements below
							if(players[1]<= players[2] && players[2] != 0 && players[1] != 0 && counter[1] == counter[2]) {
								difference = players[2] - players[1] + 1;
								players[1] += difference;
							}
							if(players[2]<= players[3] && players[3] != 0 && players[2] != 0 && counter[2] == counter[3]) {
								difference = players[3] - players[2] + 1;
								players[2] += difference;
							}
							if(players[3]<= players[4] && players[4] != 0 && players[3] != 0 && counter[3] ==  counter[4]) {
								difference = players[4] - players[3] + 1;
								players[3] += difference;
							}
							if(players[4]<= players[0] && players[4] != 0 && players[0] != 0) {
								difference = players[0] - players[4] + 1;
								players[4] += difference;
							}
						}
						//Checks if players have reached the home plate. It checks if their value is greater than 4. Since 4 is the home plate. If they are equal to it or greater, they are past home, and thus have scored a point.
						for(int j = 0; j < 5; j++) {
							if(players[j] >= 4) {
								if(bats == 1) {
									team1Score++;
								} else {
									team2Score++;
								}
								counter[j]++; //counter is increased so the program knows how many times they have batted. The purpose of this is explained above
								players[j] = 0; //resets the value of the players to 0 (at bat) so they can bat again
							}
						}
						i++;
					}
					else { //if the user inputs 0
						outs++;
					}
				}while (outs < 3);
				bats++; //increases the bats counter so the second team goes to bat
			}
			innings++; //increases the inning number
		}while(innings <= 3 || team1Score == team2Score); //checks to see if three innings have not been completed, or if the score between the teams are equal. If any of these statements are true, the do loop will repeat

		//checks to see which team wins
		if(team1Score>team2Score) {
			System.out.println(team1 + " wins! The score was " + team1Score + " - " + team2Score);
		}
		else{
			System.out.println(team2 + " wins! The score was " + team2Score + " - " + team1Score);
		}
	}

	public static int getUserInput() {
		boolean valid = true;
		String userInput = "";
		int stringLength, ACSII;
		char firstLetter;
		int hit;

		do { //this loop verifies that the user's input is correct. It takes the input as a string and then changes it to an integer to ensure that the code still runs even if the user inputs a string
			System.out.println("\nWhat did the player hit?");
			userInput = input.nextLine(); //gets input from user what the player scores
			stringLength = userInput.length(); //checks the length of the input
			firstLetter = userInput.charAt(0); //makes the first character a char
			ACSII = (int) firstLetter; //gets ACSII number
			if(stringLength > 1 || ACSII < 48 || ACSII > 52) { //checks if the thing is too long or if the ACSII characters do not match the reserved inputs
				System.out.println("Invalid input, make sure the input is either 0, 1, 2, 3, or 4.");
				valid = false;
			}
			else {
				valid = true;
			}
		} while(!valid);

		if(ACSII == 48) { //attributes the ACSII code to the appropriate result
			hit = 0;
		}
		else if(ACSII == 49) {
			hit = 1;
		}
		else if(ACSII == 50){
			hit = 2;
		}
		else if(ACSII == 51) {
			hit = 3;
		}
		else {
			hit = 4;
		}

		return hit;
	}
}
