package com.tms.android.sxtask

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.*
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.motion.widget.TransitionBuilder
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import coil.load
import coil.size.Precision
import coil.size.Scale
import java.lang.Exception
import java.nio.file.Files.move
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class SecondScreenActivity : Fragment() {

    private lateinit var image: ImageView
    private lateinit var mainTxt: TextView
    private lateinit var subTxt: TextView
    private lateinit var link: String

    private lateinit var trNOImg: String
    private lateinit var trNOMain: String
    private lateinit var trNOSub: String

    private lateinit var closeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page_second, container, false)

        image = view.findViewById(R.id.imgFrame2) as ImageView
        mainTxt = view.findViewById(R.id.mainText) as TextView
        subTxt = view.findViewById(R.id.subText) as TextView
        closeButton = view.findViewById(R.id.closeFrame) as Button

        loadData()

        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transition = TransitionInflater.from(this.context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
        postponeEnterTransition(250, TimeUnit.MILLISECONDS)

        closeButton.setOnClickListener{
            navigateBack()
            }
    }

    private fun loadData() {
        arguments?.let { bundle ->
            mainTxt.text = bundle.getString("mainTextSend")
            subTxt.text = bundle.getString("subTextSend")
            link = bundle.getString("imgLinkSend")!!
            trNOImg = bundle.getString("trNImg", "")
            trNOMain = bundle.getString("trNMainTxt", "")
            trNOSub = bundle.getString("trNSubTxt", "")
        }
        image.load(link)
    }

    private fun navigateBack(){

        val extras = FragmentNavigatorExtras(
            image to trNOImg,
            mainTxt to trNOMain,
            subTxt to trNOSub)

        Navigation.findNavController(requireView()).navigate(R.id.action_secondScreenActivity2_to_listFragment2, null, null, extras)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            navigateBack()
        }

        return true
    }
}
