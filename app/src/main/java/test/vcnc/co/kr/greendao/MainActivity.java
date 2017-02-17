package test.vcnc.co.kr.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.vcnc.co.kr.greendao.model.Company;
import test.vcnc.co.kr.greendao.model.CompanyOpenDatabaseHelper;
import test.vcnc.co.kr.greendao.model.User;
import test.vcnc.co.kr.greendao.model.UserOpenDatabaseHelper;

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

        try {
            testOutOrmLiteDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        insertButton.setOnClickListener(v -> {
            String userKey = System.currentTimeMillis() + "";
            String companyKey = companyId.getText().toString();

            CompanyOpenDatabaseHelper todoCompanyOpenDatabaseHelper = OpenHelperManager.getHelper(this,
                    CompanyOpenDatabaseHelper.class);

            UserOpenDatabaseHelper userOpenDatabaseHelper = OpenHelperManager.getHelper(this, UserOpenDatabaseHelper.class);

            try {
                Dao<Company, String> companyDao = todoCompanyOpenDatabaseHelper.getDao();
                List<Company> companies = companyDao.queryForAll();

                Dao<User, String> userDao = userOpenDatabaseHelper.getDao();
                User user = new User();
                user.setId(userKey);
                user.setCompany(companies.get(0));
                user.setName("SangEel");

                userDao.create(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        readButton.setOnClickListener(v -> {
            String companyId = getCompanyId.getText().toString();

            CompanyOpenDatabaseHelper todoCompanyOpenDatabaseHelper = OpenHelperManager.getHelper(this,
                    CompanyOpenDatabaseHelper.class);
            try {
                Dao<Company, String> todoDao = todoCompanyOpenDatabaseHelper.getDao();
                List<Company> companyList = todoDao.queryForAll();

                StringBuilder builder = new StringBuilder();

                for (Company company : companyList) {
                    builder.append("Company : " + company.getCompanyName())
                            .append(" Size : " + (company.getUsers() == null ? 0 : company.getUsers().size())).append('\n');
                }

                textView.setText(builder.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


    private void testOutOrmLiteDatabase() throws SQLException {
        CompanyOpenDatabaseHelper todoCompanyOpenDatabaseHelper = OpenHelperManager.getHelper(this,
                CompanyOpenDatabaseHelper.class);

        Dao<Company, String> todoDao = todoCompanyOpenDatabaseHelper.getDao();
        Company company = new Company();
        company.setId(System.currentTimeMillis() + "");
        company.setCompanyName("VCNC");
        todoDao.create(company);
    }
}
