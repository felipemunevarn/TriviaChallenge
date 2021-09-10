package Trivia;

public class App {

	public static void main(String[] args) {
		Game game = new Game();
		game.setQuestions();
//		System.out.println("Todo joya!");
//		System.out.println(game.getQuestions().get(23).getQuestion());
//		System.out.println(game.getQuestions().get(23).getOptions()[3].getStatement());
		game.starts();
	}

}
