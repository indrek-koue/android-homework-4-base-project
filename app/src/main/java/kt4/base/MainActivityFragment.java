package kt4.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;

import kt4.base.model.Question;
import kt4.base.model.QuestionsResponseWrapper;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = "LOGTAG";

    private final static String REQUEST_URL = "https://api.stackexchange.com/2.2/questions?order=desc&sort=activity&tagged=android&site=stackoverflow";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(REQUEST_URL).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, e + "");
            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {

                if (response == null) { return; }
                Log.i(TAG, "Response code: " + response.code());

                String inputJson = response.body().string();

                Log.i(TAG, inputJson);

                QuestionsResponseWrapper QuestionsResponseWrapper = new Gson().fromJson(inputJson,
                        QuestionsResponseWrapper.class);

                for (Question q : QuestionsResponseWrapper.items)
                    Log.d(TAG, q.title);

            }
        });

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}
