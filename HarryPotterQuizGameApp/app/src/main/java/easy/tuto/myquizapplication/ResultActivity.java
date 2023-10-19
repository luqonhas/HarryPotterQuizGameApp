package easy.tuto.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    void updateResultMessage(int score) {
        TextView resultMessageTextView = findViewById(R.id.message);
        ImageView resultImageView = findViewById(R.id.image_result);

        // Calcule a mensagem com base na pontuação
        String resultMessage;
        int resultImageId;

        if (score < 5) {
            resultMessage = "Você é realmente um trouxa. Tente outra vez!";
            resultImageId = R.drawable.resultado1;
        } else if (score >= 5 && score <= 7) {
            resultMessage = "Você não é um bruxo, mas não é totalmente um trouxa...Seria você um FAKENATTY?! Tente outra vez!";
            resultImageId = R.drawable.resultado2;
        } else if (score >= 8 && score <= 9) {
            resultMessage = "Você realmente é um bruxo, mas daqueles que falam Leviosa ao invés de Leviosá. Tente outra vez!";
            resultImageId = R.drawable.resultado3;
        } else {
            resultMessage = "Você não é FAKENATTY, seria você o próximo Alvo Dumbledore?";
            resultImageId = R.drawable.resultado4;
        }

        resultMessageTextView.setText(resultMessage);
        resultImageView.setImageResource(resultImageId);
    }

    void restartQuiz() {
        // Adicione o código para reiniciar o quiz, se necessário
        // Por exemplo, você pode iniciar a atividade principal novamente
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Fecha a tela de resultado e volta para a atividade principal
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        int totalQuestions = intent.getIntExtra("totalQuestions", 0);

        // Calcular o resultado e exibi-lo
        String resultText = "Você acertou " + score + " de " + totalQuestions + " questões";
        TextView resultTextView = findViewById(R.id.result_text);
        resultTextView.setText(resultText);

        // Carregar a imagem de resultado (substitua 'result_image' pelo ID da sua ImageView)
        ImageView resultImageView = findViewById(R.id.image_result);
        resultImageView.setImageResource(R.drawable.pergunta1); // Substitua 'result_image' pela imagem desejada

        // Atualizar a mensagem de resultado com base na pontuação
        updateResultMessage(score);

        // Configurar o botão de reinício para reiniciar o quiz
        Button restartButton = findViewById(R.id.restart_button);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartQuiz();
                finish(); // Fecha a tela de resultado e volta para o início do quiz
            }
        });
    }
}
