package com.tms.android.sxtask

import androidx.lifecycle.ViewModel
import java.util.*

class ListView: ViewModel() {

    val itemList = mutableListOf<Item>()

    val link1 = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Cat_November_2010-1a.jpg/1200px-Cat_November_2010-1a.jpg"
    val link2 = "https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png"
    val link3 = "https://media.npr.org/assets/img/2021/08/11/gettyimages-1279899488_wide-e28def62c2d01ee6368d9aa312c68998415b5e72-s1100-c50.jpg"
    val link4 = "https://www.thesprucepets.com/thmb/QDw4vt7XXQejL2IRztKeRLow6hA=/2776x1561/smart/filters:no_upscale()/cat-talk-eyes-553942-hero-df606397b6ff47b19f3ab98589c3e2ce.jpg"
    val link5 = "https://i.natgeofe.com/n/3861de2a-04e6-45fd-aec8-02e7809f9d4e/02-cat-training-NationalGeographic_1484324_3x4.jpg"

    init {
        for (index in 1..1000) {
            val item = Item()
            item.mainText = "Item №$index"
            item.subText = "Some txt. Some txt. Some txt. Some txt. Some txt. Some txt. Some txt. Some txt. Some txt. Some txt."
            when {
                index % 3  == 0 -> item.imgLink1 = link1
                index % 5  == 0 -> item.imgLink1 = link2
                index % 7  == 0 -> item.imgLink1 = link3
                index % 11 == 0 -> item.imgLink1 = link4
                else -> item.imgLink1 = link5
            }
            item.UUID = UUID.randomUUID()
            item.imgLink2 = item.imgLink1
            itemList += item
        }
    }
}