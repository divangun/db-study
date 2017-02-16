package test.vcnc.co.kr.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.vcnc.co.kr.greendao.model.DaoSession;
import test.vcnc.co.kr.greendao.model.User;
import test.vcnc.co.kr.greendao.model.UserDao;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text_view) TextView textView;
    @BindView(R.id.insert_button) Button insertButton;
    @BindView(R.id.read_button) Button readButton;
    @BindView(R.id.read_multiple_button) Button readMultipleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        insertButton.setOnClickListener(v -> {
            User user = new User(null, textView.getText().toString());
        });

        readButton.setOnClickListener(v -> {

        });

        readMultipleButton.setOnClickListener(v -> {});

    }
}
