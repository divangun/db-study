package test.vcnc.co.kr.greendao;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.vcnc.co.kr.greendao.model.ImageInfo;
import test.vcnc.co.kr.greendao.model.Moment;

public class AddRandomMomentActivity extends AppCompatActivity {

    @BindView(R.id.input_count) EditText countView;
    @BindView(R.id.insert_button) View insertButton;
    @BindView(R.id.result_view) TextView resultView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_random_moment);
        setTitle("Add Random Moment");
        ButterKnife.bind(this);

        insertButton.setOnClickListener(v -> {
            Dao<Moment, String> dao = null;
            try {
                dao = TestApplication.getApplication().getHelper().getMomentDao();
                Dao<ImageInfo, String> imageInfoStringDao = TestApplication.getApplication().getHelper().getImageDao();

                List<ImageInfo> imageInfoList = imageInfoStringDao.queryForAll();

                int count = Integer.parseInt(countView.getText().toString());
                for (int i = 0; i < count; i++) {
                    Moment moment = new Moment();
                    moment.setId(count + System.currentTimeMillis() + "");
                    moment.setAuthor("Author");
                    moment.setMomentDate(new Date(100000));
                    moment.setImageInfo(imageInfoList.get(0));
                    moment.setDao(dao);

                    moment.create();

                    resultView.setText("Complete " + i);
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
