package edu.ecu.cs.pirateplaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(), PiratePlacesListFragment.Callbacks{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pirate_places_list)

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = PiratePlacesListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onPiratePlacesSelected(place: UUID) {
        val fragment = PiratePlacesDetailFragment.newInstance(place)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
