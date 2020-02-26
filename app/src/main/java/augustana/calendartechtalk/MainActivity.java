package augustana.calendartechtalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button);
        b.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        setEvent();
    }


    public void setEvent() {
        Calendar c = Calendar.getInstance();
        c.set(2020, 6, 20, 7, 30);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2020, 6, 20, 8, 30);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, c.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, "Dylan's birthday.")
                .putExtra(CalendarContract.Events.DESCRIPTION, "Celebration of Dylan's 22nd birthday!")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "TBD")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                .putExtra(Intent.EXTRA_EMAIL, "dylanhart16@augustana.edu");
        startActivity(intent);
    }
}
