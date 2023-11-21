package com.diabeat.ease.pro.databinding

import android.os.Parcelable
import android.util.Base64
import android.view.View.OnClickListener
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.databinding.BaseObservable
import com.diabeat.ease.pro.R
import com.diabeat.ease.pro.app
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import java.nio.charset.Charset

@Parcelize
data class QA(
    @DrawableRes
    var icon: Int = R.mipmap.ic_launcher_round,
    var title: String = "",
    var content: String = "",
    var from: String = "",
    @ColorRes
    var bg: Int = R.color.bg_qa_item_0
) : BaseObservable(), Parcelable

data class QAEntity(
    val topics:List<QA> = mutableListOf()
)
val bgItemList: List<Int> by lazy {
    mutableListOf<Int>(
        R.color.bg_qa_item_0,
        R.color.bg_qa_item_1,
        R.color.bg_qa_item_2,
        R.color.bg_qa_item_3,
    )
}

val iconItemList: List<Int> by lazy {
    mutableListOf<Int>(
        R.mipmap.ic_qa_item_icon_0,
        R.mipmap.ic_qa_item_icon_1,
        R.mipmap.ic_qa_item_icon_2,
        R.mipmap.ic_qa_item_icon_3,
    )
}

val qaTestList: List<QA> by lazy {
    mutableListOf(
        QA(
            icon = iconItemList[0],
            bg = bgItemList[0],
            title = "The Old Man and the Sea",
            content = "The sun rose thinly from the sea and the old man could see the other boats, low on the water and well in toward the shore, spread out across the current. Then the sun was brighter and the glare came on the water and then, as it rose clear, the flat sea sent it back at his eyes so that it hurt sharply and he rowed without looking into it. He looked down into the water and watched the lines that went straight down into the dark of the water. He kept them straighter than anyone did, so that at each level in the darkness of the stream there would be a bait waiting exactly where he wished it to be for any fish that swam there. Others let them drift with the current and sometimes they were at sixty fathoms when the fishermen thought they were at a hundred.\nBut, he thought, I keep them with precision. Only I have no luck any more. But who knows? Maybe today. Every day is a new day. It is better to be lucky. But I would rather be exact. Then when luck comes you are ready.\nThe sun was two hours higher now and it did not hurt his eyes so much to look into the east. There were only three boats in sight now and they showed very low and far inshore.\nAll my life the early sun has hurt my eyes, he thought. Yet they are still good. In the evening I can look straight into it without getting the blackness. It has more force in the evening too. But in the morning it is painful.\nJust then he saw a man-of-war bird with his long black wings circling in the sky ahead of him. He made a quick drop, slanting down on his back-swept wings, and then circled again.\n\"He's got something,\" the old man said aloud. \"He's not just looking.\"\nHe rowed slowly and steadily toward where the bird was circling. He did not hurry and he kept his lines straight up and down. But he crowded the current a little so that he was still fishing correctly though faster than he would have fished if he was not trying to use the bird.\nThe bird went higher in the air and circled again, his wings motionless. Then he dove suddenly and the old man saw flying fish spurt out of the water and sail desperately over the surface.\n\"Dolphin,\" the old man said aloud. \"Big dolphin.\"\nHe shipped his oars and brought a small line from under the bow. It had a wire leader and a medium-sized hook and he baited it with one of the sardines. He let it go over the side and then made it fast to a ring bolt in the stern. Then he baited another line and left it coiled in the shade of the bow. He went back to rowing and to watching the long-winged black bird who was working, now, low over the water."
        ),
        QA(icon = iconItemList[1], bg = bgItemList[1]),
        QA(icon = iconItemList[2], bg = bgItemList[2]),
        QA(icon = iconItemList[3], bg = bgItemList[3]),
        QA(icon = iconItemList[0], bg = bgItemList[0]),
        QA(icon = iconItemList[1], bg = bgItemList[1]),
        QA(icon = iconItemList[2], bg = bgItemList[2]),
        QA(icon = iconItemList[3], bg = bgItemList[3]),
        QA(icon = iconItemList[0], bg = bgItemList[0]),
        QA(icon = iconItemList[1], bg = bgItemList[1]),
        QA(icon = iconItemList[2], bg = bgItemList[2]),
        QA(icon = iconItemList[3], bg = bgItemList[3])
    )
}

val qaList: List<QA> = Gson().fromJson(Base64.decode(app.assets.open("qa.json").let {
    val buffer = ByteArray(it.available())
    it.read(buffer)
    it.close()
    String(buffer, Charset.defaultCharset())
}, Base64.DEFAULT).decodeToString(), QAEntity::class.java).topics.apply {
    for (i in indices){
        this[i].icon = iconItemList[i % 4]
        this[i].bg = bgItemList[i % 4]
    }
}
