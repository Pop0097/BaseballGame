import java.util.Scanner;

public class BaseballGame3 {
	
	public static void main(String args[]) {
	
		Scanner input = new Scanner(System.in);	
		
		String team1 = "";
		String team2 = "";
		String result1 = "";
		char firstLetter;
		
		boolean valid = false;

		int[] players = new int[5];
			players[0] = 0;
			players[1] = 0;
			players[2] = 0;
			players[3] = 0;
			players[4] = 0;
		int i, outs, counter1, counter2, counter3, counter4, counter5, extraInnings, stringLength, ACSII, bats, difference; //these variables are initialized later in the order they are presented
		int innings = 1;
		int team1Score = 0;
		int team2Score = 0;
		int hit = 0;
		
		
		//asks for the team names
		System.out.println("Enter the name of the first team:");
		team1 = input.nextLine();
		System.out.println("Enter the name of the second team:");
		team2 = input.nextLine();
		System.out.println("\nRules: \n If it is a single, type \"1.\"\n If it is a double, type \"2.\"\n If it is a tripple, type \"3.\"\n If it is a home run, type \"4.\" \n If it is an out, type \"0.\"");
		System.out.println();
		
		do {
			
			System.out.println("Current scores:"); //presents scores
			System.out.println(team1 + ": " + team1Score);
			System.out.println(team2 + ": " + team2Score);
			System.out.println();
			
			bats = 1; //resets to bats to 1 for each inning
				
			while (bats <= 2) { //This is where the user will determine the results for each team
					
				i = 0;
				outs = 0;
				counter1 = 0;
				counter2 = 0;
				counter3 = 0;
				counter4 = 0;
				counter5 = 0;
				players[0] = 0;
				players[1] = 0;
				players[2] = 0;
				players[3] = 0;
				players[4] = 0;
				
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
					
					do {
						
						valid = false;
						System.out.println("\nWhat did the player hit?");
						result1 = input.nextLine(); //gets input from user what the player scores
						stringLength = result1.length(); //checks the length of the input
						firstLetter = result1.charAt(0); //makes the first character a char
						ACSII = (int) firstLetter; //gets ACSII number
						
						if(stringLength > 1 || ACSII < 48 || ACSII > 52) { //checks if the thing is too long or if the ACSII characters do not match the reserved inputs
							
							System.out.println("Invalid input, make sure the input is either 0, 1, 2, 3, or 4.");
							valid = true;
							
						}
						else {//if ACSII code is not invalid, it is processed by the following statements

							//attributes the ACSII code to the appropriate result
							if(ACSII == 48) { 
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
						}					
					} while(valid);
					
					if (hit >=1 && hit <=4) { //if the number means that the ball was hit, this program runs
								
						players[i] += hit; //the number inputed is added, making the player go to the corresponding base. 1 is first base. 2 is second base. 3 is third base. 4 is home.
							
						for (int j=0;j<=4;j++) {//loop repeated four times in order to ensure all players move
								
							//These if statements check if the players need to move. 
								
							if(players[0]<= players[1] && players[1] != 0 && players[0] != 0 && counter1 == counter2) {  //Checks if a player passes the player that bat before them
										
								difference = players[1] - players[0] + 1; //the difference between the two players is checked (remember, the value 1,2,3,4, etc.) is the base the player is at. One is added in case they are on the same base
								players[0] += difference; //the difference is added to the player who is passed, so they proceed to the base after the player that bats after them
														
							}
							//the comments above apply to all statements below
							if(players[1]<= players[2] && players[2] != 0 && players[1] != 0 && counter2 == counter3) {
										
								difference = players[2] - players[1] + 1;
								players[1] += difference;
									
							}
									
							if(players[2]<= players[3] && players[3] != 0 && players[2] != 0 && counter3 == counter4) { 
								//Since there are only four players. 
								//If player 3 scores a point, and bats again (on his second run) when player 4 is still on his first run, the program will think player3 is behind player 4 when he is in fact in front.
								//The counters check to see if they are still on the same run, and not on different ones. If they are on different runs, then the program awknowledges that Player4 is ahead of Player3 
								//This applies to all if statements in the for loop
										
								difference = players[3] - players[2] + 1;
								players[2] += difference;
										
							}
									
							if(players[3]<= players[4] && players[4] != 0 && players[3] != 0 && counter5 ==  counter4) {
										
								difference = players[4] - players[3] + 1;
								players[3] += difference;
									
							}
							if(players[4]<= players[0] && players[4] != 0 && players[0] != 0) {
									
								difference = players[0] - players[4] + 1;
								players[4] += difference;
									
							}

						}
								
						//Checks if players have reached the home plate. It checks if their value is greater than 4. Since 4 is the home plate. If they are equal to it or greater, they are past home, and thus have scored a point.
						if(players[0] >= 4) { 
							if(bats == 1) { //checks if it is team1 or 2 batting and adds score to appropriate team.
								team1Score++;
							} else {
								team2Score++;
							} 
							counter1++; //counter is increased so the program knows how many times they have batted. The purpose of this is explained above
							players[0] = 0; //resets the value of the players to 0 (at bat) so they can bat again
							
						}
						//the comments above apply to all if statements below
						if(players[1] >= 4) {
							if(bats == 1) {
								team1Score++;
							} else {
								team2Score++;
							}
							players[1] = 0;
							counter2++;
	
						} 
						
						if(players[2] >= 4) {
							if(bats == 1) {
								team1Score++;
							} else {
								team2Score++;
							}
							players[2] = 0;
							counter3++;
							
						}
							
						if(players[3] >= 4) {
							if(bats == 1) {
								team1Score++;
							} else {
								team2Score++;
							}
							players[3] = 0;
							counter4++;
																
						}
						if(players[4] >= 4) {
							if(bats == 1) {
								team1Score++;
							} else {
								team2Score++;
							}
							players[4] = 0;
							counter5++;
								
						}			
				
						i++;
					}
					else {
								
						outs++;
							
					}
				}while (outs < 3);
					
				bats++;	
			}
				
			innings++;	
		}while(innings <= 3 || team1Score == team2Score); //checks to see if three innings have not been completed, or if the score between the teams are equal. If any of these statements are true, the do loop will repeat 
		
		//checks to see which team wins
		if(team1Score>team2Score) { 
		
			System.out.println(team1 + " wins! The score was " + team1Score + " - " + team2Score);
			
		}
		else{
			
			System.out.println(team2 + " wins! The score was " + team2Score + " - " + team1Score);
			
		}
	}
}