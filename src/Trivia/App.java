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

		game.setQuestions();

		if (game.isSetted()) {
			System.out.println("WELCOME TO YOUR TRIVIA GAME!!!");
			System.out.println();
			game.starts();
		}

		if (game.isFinished()) {
			if (game.getPlayer().getScore() > 0) {
				System.out.print("Your name? ");
				String name = in.nextLine().strip();
				game.finishes(name);
				System.out.println("Congratulations " + game.getPlayer().getName() + "!");
				
				try (Statement sql = con.createStatement()){
					int result = sql.executeUpdate("INSERT INTO players('name', 'score') VALUES ('" + game.getPlayer().getName() + "'," + game.getPlayer().getScore() + ");");
					if(result != 0)
						System.out.println("You will be added to the top");
					
				} catch (Exception e) {
					System.out.println("Bad request");
				}
				
			} else {
				System.out.println("Sorry, too low score. Try again!");
			}

		}

		try (Statement sql = con.createStatement()) {
			ResultSet rset = sql.executeQuery("SELECT name, score FROM players ORDER BY score DESC;");
			while (rset.next()) {
				System.out.printf("%-20s %-10s%n", rset.getString("name"), rset.getInt("score"));
			}
		} catch (Exception e) {
			System.out.println("Something went wrong");
		}

		in.close();
	}

}
