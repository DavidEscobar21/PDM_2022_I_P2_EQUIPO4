package hn.edu.ujcv.pdm_2022_i_p2_equipo4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hn.edu.ujcv.pdm_2022_i_p2_equipo4.ui.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().
            replace(R.id.fraMainActivity, MainFragment.newInstance()).commitNow()
        }
    }

}