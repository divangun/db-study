package test.vcnc.co.kr.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.company_info) TextView textView;

    @BindView(R.id.edit_text_user_name) EditText username;
    @BindView(R.id.edit_text_company_id) EditText companyId;
    @BindView(R.id.find_company) EditText getCompanyId;

    @BindView(R.id.insert_button) Button insertButton;
    @BindView(R.id.read_button) Button readButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        insertButton.setOnClickListener(v -> {
            String userKey = System.currentTimeMillis() + "";
            String companyKey = companyId.getText().toString();
        });

        readButton.setOnClickListener(v -> {
            String companyId = getCompanyId.getText().toString();
        });
    }
}
