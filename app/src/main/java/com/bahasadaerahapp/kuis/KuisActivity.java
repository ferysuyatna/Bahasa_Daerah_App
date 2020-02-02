package com.bahasadaerahapp.kuis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bahasadaerahapp.R;
import com.bahasadaerahapp.db.helper.QuizDbHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class KuisActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewDifficulty;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;
    private long backPressedTime;

    private int maxSoal = 4;

    //TO DO menambahkan soal untuk random
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewDifficulty = findViewById(R.id.text_view_difficulty);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();

        Intent intent = getIntent();
        final String difficulty = intent.getStringExtra(StartingQuizActivity.EXTRA_DIFFICULTY);

        textViewDifficulty.setText("Tingkat Kesulitan : " + difficulty);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ImageView mg = new ImageView(this);
        mg.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        switch (difficulty) {
            case "Level 1":
                maxSoal = 4;
                mg.setBackgroundResource(R.drawable.bali);
                break;
            case "Level 2":
                maxSoal = 5;
                mg.setBackgroundResource(R.drawable.bali);
                break;
            case "Level 3":
                maxSoal = 6;
                mg.setBackgroundResource(R.drawable.bali);
                break;
            case "Level 4":
                maxSoal = 7;
                mg.setBackgroundResource(R.drawable.bali);
                break;
            case "Level 5":
                maxSoal = 5;
                mg.setBackgroundResource(R.drawable.bali);
                break;
        }

        builder.setView(mg);
        builder.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (savedInstanceState == null) {
                    QuizDbHelper dbHelper = new QuizDbHelper(KuisActivity.this);
                    questionList = dbHelper.getQuestions(difficulty);
                    questionCountTotal = questionList.size();
                    Collections.shuffle(questionList);

                    showNextQuestion();
                } else {
                    questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
                    questionCountTotal = questionList.size();
                    questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
                    currentQuestion = questionList.get(questionCounter - 1);
                    score = savedInstanceState.getInt(KEY_SCORE);
                    timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
                    answered = savedInstanceState.getBoolean(KEY_ANSWERED);

                    if (!answered) {
                        startCountDown();
                    } else {
                        updateCountDownText();
                        showSolution();
                    }
                }
            }
        });
        builder.show();


        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(KuisActivity.this, "Silakan pilih jawaban dulu", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (questionCounter < maxSoal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            questionCounter++;
            textViewQuestionCount.setText("Pertanyaan: " + questionCounter + "/" + maxSoal); //questionCountTotal
            answered = false;
            buttonConfirmNext.setText("Konfirmasi");

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }

    private void checkAnswer() {
        answered = true;

        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()) {
            score++;
            textViewScore.setText("Skor: " + score);
        }

        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Jawaban 1 yang benar");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Jawaban 2 yang benar");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Jawaban 3 yang benar");
                break;
        }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Lanjut");
        } else {
            buttonConfirmNext.setText("Selesai");
        }
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Tekan kembali untuk menyelesaikan", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }
}