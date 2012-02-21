package de.codecentric.wwm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.codecentric.wwm.model.Question;
import de.codecentric.wwm.model.QuestionRepository;

public class QuestionRepositoryReader {

	private final BufferedReader buffreader;
	private final QuestionRepository repository;

	public QuestionRepositoryReader(InputStream is) {
		final InputStreamReader inputreader = new InputStreamReader(is);
		this.buffreader = new BufferedReader(inputreader);	
		repository = new QuestionRepository();
	}

	public QuestionRepository getRepository() {
		Question q;
		while((q = getNextElement()) != null) { 
			repository.addQuestion(q);
		}
		return repository;
	}
	
	
	public Question getNextElement() {
		Question result = null;
		String line = null;
		int lineNo = 0;
		int level = 0;       
		String question = null; 
		String[] options = new String[4];
		String answer = null;      
		
		try {
			while((line = buffreader.readLine()) != null) {	
				if(isComment(line)) {
					lineNo=0;
				} else {
					lineNo++;
					switch (lineNo) {
						case 1 : level = Integer.parseInt(line.trim());
							break;
						case 2 : answer = line.trim();
							break;
						case 3 : question = line.trim();
							break;
						default : options[lineNo-4] = line;
				    }
					if(lineNo == 7) {
						int intAnswer = answer.toUpperCase().toCharArray()[0] - 64;
						result = new Question(level, question, options, intAnswer);
						break;
					}
					
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e); 
		}
		return result;
	}

	private boolean isComment(String line) {
		return line.startsWith("#") || line.trim().equals("");
	}
	
}
