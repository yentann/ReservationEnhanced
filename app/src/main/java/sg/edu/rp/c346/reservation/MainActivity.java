package sg.edu.rp.c346.reservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText Name;
    EditText Mobile;
    EditText Size;
    EditText Day;
    EditText Time;
    CheckBox Smoke;
    Button reset;
    Button reserve;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.editTextName);
        Mobile = findViewById(R.id.editTextMobile);
        Size = findViewById(R.id.editTextPax);
        Day = findViewById(R.id.editTextDate);
        Time = findViewById(R.id.editTextTime);
        Smoke = findViewById(R.id.checkBox);
        reset = findViewById(R.id.buttonReset);
        reserve = findViewById(R.id.buttonConfirm);
        reset = findViewById(R.id.buttonReset);

        // Time
        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Time.setText("Time: " + hourOfDay + ":" + minute);
                    }
                };

                Date time = Calendar.getInstance().getTime();
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this, myTimeListener, time.getHours(), time.getMinutes(), true);

                myTimeDialog.show();


            }
        });

        // Day aka Date
        Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create the Listener to set the date
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Day.setText("Date: " + dayOfMonth + "/" + monthOfYear + "/" + year);
                    }
                };

                //Create the Date Picker Dialog
                Calendar calender = Calendar.getInstance();
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, myDateListener, calender.get(Calendar.YEAR), calender.get(Calendar.MONTH) - 1, calender.get(Calendar.DAY_OF_MONTH));
                Day.setText("Date: " + calender.get(Calendar.DAY_OF_MONTH) + "/" + (calender.get(Calendar.MONTH)-1) + "/" + calender.get(Calendar.YEAR));
                myDateDialog.show();

            }
        });


        // Reserve
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create the Dialog Builder

                String Namee = Name.getText().toString();
                String MobileNum = Mobile.getText().toString();
                String Pax = Size.getText().toString();


                String smokeornot = "";
                if (Smoke.isChecked()) {
                    smokeornot = "Yes";
                } else {
                    smokeornot = "No";
                }



                if (TextUtils.isEmpty(MobileNum) || TextUtils.isEmpty(Namee) || TextUtils.isEmpty(Pax)) {
                    Toast.makeText(MainActivity.this, "One of the fields is empty", Toast.LENGTH_LONG).show();

                } else {
                    LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View viewDialog = inflater.inflate(R.layout.input, null);

                    //Obtain the UI component in the input.xml layout
                    //It needs to be defined as "final", so that it can used in the onClick() method later
                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                    myBuilder.setView(viewDialog); //Set the view of the dialog
                    myBuilder.setTitle("Confirm Your Order");
                    myBuilder.setMessage("New Reservation"
                            + "\n" + "Name: " + Name.getText().toString()
                            + "\n" + "Smoking: " + smokeornot
                            + "\n" + "Size: " + Size.getText().toString()
                            + "\n" + Day.getText().toString()
                            + "\n" + Time.getText().toString());

                    myBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Extract the text entered by the user
                            //Set the text to the TextView
                        }
                    });

                    //Configuer the "cancel" button (Set to a corner)
                    myBuilder.setNeutralButton("Cancel", null);
                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();
                }
            }
            });




        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name.setText("");
                Mobile.setText("");
                Size.setText("");
                Day.setText("");
                Time.setText("");
            }

        });


    }
}


