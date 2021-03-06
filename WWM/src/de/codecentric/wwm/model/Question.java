package de.codecentric.wwm.model;

public class Question {
	private int level;
	private String question = new String();
	private String[] options = new String[4];
	private int correctAnswerIndex;
	
	
	public Question(int level, String question, String[] options, int answer) {
		this.level = level;
		this.question = question;
		this.options = options;
		this.correctAnswerIndex = answer;
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
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	public int getCorrectAnswer() {
		return correctAnswerIndex;
	}
	public void setCorrectAnswer(int answer) {
		this.correctAnswerIndex = answer;
	}
	
	public boolean isAnswerCorrect(int index) {
		return index == this.correctAnswerIndex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + level;
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (level != other.level)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}
			
}
