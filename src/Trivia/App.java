package Trivia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws Exception, Throwable {
		Scanner in = new Scanner(System.in);
		Game game = new Game();
		
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:/home/pipemun/Documents/SofkaChallenge/db/Trivia.db");
		
		try (Statement sql = con.createStatement()){			
			ResultSet rset = sql.executeQuery("SELECT * FROM players");
			while(rset.next()) {
				System.out.printf("%-40s %-10s%n", rset.getString("name"), rset.getInt("score"));
			}
		} catch (Exception e) {
			System.out.println("Todo mal!");
		}
		
		game.setQuestions();
		
		if(game.isSetted())
			System.out.println("WELCOME TO YOUR TRIVIA GAME!!!");
			System.out.println();
			game.starts();
		
		if(game.isFinished()) {
			if (game.getPlayer().getScore() > 0) {
				System.out.print("Your name? ");
				String name = in.nextLine();
				game.finishes(name);
				System.out.println("Congratulations " + game.getPlayer().getName() + "!");
			} else {
				System.out.println("Sorry, too low score. Try again!");
			}
			
		}
		
		in.close();		
	}

}
