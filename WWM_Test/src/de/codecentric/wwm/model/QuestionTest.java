package de.codecentric.wwm.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuestionTest {

	@Test
	public void testIsAnswerCorrect() {	
		Question q = new Question(1, "Was bin ich?", new String[] {"Ein Mensch", "Ein Hund", "Eine Katze", "Ein Fisch" }, 0);
		
		assertTrue(q.isAnswerCorrect(0));
		assertEquals(0,q.getCorrectAnswer());
	}
}
