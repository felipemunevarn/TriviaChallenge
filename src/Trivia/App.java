package Trivia;

public class App {

	public static void main(String[] args) {
		Game game = new Game();
		
		game.setQuestions();
		
		if(game.isSetted())
			game.starts();
		
		if(game.isFinished())
			game.finishes();
		
		
	}

}
