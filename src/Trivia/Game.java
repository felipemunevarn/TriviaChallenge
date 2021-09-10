package Trivia;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {
	private Player player;
	private boolean setted;
	private boolean finished = false;
	private ArrayList<Question> questions = new ArrayList<Question>();

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
			}
			
			Question tempQuestion = new Question((count / 5), question, options);
			this.questions.add(tempQuestion);
			System.out.println(getQuestions().get(count).getLevel());
			count++;			
		} while (getQuestions().size() < 25);
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public void starts() {
		int round = 0;		
		do {
			ArrayList<Question> questionByLevel = new ArrayList<Question>();
			for (int i = 0; i < questions.size(); i++) {
				if(questions.get(i).getLevel() == round)
					questionByLevel.add(questions.get(i));
			}
			int position = questionByLevel.size() * (int)Math.random();
			System.out.println(questionByLevel.get(position).getQuestion());
			ArrayList<Option> tempOptions = new ArrayList<Option>();
			for(Option i : questionByLevel.get(position).getOptions()) {
				tempOptions.add(i);
			}
			for (int i = 0; i < tempOptions.size(); i++) {
				System.out.print("opcion " + (i + 1));
				position = tempOptions.size() * (int)Math.random();				
				System.out.println(": " + tempOptions.get(position).getStatement());
				tempOptions.remove(position);
			}
			
			round++;
			System.out.println(round);
		} while (round < 4);
//			isFinished() == false || 
	}
	
	public void finishes() {		
		setFinished(true);
	}

}
