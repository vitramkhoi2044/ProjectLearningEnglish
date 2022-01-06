package com.example.learningenglish;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ActivityTutorialEnglishQuiz extends Activity {
    private TextView txt;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        txt = (TextView)findViewById(R.id.TxtTutorial);
        btn = (Button)findViewById(R.id.BtnBackTutorial);
        txt.setText("Với mỗi câu hỏi, người chơi sẽ có 20 giây " +
                "để trả lời, thời gian trả lời sẽ được cập nhật " +
                "liên tục ở góc trái để người dùng có thể " +
                "hoàn thành trong thời gian cho phép. Người " +
                "dùng chọn câu trả lời bằng cách click chọn " +
                "vào câu trả lời tương ứng sau đó nhấn nút " +
                "Submit để chuyển sang câu tiếp theo. Nếu " +
                "trong trường hợp người dùng không bấm " +
                "nút Submit nhưng đã chọn câu trả lời thì hệ " +
                "thống vẫn ghi nhận và chuyển sang câu tiếp theo.");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("");
                finish();
            }
        });
    }
}
