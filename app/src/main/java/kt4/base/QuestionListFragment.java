package kt4.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import kt4.base.model.Question;
import kt4.base.model.QuestionsResponseWrapper;

/**
 * A placeholder fragment containing a simple view.
 */
public class QuestionListFragment extends Fragment {

    private static final String TAG = "LOGTAG";

    private final static String REQUEST_URL = "https://api.stackexchange.com/2.2/questions?" +
            "order=desc&sort=activity&tagged=android&site=stackoverflow";

    public QuestionListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        requestQuestions();

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    private void requestQuestions() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(REQUEST_URL).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, e + "");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response == null)
                    return;

                Log.i(TAG, "Response code: " + response.code());

                QuestionsResponseWrapper parsedResponse = parse(response);

                attachToListview(parsedResponse);

            }
        });
    }

    private void attachToListview(QuestionsResponseWrapper questionsResponseWrapper) {
        final ArrayAdapter<Question> adapter =
                new ArrayAdapter<>(getActivity().getBaseContext(),
                        android.R.layout.simple_list_item_1,
                        questionsResponseWrapper.items);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ListView listView = (ListView) getActivity().findViewById(R.id.listView);
                listView.setAdapter(adapter);
            }
        });
    }

    private QuestionsResponseWrapper parse(Response response) throws IOException {
        String inputJson = response.body().string();
        return new Gson().fromJson(inputJson,
                QuestionsResponseWrapper.class);
    }

}
