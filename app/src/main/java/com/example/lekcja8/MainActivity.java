package com.example.lekcja8;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView koscNr1;
    private ImageView koscNr2;
    private ImageView koscNr3;
    private ImageView koscNr4;
    private ImageView koscNr5;
    private Button thrown;
    private Button reset;
    private TextView gameResults;
    private TextView thrownResults;

    private final int[] diceDrawables = {
      R.drawable.dice1,
      R.drawable.dice2,
      R.drawable.dice3,
      R.drawable.dice4,
      R.drawable.dice5,
            R.drawable.dice6
    };

    private final Random random = new Random();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        koscNr1 = findViewById(R.id.dice1);
        koscNr2 = findViewById(R.id.dice2);
        koscNr3 = findViewById(R.id.dice3);
        koscNr4 = findViewById(R.id.dice4);
        koscNr5 = findViewById(R.id.dice5);

        thrown = findViewById(R.id.button_roll_dice);
        thrownResults = findViewById(R.id.text_roll_result);

        thrownResults.setText("Wynik tego losowania: ");

        thrown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollFiveDiceandShowResult();
            }
        });


    }
    private void rollFiveDiceandShowResult(){
        ImageView[] diceViews = {koscNr1, koscNr2,koscNr3,koscNr4,koscNr5};

        int sum = 0;
        for(ImageView diceView : diceViews){
            int roll = rollOneDie();
            sum += roll;
            int drawableRes = drawableForRoll(roll);
            diceView.setImageResource(drawableRes);
        }
        thrownResults.setText("Wynik tego losowania:");
    }
    private int rollOneDie(){
        return random.nextInt(6) + 1;
    }

    private int drawableForRoll(int roll){
        if (roll < 1 || roll > 6){
            return R.drawable.question_dice;
        }
        return diceDrawables[roll - 1];
    }


}