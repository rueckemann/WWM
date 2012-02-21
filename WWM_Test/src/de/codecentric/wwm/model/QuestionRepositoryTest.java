package de.codecentric.wwm.model;


import org.junit.Test;
import static org.junit.Assert.*;


public class QuestionRepositoryTest  {

	private QuestionRepository repo = new QuestionRepository();

	@Test
	public void testAddQuestion() {	
		Question q = new Question(1, "Was bin ich?", new String[] {"Ein Mensch", "Ein Hund", "Eine Katze", "Ein Fisch" }, 0);
		repo.addQuestion(q);
		
		assertEquals("Was bin ich?", q.getQuestion());
		Question q1 = repo.getRandomQuestion(1);
		assertEquals("Was bin ich?", q1.getQuestion());
		
		Question q2 = repo.getRandomQuestion(2);
		assertNull(q2);
	}
	
	@Test
	public void testIsRandomQuestion() {	
		for(int i = 0; i < 100; i++) {
			Question q = new Question(1, "Question " + i, null, 0);
			repo.addQuestion(q);			
		}
		Question q1 = repo.getRandomQuestion(1);
		Question q2 = repo.getRandomQuestion(1);
		
		assertFalse(q1.equals(q2));
	}
		
	
}
