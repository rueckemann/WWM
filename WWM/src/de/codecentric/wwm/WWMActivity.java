package de.codecentric.wwm;

import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import de.codecentric.wwm.model.Question;
import de.codecentric.wwm.model.QuestionRepository;

public class WWMActivity extends Activity {
	
	/* the repository of all the available questions */
	QuestionRepository repository;
	
	/* the question currently shown to the user */
	Question currentQuestion;
	
	/* the rating bar showing level the user has already reached */ 
	RatingBar ratingBar;

	/* the current level the user has reached */
	private int level = 0;

	/* the retry button */
	Button btRetry;

	/* a central listener handling clicks on all four result buttons */ 
	View.OnClickListener resultClickListener = new View.OnClickListener() {
        public void onClick(View v) {
     	    Button clickedResult = (Button)v;
     	    if(isAnswerValid(clickedResult)) {
     	    	Toast.makeText(getBaseContext(), "Richtig !", Toast.LENGTH_SHORT).show();
     	    	ratingBar.setRating(level);
     	    	if(level < repository.getMaxLevel()) {
     	    		displayNextQuestion();
     	    	} else {
     	    		Toast.makeText(getBaseContext(), "Herzlichen Gl�ckwunsch!\nAlle Fragen richtig beantwortet.", Toast.LENGTH_SHORT).show();
     	    		btRetry.setVisibility(View.VISIBLE);
     	    	}
     	    } else {
     	    	Toast.makeText(getBaseContext(), "Leider falsch. Die richtige Antwort w�re \"" + currentQuestion.getOptions()[currentQuestion.getCorrectAnswer()-1] + "\" gewesen.", Toast.LENGTH_SHORT).show();
     	    	btRetry.setVisibility(View.VISIBLE);
     	    }
        }

    };


	/*
	 * the central entry point for all Activities
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
               
        /* init Button click handler */
        initButtonListener();
        /* load the question repository from a file */
		readQuestionRepositoryFromFile();
        
        ratingBar = (RatingBar)findViewById(R.id.ratingBar1);
        ratingBar.setIsIndicator(true);
        ratingBar.setNumStars(repository.getMaxLevel());
        ratingBar.setMax(repository.getMaxLevel());
        ratingBar.setStepSize(1);

		/* display a question from the repository */
        displayNextQuestion();
    }

	    
	private void initButtonListener() {
        Button button = (Button) findViewById(R.id.btOption1);
        button.setOnClickListener(resultClickListener); 
		button = (Button) findViewById(R.id.btOption2);
		button.setOnClickListener(resultClickListener);
		button = (Button) findViewById(R.id.btOption3);
		button.setOnClickListener(resultClickListener);
		button = (Button) findViewById(R.id.btOption4);
		button.setOnClickListener(resultClickListener);

		btRetry = (Button) findViewById(R.id.btRetry);
        btRetry.setVisibility(View.INVISIBLE);
		btRetry.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ratingBar.setRating(0);
				ratingBar.setNumStars(repository.getMaxLevel());
				level = 0;
				btRetry.setVisibility(View.INVISIBLE);
				displayNextQuestion();
			}
		});	
	}

	private void readQuestionRepositoryFromFile() {
		InputStream is = this.getResources().openRawResource(R.raw.fragenkatalog);
		QuestionRepositoryReader qr = new QuestionRepositoryReader(is);
		repository = qr.getRepository();
	}
	
	private void displayNextQuestion() {
		level ++;
		currentQuestion = repository.getRandomQuestion(level);
		TextView tvQuestion = (TextView) findViewById(R.id.tvQuestion);
		tvQuestion.setText(currentQuestion.getQuestion());
		Button btOption1 = (Button) findViewById(R.id.btOption1);
		btOption1.setText(currentQuestion.getOptions()[0]);
		Button btOption2 = (Button) findViewById(R.id.btOption2);
		btOption2.setText(currentQuestion.getOptions()[1]);
		Button btOption3 = (Button) findViewById(R.id.btOption3);
		btOption3.setText(currentQuestion.getOptions()[2]);
		Button btOption4 = (Button) findViewById(R.id.btOption4);
		btOption4.setText(currentQuestion.getOptions()[3]);
	}

	private boolean isAnswerValid(Button clickedResult) {
		int userAnswer=0;
		switch(clickedResult.getId()) {
			case R.id.btOption1 : userAnswer = 1; break;
			case R.id.btOption2 : userAnswer = 2; break;
			case R.id.btOption3 : userAnswer = 3; break;
			case R.id.btOption4 : userAnswer = 4; break;	
		}
		return currentQuestion.isAnswerCorrect(userAnswer);
	}

}