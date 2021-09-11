package Trivia;

import java.util.ArrayList;

import java.util.Scanner;

public class Game {
	Scanner in = new Scanner(System.in);
	private Player player = new Player(0);
	private boolean setted = false;
	private boolean finished = false;
	private ArrayList<Question> questions = new ArrayList<Question>();
	
	public boolean isSetted() {
		return setted;
	}

	public void setSetted(boolean setted) {
		this.setted = setted;
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions() {
		int count = 0;
		do {
			//read question
			String question = "e.g. question " + count + "?";
			Option[] options = new Option[4];
			for (int i = 0; i < options.length; i++) {				
				//read statements (first must be the correct answer)
				String statement = "e.g. statement " + count + "." + i; 
				options[i] = new Option(statement);
				if (i == 0) 
					options[i].setCorrect(true);				
			}
			
			Question tempQuestion = new Question((count / 5), question, options);
			this.questions.add(tempQuestion);
			count++;			
		} while (getQuestions().size() < 25);
		setSetted(true);
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public void starts() {
		int round = 0;
		int ans;
		ArrayList<Question> questionByLevel = new ArrayList<Question>();
		ArrayList<Option> tempOptions = new ArrayList<Option>();
		
		do {
			//getting all the questions by level
			for (int i = 0; i < questions.size(); i++) {
				if(this.questions.get(i).getLevel() == round)
					questionByLevel.add(this.questions.get(i));
			}
			//choosing and printing one randomly
			int position = (int)(questionByLevel.size() * Math.random());			
			System.out.println(questionByLevel.get(position).getQuestion());
			//finding the options for the questing choosed
			for(Option i : questionByLevel.get(position).getOptions()) {
				tempOptions.add(i);
			}
//			String correctStatement = questionByLevel.get(position).getOptions()[0].getStatement();
			//showing options randomly ordered to choose the answer
			int i = 0;
			int correctPosition = 0;
			do {
				System.out.print("option " + (i + 1));
				position = (int)(tempOptions.size() * Math.random());				
				System.out.println(": " + tempOptions.get(position).getStatement());
				if (tempOptions.get(position).isCorrect()) {
					correctPosition = i + 1;
				}
				tempOptions.remove(position);
				i++;
			} while (tempOptions.size() > 0);
			System.out.println("option 5: Quit");
			// read answer
			ans = in.nextInt();
			if (ans == 5 || ans != correctPosition)
				setFinished(true);			
			else {
				player.setScore(player.getScore() + (round + 1) * 5);
				System.out.println("Correct! Your score is " + player.getScore());
//				if (round == 5) {
//					setFinished(true);
//				}
			}
			round++;
			
		} while (round < 5 && !isFinished());
		setFinished(true);
	}
	
	public void finishes() {
		if (player.getScore() > 0) {
			String name = "e.g. name";
			player.setName(name);
			System.out.println("Congratulations " + player.getName() + "!");
		} else {
			System.out.println("Sorry, too low score. Try again!");
		}
	}

}
