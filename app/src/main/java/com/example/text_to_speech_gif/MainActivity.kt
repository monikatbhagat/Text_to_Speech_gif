package com.example.text_to_speech_gif

import android.content.Context
import android.media.AudioManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity(),TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var buttonSpeak: Button? = null
    private var editText: EditText? = null
            var mContext: Context=this
            val arryOfCategory= arrayOf("Loans", "Fixed Deposits", "Personal Banking")
    var categorySelected:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Glide.with(mContext)
                .asGif()
                .load(R.drawable.namme)
                .into(imageView2)


        textView!!.isEnabled=false
        tts = TextToSpeech(this, this)

       selectSpinner(spCategory,arryOfCategory)
//        if(spCategory!=null)
//        {
//            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arryOfCategory)
//            spCategory.adapter = arrayAdapter
//            spCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                }
//                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                    categorySelected=arryOfCategory[position]
//                    Log.d("ggg","kkk"+categorySelected)
////                    yearsSelected=numberOfyears[position] //also can use
//
//
//
//                }
//            }
//        }



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
                    if(categorySelected=="Loans")
                    {
                        textView!!.text=getString(R.string.minimum_balance)
                        textView2!!.text=getString(R.string.rate_of_interest)
                        textView3!!.text=getString(R.string.services)

                        textView!!.setOnClickListener {
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

        } else {
            Log.e("TTS", "Initilization Failed!")
        }

    }

 /*   private fun speakOut() {
        val text = editText!!.text.toString()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
        }
    }*/


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
        }
    }

    public override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

}