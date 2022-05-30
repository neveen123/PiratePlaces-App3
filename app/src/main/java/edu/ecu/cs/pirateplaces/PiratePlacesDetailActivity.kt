package edu.ecu.cs.pirateplaces

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

private const val EXTRA_PLACE = "edu.ecu.cs.pirateplaces.place"

class PiratePlacesDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pirate_places_list)

        val place = intent.getSerializableExtra(EXTRA_PLACE) as PiratePlace

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = PiratePlacesDetailFragment.newInstance(place)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    companion object {
        fun newIntent(packageContext: Context, place: PiratePlace): Intent {
            return Intent(packageContext, PiratePlacesDetailActivity::class.java).apply {
                putExtra(EXTRA_PLACE, place)
            }
        }
    }
}
