package edu.ecu.cs.pirateplaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PiratePlacesListFragment: Fragment() {
    private lateinit var piratePlacesRecyclerView: RecyclerView
    private var adapter: PlaceAdapter? = null

    private val piratePlacesListViewModel : PiratePlacesListViewModel by lazy {
        ViewModelProviders.of(this).get(PiratePlacesListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pirate_places_list, container, false)

        piratePlacesRecyclerView = view.findViewById(R.id.pirate_places_recycler_view) as RecyclerView
        piratePlacesRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    private fun updateUI() {
        val places = piratePlacesListViewModel.piratePlaceList
        adapter = PlaceAdapter(places)
        piratePlacesRecyclerView.adapter = adapter
    }

    companion object {
        fun newInstance() : PiratePlacesListFragment {
            return PiratePlacesListFragment()
        }
    }

    private inner class PlaceHolder(view: View):
            RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var place: PiratePlace
        private var nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        private var visitedWithTextView: TextView = itemView.findViewById(R.id.visited_with_text_view)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(place: PiratePlace) {
            this.place = place
            nameTextView.text = place.name
            visitedWithTextView.text = place.visitedWith
        }

        override fun onClick(v: View) {
            val intent = PiratePlacesDetailActivity.newIntent(requireContext(), place)
            startActivity(intent)
        }
    }

    private inner class PlaceAdapter(var places:List<PiratePlace>):
            RecyclerView.Adapter<PlaceHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
            val view = layoutInflater.inflate(R.layout.list_item_place, parent, false)
            return PlaceHolder(view)
        }

        override fun getItemCount() = places.size

        override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
            holder.bind(places[position])
        }
    }
}