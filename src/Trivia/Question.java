package Trivia;

import java.util.ArrayList;

public class Question {
	private int level;
	private String question;
	private Option[] options = new Option[4];
	
	public Question(int level, String question, Option[] options) {
		this.level = level;
		this.question = question;
		this.options = options;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Option[] getOptions() {
		return options;
	}

	public void setOptions(Option[] options) {
		this.options = options;
	}
	
	

	
	
	
	
}	
