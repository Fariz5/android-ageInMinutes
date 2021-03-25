package eu.tutorials.ageinminutes2

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
// cabang
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePicker = findViewById(R.id.tanggal) as Button

        datePicker.setOnClickListener { view ->
            clickDatePicker(view)
            Toast.makeText(this, "Pilih tanggal lahir anda", Toast.LENGTH_LONG).show() }
    }

    // function date picker
    fun clickDatePicker(view: View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener
        { view, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selected = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            val tvDate = findViewById(R.id.tvSelectedDate) as TextView
            tvDate.setText(selected)
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val Date = sdf.parse(selected)
            // diubah menjadi mili second
            // 1 menit = 60000 mili second
            // 1 hari = 86400000 mili second
            // 1 tahun = 31,536,000,000 mili second

            // Umur dalam hari
            val dateInYear = Date!!.time / 31536000000
            // ngubah format waktu ke milisecond
            val currentYear  = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentYear2 = currentYear!!.time / 31536000000
            val differenceYear = currentYear2 - dateInYear
            val tvSDInYear = findViewById(R.id.tvSDInYear) as TextView
            tvSDInYear.setText(differenceYear.toString())

            // Umur dalam hari
            val dateInDay = Date!!.time / 86400000
            val currentDay = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDay2 = currentDay!!.time / 86400000
            val differenceDay = currentDay2 - dateInDay
            val tvSelectedDateInDay = findViewById(R.id.tvSelectedDateInDay) as TextView
            tvSelectedDateInDay.setText(differenceDay.toString())


            // Umur dalam menit
            val dateInMinutes = Date!!.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time / 60000
            val differenceDate = currentDateInMinutes - dateInMinutes
            val tvInMinutes = findViewById(R.id.tvSelectedDateInMinutes) as TextView
            tvInMinutes.setText(differenceDate.toString())

        },year, month, day)

        // membuat max tanggal yang dapat dipilih
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }
}