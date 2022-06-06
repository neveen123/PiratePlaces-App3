package edu.ecu.cs.pirateplaces

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


private const val TAG="PiratePlacesListFragment"
class PiratePlacesListFragment: Fragment() {

    /**
     * Required interface for hosting activities
     */
    interface Callbacks {
        fun onPiratePlacesSelected(placeId: UUID)
    }
    private var callbacks: Callbacks? = null

    private lateinit var piratePlacesRecyclerView: RecyclerView
    private var adapter: PlaceAdapter = PlaceAdapter(emptyList())
    private val piratePlacesListViewModel : PiratePlacesListViewModel by lazy {
        ViewModelProviders.of(this).get(PiratePlacesListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pirate_places_list, container, false)

        piratePlacesRecyclerView = view.findViewById(R.id.pirate_places_recycler_view) as RecyclerView
        piratePlacesRecyclerView.layoutManager = LinearLayoutManager(context)
        piratePlacesRecyclerView.adapter = adapter

        return view
    }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        piratePlacesListViewModel.pirateListLiveData.observe(
            viewLifecycleOwner,
           Observer { places ->
                places?.let {
                    Log.i(TAG," ${places.size}")
                    updateUI(places)
                }
            })
    }
    override fun onDetach() {
        super.onDetach()
        callbacks = null}

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_place_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_place -> {
                val place = PiratePlace()
                piratePlacesListViewModel.addPiratePlace(place)
                callbacks?.onPiratePlacesSelected(place.id)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateUI(places: List<PiratePlace>){

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
            callbacks?.onPiratePlacesSelected(place.id)
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