package test.vcnc.co.kr.greendao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.android.AndroidCompiledStatement;
import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.dao.CloseableIterable;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.vcnc.co.kr.greendao.model.ImageInfo;
import test.vcnc.co.kr.greendao.model.Moment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_moment_button) View momentViewButton;
    @BindView(R.id.view_image_button) View imageViewButton;


    @BindView(R.id.add_image_button) View addImageButton;

    @BindView(R.id.add_moment_button) View addMomentButton;
    @BindView(R.id.add_random_moment_button) View addRandomMomentButton;

    @BindView(R.id.remove_photo_button) View removeButton;
    @BindView(R.id.remove_photo_text) EditText photoText;
    @BindView(R.id.result_view) TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        addMomentButton.setOnClickListener(v -> startActivity(new Intent(this, AddMomentActivity.class)));

        addImageButton.setOnClickListener(v -> startActivity(new Intent(this, AddImageActivity.class)));

        addRandomMomentButton.setOnClickListener(v -> startActivity(new Intent(this, AddRandomMomentActivity.class)));

        momentViewButton.setOnClickListener(v -> {
            try {
                Dao<Moment, String> momentDao = TestApplication.getApplication().getHelper().getMomentDao();
                Dao<ImageInfo, String> imageDao = TestApplication.getApplication().getHelper().getImageDao();

                AndroidDatabaseResults androidDatabaseResults;

                QueryBuilder<Moment, String> qb = momentDao.queryBuilder();
                CloseableIterable<Moment> iterator = (CloseableIterable<Moment>) momentDao.iterator(qb.prepare());
                AndroidDatabaseResults results = iterator.getrwa




                List<Moment> momentList = momentDao.queryForAll();
                StringBuilder sb = new StringBuilder();

                Moment moment = momentList.get(0);

                sb.append("Moment\n")
                        .append("-id : " + moment.getId() + "\n")
                        .append("-author : " + moment.getAuthor() + "\n")
                        .append("-momentDate : " + moment.getMomentDate().toString() + "\n");
//                            .append("-ImageKey" + moment.getImageInfo().getId() + "\n")
//                            .append("-ImageCount" + moment.getImageInfo().getCount() + "\n");


                resultText.setText(sb.toString());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        imageViewButton.setOnClickListener(v -> {
            try {
                Dao<ImageInfo, String> imageDao = TestApplication.getApplication().getHelper().getImageDao();

                List<ImageInfo> imageInfos = imageDao.queryForAll();
                StringBuilder sb = new StringBuilder();
                for (ImageInfo imageInfo : imageInfos) {
                    sb.append("Image\n")
                            .append("-id : " + imageInfo.getId() + "\n")
                            .append("-url : " + imageInfo.getUrl() + "\n")
                            .append("-width : " + imageInfo.getWidth() + "\n")
                            .append("-height : " + imageInfo.getHeight() + "\n")
                            .append("-count : " + imageInfo.getCount() + "\n");
                }

                resultText.setText(sb.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        removeButton.setOnClickListener(v -> {
            try {
                Dao<ImageInfo, String> imageDao = TestApplication.getApplication().getHelper().getImageDao();
                imageDao.deleteById(photoText.getText().toString());

                resultText.setText("Delete Success");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }

//    private void testOutOrmLiteDatabase() throws SQLException {
//        CompanyOpenDatabaseHelper todoCompanyOpenDatabaseHelper = OpenHelperManager.getHelper(this,
//                CompanyOpenDatabaseHelper.class);
//
//        Dao<Company, String> todoDao = todoCompanyOpenDatabaseHelper.getDao();
//        Company company = new Company();
//        company.setId(System.currentTimeMillis() + "");
//        company.setCompanyName("VCNC");
//        todoDao.create(company);
//    }
}
