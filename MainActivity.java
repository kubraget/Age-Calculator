import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
                calendar.setTimeInMillis(System.currentTimeMillis());
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar dob = new GregorianCalendar(year, month, dayOfMonth);
                        long diffInMillies = Math.abs(calendar.getTimeInMillis() - dob.getTimeInMillis());
                        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                        result.setText("Your age is " + diff / 365 + " years " + (diff % 365) + " days");
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}
