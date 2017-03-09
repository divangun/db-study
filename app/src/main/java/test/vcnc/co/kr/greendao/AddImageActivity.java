package test.vcnc.co.kr.greendao;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.vcnc.co.kr.greendao.model.ImageInfo;

public class AddImageActivity extends AppCompatActivity {


    @BindView(R.id.input_id_view) EditText inputId;
    @BindView(R.id.input_url_view) EditText inputUrl;
    @BindView(R.id.input_width_view) EditText inputWidth;
    @BindView(R.id.input_height_view) EditText inputHeight;

    @BindView(R.id.insert_button) View insertButton;
    @BindView(R.id.result_view) TextView resultView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        setTitle("AddImage");
        ButterKnife.bind(this);

        insertButton.setOnClickListener(v -> {
            try {
                Dao<ImageInfo, String> dao = TestApplication.getApplication().getHelper().getImageDao();

                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setId(inputId.getText().toString());
                imageInfo.setUrl(inputUrl.getText().toString());
                imageInfo.setWidth(Integer.parseInt(inputWidth.getText().toString()));
                imageInfo.setHeight(Integer.parseInt(inputHeight.getText().toString()));
                imageInfo.setCount(0);

                Dao.CreateOrUpdateStatus createOrUpdateStatus = dao.createOrUpdate(imageInfo);
                if (createOrUpdateStatus.isCreated()) {
                    resultView.setText("Create!!");
                } else if (createOrUpdateStatus.isUpdated()) {
                    resultView.setText("Update!!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
