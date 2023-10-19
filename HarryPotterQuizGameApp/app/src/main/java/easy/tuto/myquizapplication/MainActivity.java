package easy.tuto.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score=0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa os elementos da interface do usuário
        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);
        submitBtn.setEnabled(false);

        // Define os ouvintes de clique para os botões
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        // Carrega a primeira pergunta
        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        // Define a cor de fundo de todos os botões para branco
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {
            // Botão de envio foi clicado
            if (!selectedAnswer.isEmpty()) {
                if (selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])) {
                    score++;
                }
                currentQuestionIndex++;
                loadNewQuestion();
            }
        } else {
            // Um dos botões de escolha foi clicado
            if (clickedButton.getText().toString().equals(selectedAnswer)) {
                // Se o botão clicado já estiver selecionado, desmarque-o
                selectedAnswer = "";
                clickedButton.setBackgroundColor(Color.WHITE);
                // Desabilite o botão de envio, pois nada está selecionado
                submitBtn.setEnabled(false);
            } else {
                // Se o botão clicado não estiver selecionado, selecione-o
                selectedAnswer = clickedButton.getText().toString();
                clickedButton.setBackgroundColor(Color.MAGENTA);
                // Habilita o botão de envio quando uma opção é selecionada
                submitBtn.setEnabled(true);
            }
        }
    }



    void loadNewQuestion() {
        // Desabilite o botão de envio sempre que uma nova questão for carregada
        submitBtn.setEnabled(false);

        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }

        // Define o texto da pergunta e das opções de resposta
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

        // Configure a imagem da questão atual
        ImageView questionImage = findViewById(R.id.question_image);
        questionImage.setImageResource(QuestionAnswer.images[currentQuestionIndex]);
    }


    void finishQuiz() {
        // Crie uma Intent para iniciar a ResultActivity
        Intent resultIntent = new Intent(this, ResultActivity.class);
        resultIntent.putExtra("score", score);
        resultIntent.putExtra("totalQuestions", totalQuestion);
        startActivity(resultIntent);
    }

}