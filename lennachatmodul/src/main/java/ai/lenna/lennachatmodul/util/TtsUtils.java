package ai.lenna.lennachatmodul.util;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.speech.tts.Voice;
import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class TtsUtils extends UtteranceProgressListener implements TextToSpeech.OnInitListener {

    private String male_voice = "id-id-x-dfz#male_2-local";
    private String female_voice = "id-id-x-dfz#female-local";
    private Voice v= null;

    private final String TAG = TtsUtils.class.getSimpleName();

    private TextToSpeech textToSpeech;
    private Locale locale;

    public TtsUtils(Context context, Locale locale, String ttsEngine) {
        this.locale = locale;
        textToSpeech = new TextToSpeech(context, this, ttsEngine);
        textToSpeech.setOnUtteranceProgressListener(this);
        textToSpeech.setPitch(Float.parseFloat(Constant.TTS_PITCH));
        textToSpeech.setSpeechRate(Float.parseFloat(Constant.SPEECH_RATE));
        Log.d(TAG,"tts.getDefaultEngine() : "+getDefaultEngineTts());
    }


    public void speak(String text) {
        if(textToSpeech != null) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Set<String> a=new HashSet<>();
                a.add("female");//here you can give male if you want to select mail voice.
                v = new Voice(female_voice,new Locale("id", "ID"),500,400,true,a);
                textToSpeech.setVoice(v);
                String myUtteranceID = "myUtteranceID";
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, myUtteranceID);
            }
            else {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "myUtteranceID");
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, hashMap);
            }
        }
    }

    public List<TextToSpeech.EngineInfo> getDefaultEngineTtsAll(){
        return textToSpeech.getEngines();
    }

    public String getDefaultEngineTts(){
        return textToSpeech.getDefaultEngine();
    }

    public void stop() {
        textToSpeech.stop();
    }

    public void shutdown() {
        textToSpeech.shutdown();
    }

    public boolean isSpeaking() {
        return textToSpeech.isSpeaking();
    }

    @Override
    public void onStart(String utteranceId) {
        Log.d(TAG, "onStart / utteranceID = " + utteranceId);
    }

    @Override
    public void onDone(String utteranceId) {
        Log.d(TAG, "onDone / utteranceID = " + utteranceId);
    }

    @Override
    public void onError(String utteranceId) {
        Log.d(TAG, "onError / utteranceID = " + utteranceId);
    }

    @Override
    public void onInit(int status) {
        Log.e("STATUS_TTS", String.valueOf(status));
        if(status != TextToSpeech.ERROR)
            textToSpeech.setLanguage(locale);
    }
}