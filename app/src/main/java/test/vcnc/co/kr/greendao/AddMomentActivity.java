package test.vcnc.co.kr.greendao;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.Date;
import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.vcnc.co.kr.greendao.model.ImageInfo;
import test.vcnc.co.kr.greendao.model.Moment;

public class AddMomentActivity extends AppCompatActivity {

    @BindView(R.id.input_id_view) EditText inputId;
    @BindView(R.id.input_date_view) EditText inputDate;
    @BindView(R.id.input_author_view) EditText inputAuthor;
    @BindView(R.id.input_image_info_view) EditText inputImageInfo;

    @BindView(R.id.insert_button) View insertButton;
    @BindView(R.id.result_view) TextView resultView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_moment);
        setTitle("AddImage");
        ButterKnife.bind(this);

        insertButton.setOnClickListener(v -> {
            try {
                Dao<Moment, String> dao = TestApplication.getApplication().getHelper().getMomentDao();
                Dao<ImageInfo, String> imageDao = TestApplication.getApplication().getHelper().getImageDao();

                Moment moment = new Moment();
                moment.setId(inputId.getText().toString());
                moment.setMomentDate(new Date(Long.parseLong(inputDate.getText().toString())));
                moment.setAuthor(inputAuthor.getText().toString());

                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setId(inputImageInfo.getText().toString());
                imageInfo.setCount(1);

//                ImageInfo imageInfo = imageDao.queryForId(inputImageInfo.getText().toString());
//                imageInfo.setCount(imageInfo.getCount() + 1);
                imageDao.update(imageInfo);
                moment.setImageInfo(imageInfo);


                moment.update();

                Dao.CreateOrUpdateStatus createOrUpdateStatus = dao.createOrUpdate(moment);
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

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}
