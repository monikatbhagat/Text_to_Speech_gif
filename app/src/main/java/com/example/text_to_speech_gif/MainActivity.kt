package com.example.text_to_speech_gif

import android.app.Activity
import android.app.Application
import android.content.Context
import android.media.AudioManager
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.speech.tts.TextToSpeech
import android.support.annotation.Nullable
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.HashMap
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestOptions
import com.cunoraz.gifview.library.GifView
import android.system.Os.shutdown
import android.speech.tts.UtteranceProgressListener



class MainActivity() : AppCompatActivity(),TextToSpeech.OnInitListener{

    private var tts: TextToSpeech? = null
    private var buttonSpeak: Button? = null
    private var editText: EditText? = null
    var mContext: Context=this
    val arryOfCategory= arrayOf("Loans", "Fixed Deposits", "Personal Banking")
    var categorySelected:String?=null

    private var gifView: GifView? = null

    private var ansCompleted:Boolean?=false

     var speakerbox: Speakerbox? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView!!.isEnabled = false
        tts = TextToSpeech(this, this)
        gifView=this.gif1

        gifView!!.pause()

        speakerbox = Speakerbox(application)
        speakerbox!!.setActivity(mContext as Activity?)


        selectSpinner(spCategory, arryOfCategory)
    }




    private fun selectSpinner(sSpinner: Spinner,arraofItem:Array<String>)
    {
        if(sSpinner!=null)
        {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arraofItem)
            sSpinner.adapter = arrayAdapter
            sSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    categorySelected=arraofItem.get(position)
//                    yearsSelected=numberOfyears[position] //also can use
                    setText(categorySelected!!)

                }

            }
        }


    }


    fun setText(categorySelected:String)
    {
        if(categorySelected=="Loans")
        {
            textView!!.text=getString(R.string.minimum_balance)
            textView2!!.text=getString(R.string.rate_of_interest)
            textView3!!.text=getString(R.string.services)

            textView!!.setOnClickListener {
                val textToSpeech=getString(R.string.minimum_balance_ans)
                val onStart = Runnable {
                    if (gifView!!.isPaused){
                        gifView!!.play()
                    }
                }
                val onDone = Runnable {
                    if (gifView!!.isPlaying){
                        gifView!!.pause()
                    }
                }
                val onError = Runnable {
                    Toast.makeText(mContext,"Error..",Toast.LENGTH_LONG).show()
                }
                speakerbox!!.play(textToSpeech, onStart, onDone, null)
            }

            textView2!!.setOnClickListener {


                val textToSpeech=getString(R.string.rate_of_interest_ans)
                val onStart = Runnable {
                    if (gifView!!.isPaused){
                        gifView!!.play()
                    }
                }
                val onDone = Runnable {
                    if (gifView!!.isPlaying){
                        gifView!!.pause()
                    }
                }
                val onError = Runnable {
                    Toast.makeText(mContext,"Error..",Toast.LENGTH_LONG).show()
                }
                speakerbox!!.play(textToSpeech, onStart, onDone, null)

            }

            textView3!!.setOnClickListener {
                val textToSpeech=getString(R.string.services_ans)
                val onStart = Runnable {
                    if (gifView!!.isPaused){
                        gifView!!.play()
                    }
                }
                val onDone = Runnable {
                    if (gifView!!.isPlaying){
                        gifView!!.pause()
                    }
                }
                val onError = Runnable {
                    Toast.makeText(mContext,"Error..",Toast.LENGTH_LONG).show()
                }
                speakerbox!!.play(textToSpeech, onStart, onDone, null)

            }
            Log.d("tag","6")

        }else if(categorySelected=="Fixed Deposits")
        {
            textView!!.text=getString(R.string.loan_eligibility)
            textView2!!.text=getString(R.string.maximum_home_loan)
            textView3!!.text=getString(R.string.security)

            textView!!.setOnClickListener {
                val textToSpeech=getString(R.string.loan_eligibility_ans)
                val onStart = Runnable {
                    if (gifView!!.isPaused){
                        gifView!!.play()
                    }
                }
                val onDone = Runnable {
                    if (gifView!!.isPlaying){
                        gifView!!.pause()
                    }
                }
                val onError = Runnable {
                    Toast.makeText(mContext,"Error..",Toast.LENGTH_LONG).show()
                }
                speakerbox!!.play(textToSpeech, onStart, onDone, null)

            }
            textView2!!.setOnClickListener {
                val textToSpeech=getString(R.string.maximum_home_loan_ans)
                val onStart = Runnable {
                    if (gifView!!.isPaused){
                        gifView!!.play()
                    }
                }
                val onDone = Runnable {
                    if (gifView!!.isPlaying){
                        gifView!!.pause()
                    }
                }
                val onError = Runnable {
                    Toast.makeText(mContext,"Error..",Toast.LENGTH_LONG).show()
                }
                speakerbox!!.play(textToSpeech, onStart, onDone, null)





            }
            textView3!!.setOnClickListener {
                val textToSpeech=getString(R.string.security_ans)
                val onStart = Runnable {
                    if (gifView!!.isPaused){
                        gifView!!.play()
                    }
                }
                val onDone = Runnable {
                    if (gifView!!.isPlaying){
                        gifView!!.pause()
                    }
                }
                val onError = Runnable {
                    Toast.makeText(mContext,"Error..",Toast.LENGTH_LONG).show()
                }
                speakerbox!!.play(textToSpeech, onStart, onDone, null)

            }

        }else if(categorySelected=="Personal Banking")
        {
            textView!!.text=getString(R.string.interest)
            textView2!!.text=getString(R.string.minimum_and_maximum)
            textView3!!.text=getString(R.string.blanck)
            textView!!.setOnClickListener {
                val textToSpeech=getString(R.string.interest_ans)
                val onStart = Runnable {
                    if (gifView!!.isPaused){
                        gifView!!.play()
                    }
                }
                val onDone = Runnable {
                    if (gifView!!.isPlaying){
                        gifView!!.pause()
                    }
                }
                val onError = Runnable {
                    Toast.makeText(mContext,"Error..",Toast.LENGTH_LONG).show()
                }
                speakerbox!!.play(textToSpeech, onStart, onDone, null)

            }
            textView2!!.setOnClickListener {
                val textToSpeech=getString(R.string.minimum_and_maximum_ans)
                val onStart = Runnable {
                    if (gifView!!.isPaused){
                        gifView!!.play()
                    }
                }
                val onDone = Runnable {
                    if (gifView!!.isPlaying){
                        gifView!!.pause()
                    }
                }
                val onError = Runnable {
                    Toast.makeText(mContext,"Error..",Toast.LENGTH_LONG).show()
                }
                speakerbox!!.play(textToSpeech, onStart, onDone, null)

            }

        }
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            } else {
                textView!!.isEnabled = true
                textView2!!.isEnabled = true
                textView3!!.isEnabled = true
            }
//
//            tts!!.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
//                override fun onDone(utteranceId: String) {
//                    // Log.d("MainActivity", "TTS finished");
//                    Toast.makeText(mContext,"nana",Toast.LENGTH_LONG).show()
//
//
//
//                }
//
//                override fun onError(utteranceId: String) {}
//
//                override fun onStart(utteranceId: String) {
//                    Toast.makeText(mContext,"start",Toast.LENGTH_LONG).show()
//
//                }
//            })

        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

    private fun speakQues(text:String)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
        }
        else{
            val hash=HashMap<String,String>()
            hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
                    AudioManager.STREAM_NOTIFICATION.toString())
            tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,hash)
            Toast.makeText(mContext,"dfgfd11",Toast.LENGTH_LONG).show()
        }
    }

    public override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
            Toast.makeText(mContext,"dfgfd",Toast.LENGTH_LONG).show()
        }
        Toast.makeText(mContext,"fd",Toast.LENGTH_LONG).show()
        super.onDestroy()
    }
}

private fun clearStatusText(speakStatus: TextView) {
    Handler().postDelayed({ speakStatus.text = "" }, 1000)
}













/*
package com.example.text_to_speech_gif

import android.content.Context
import android.media.AudioManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.speech.tts.TextToSpeech
import android.support.annotation.Nullable
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.HashMap
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestOptions
import com.cunoraz.gifview.library.GifView
import android.system.Os.shutdown
import android.speech.tts.UtteranceProgressListener






class MainActivity() : AppCompatActivity(),TextToSpeech.OnInitListener{

    private var tts: TextToSpeech? = null
    private var buttonSpeak: Button? = null
    private var editText: EditText? = null
            var mContext: Context=this
            val arryOfCategory= arrayOf("Loans", "Fixed Deposits", "Personal Banking")
       var categorySelected:String?=null

    private var gifView: GifView? = null

    private var ansCompleted:Boolean?=false

    override fun recreate() {
        super.recreate()
        Log.d("tag","ccc")
    }

    override fun onResume() {
        super.onResume()
        Log.d("tag","rrr")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView!!.isEnabled = false
        tts = TextToSpeech(this, this)
        gifView=this.gif1

        gifView!!.pause()


        selectSpinner(spCategory, arryOfCategory)
        Log.d("tag","9")
    }




    private fun selectSpinner(sSpinner: Spinner,arraofItem:Array<String>)
    {
        if(sSpinner!=null)
        {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arraofItem)
            sSpinner.adapter = arrayAdapter
            sSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    categorySelected=arraofItem.get(position)
//                    yearsSelected=numberOfyears[position] //also can use

                 setText(categorySelected!!)

                }

            }
        }

        Log.d("tag","8")

    }


   fun setText(categorySelected:String)
    {
        if(categorySelected=="Loans")
        {
            textView!!.text=getString(R.string.minimum_balance)
            textView2!!.text=getString(R.string.rate_of_interest)
            textView3!!.text=getString(R.string.services)

            textView!!.setOnClickListener {

                if (gifView!!.isPaused){
                    gifView!!.play()
                }

                val textToSpeech=getString(R.string.minimum_balance_ans)
                speakQues(textToSpeech)

            }

            textView2!!.setOnClickListener {


                val textToSpeech=getString(R.string.rate_of_interest_ans)
                speakQues(textToSpeech)

            }

            textView3!!.setOnClickListener {
                val textToSpeech=getString(R.string.services_ans)
                speakQues(textToSpeech)

            }
            Log.d("tag","6")

        }else if(categorySelected=="Fixed Deposits")
        {
            textView!!.text=getString(R.string.loan_eligibility)
            textView2!!.text=getString(R.string.maximum_home_loan)
            textView3!!.text=getString(R.string.security)

            textView!!.setOnClickListener {
                val textToSpeech=getString(R.string.loan_eligibility_ans)
                speakQues(textToSpeech)



            }
            textView2!!.setOnClickListener {
                val textToSpeech=getString(R.string.maximum_home_loan_ans)
                speakQues(textToSpeech)

            }
            textView3!!.setOnClickListener {
                val textToSpeech=getString(R.string.security_ans)
                speakQues(textToSpeech)

            }

        }else if(categorySelected=="Personal Banking")
        {
            textView!!.text=getString(R.string.interest)
            textView2!!.text=getString(R.string.minimum_and_maximum)
            textView3!!.text=getString(R.string.blanck)
            textView!!.setOnClickListener {
                val textToSpeech=getString(R.string.interest_ans)
                speakQues(textToSpeech)

            }
            textView2!!.setOnClickListener {
                val textToSpeech=getString(R.string.minimum_and_maximum_ans)
                speakQues(textToSpeech)

            }

        }
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            } else {
                textView!!.isEnabled = true
                textView2!!.isEnabled = true
                textView3!!.isEnabled = true
            }
//
//            tts!!.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
//                override fun onDone(utteranceId: String) {
//                    // Log.d("MainActivity", "TTS finished");
//                    Toast.makeText(mContext,"nana",Toast.LENGTH_LONG).show()
//
//
//
//                }
//
//                override fun onError(utteranceId: String) {}
//
//                override fun onStart(utteranceId: String) {
//                    Toast.makeText(mContext,"start",Toast.LENGTH_LONG).show()
//
//                }
//            })

        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }



    private fun speakQues(text:String)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
        }
        else{
            val hash=HashMap<String,String>()
            hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
                    AudioManager.STREAM_NOTIFICATION.toString())
            tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,hash)
            Toast.makeText(mContext,"dfgfd11",Toast.LENGTH_LONG).show()
        }
    }

    public override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
            Toast.makeText(mContext,"dfgfd",Toast.LENGTH_LONG).show()
        }
        Toast.makeText(mContext,"fd",Toast.LENGTH_LONG).show()
        super.onDestroy()
    }
}*/
