package com.tms.android.sxtask

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import java.util.concurrent.TimeUnit

class ListFragment: Fragment() {

    private val listViewModel: ListView by lazy {
        ViewModelProviders.of(this).get(ListView::class.java)
    }

    private lateinit var listRecyclerView: RecyclerView
    private var adapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recycler_list, container, false)

        listRecyclerView = view.findViewById(R.id.list_of_items) as RecyclerView
        listRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    private inner class ListHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener{

        private lateinit var item: Item
        private lateinit var link: String
        private val mainText: TextView = itemView.findViewById(R.id.text_single_main)
        private val subText: TextView = itemView.findViewById(R.id.text_single_sub)
        private val img1: ImageView = itemView.findViewById(R.id.img_single_1)
        private val img2: ImageView = itemView.findViewById(R.id.img_single_2)

        fun bind(item: Item){
            this.item = item
            mainText.text = item.mainText
            subText.text = item.subText
            link = item.imgLink1

            img1.load(link){
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
                transformations(CircleCropTransformation())
            }

            img2.load(link){
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
                transformations(CircleCropTransformation())
            }
        }

        init {
            itemView.setOnClickListener(this)
        }

        @SuppressLint("ResourceType")
        override fun onClick(view: View) {

            navigateData(view)

            Toast.makeText(context, mainText.text, Toast.LENGTH_SHORT).show()
        }

        fun navigateData(view: View){
            img1.transitionName = item.UUID.toString() + "img"
            mainText.transitionName = item.UUID.toString() + "mainText"
            subText.transitionName = item.UUID.toString() + "subText"

            val extras = FragmentNavigatorExtras(
                img1 to "big_image_second_screen",
                mainText to "main_text_second_screen",
                subText to "sub_text_second_screen")

            val mainTxtSend = mainText.text.trim()
            val subTextSend = subText.text.trim()
            val imgLinkSend = link.trim()
            val trNImg = img1.transitionName
            val trNMainTxt = mainText.transitionName
            val trNSubTxt = subText.transitionName

            val bundle = bundleOf(
                "mainTextSend" to mainTxtSend,
                "subTextSend" to subTextSend,
                "imgLinkSend" to imgLinkSend,
                "trNImg" to trNImg,
                "trNMainTxt" to trNMainTxt,
                "trNSubTxt" to trNSubTxt)

            Navigation.findNavController(view).navigate(R.id.action_listFragment2_to_secondScreenActivity2, bundle, null, extras)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transition = TransitionInflater.from(this.context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
        postponeEnterTransition(250, TimeUnit.MILLISECONDS)
    }

    private inner class ListAdapter(var list: List<Item>): RecyclerView.Adapter<ListHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {

            val view = layoutInflater.inflate(R.layout.fragment_page_start, parent, false)
            return ListHolder(view)
        }

        override fun onBindViewHolder(holder: ListHolder, position: Int) {

            val item = list[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int = list.size
    }

    companion object{
        fun newInstance(): ListFragment{
            return ListFragment()
        }
    }

    private fun updateUI(){
        val items = listViewModel.itemList
        adapter = ListAdapter(items)
        listRecyclerView.adapter = adapter
    }
}