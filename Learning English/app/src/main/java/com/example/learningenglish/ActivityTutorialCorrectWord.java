package com.example.learningenglish;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ActivityTutorialCorrectWord extends Activity {
    private TextView txt;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        txt = (TextView)findViewById(R.id.TxtTutorial);
        btn = (Button)findViewById(R.id.BtnBackTutorial);
        txt.setText("Khi bắt đầu người chơi sẽ có 30s cho mỗi " +
                "câu để đoán từ bị sắp xếp lộn xộn ngẫu " +
                "nhiên và điền đáp án vào ô trả lời bên dưới. " +
                "Nếu hết 30s hệ thống sẽ tự động kiểm tra " +
                "đáp án và chuyển sang câu tiếp theo, còn " +
                "nếu chưa hết thời gian người có thể bấm nút " +
                "Submit để kiểm tra kết quả và chuyển sang " +
                "câu tiếp theo. Nếu đúng liên tiếp 3 câu sẽ " +
                "được một sao và sai liên tiếp 3 câu sẽ trừ 1 " +
                "sao (Nếu có). Khi người chơi hết sao mà " +
                "vẫn sai liên tiếp 3 câu thì trò chơi sẽ kết thúc.");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("");
                finish();
            }
        });
    }

}
