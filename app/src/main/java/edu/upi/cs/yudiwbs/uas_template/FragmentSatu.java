package edu.upi.cs.yudiwbs.uas_template;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import edu.upi.cs.yudiwbs.uas_template.databinding.FragmentSatuBinding;


public class FragmentSatu extends Fragment {

    private FragmentSatuBinding binding;
    private ViewModelFragmentSatu model;
    private TextView tvPertanyaan, tvHasilJawaban;

    public FragmentSatu() {
        // Required empty public constructor
    }

    public static FragmentSatu newInstance(String param1, String param2) {
        FragmentSatu fragment = new FragmentSatu();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView (R.layout.fragment_satu);
//
//        tvPertanyaan = getView().findViewById(R.id.tvPertanyaan);
//        tvHasilJawaban = getView().findViewById(R.id.tvHasil);
//        model = new ViewModelProvider(this).get(ViewModelFragmentSatu.class);

//        final Observer<String> pertanyaanObs = new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable final String pertanyaan) {
//                tvPertanyaan.setText(pertanyaan);
//            }
//        };
//        model.data.observe(this, pertanyaanObs);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSatuBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.loadPertanyaanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvKeterangan.setText("Tunggu sebentar, loading data harga bitcoin (USD)");
                //https://api.coindesk.com/v1/bpi/currentprice.json
                Log.d("debugyudi","onclick");
                APIJoke.get("bpi/currentprice.json", null, new JsonHttpResponseHandler() {
                    @Override
                    //hati2 success jsonobjek atau jsonarray
                    public void onSuccess(int statusCode,
                                          cz.msebera.android.httpclient.Header[] headers,
                                          org.json.JSONObject response) {
                        Log.d("debugyudi","onSuccess jsonobjek");

                        /* hasil jsonn
                        {"time":{"updated":"Dec 19, 2022 09:53:00 UTC","updatedISO":"2022-12-19T09:53:00+00:00",
                                "updateduk":"Dec 19, 2022 at 09:53 GMT"},

                        "disclaimer":"This data was produced from the CoinDesk Bitcoin Price Index (USD).
                              Non-USD currency data converted using hourly conversion rate from openexchangerates.org",
                         "chartName":"Bitcoin",
                         "bpi":{"USD":{"code":"USD","symbol":"&#36;","rate":"16,730.3955",
                                    "description":"United States Dollar","rate_float":16730.3955},
                                "GBP":{"code":"GBP","symbol":"&pound;","rate":"13,979.7846",
                                  "description":"British Pound Sterling","rate_float":13979.7846},      "EUR":{"code":"EUR","symbol":"&euro;","rate":"16,297.8478","description":"Euro","rate_float":16297.8478}}}
                         */

                        //ambil USD rate
                        String rate="";
                        try {
                            JSONObject bpi = response.getJSONObject("bpi");
                            JSONObject usd = bpi.getJSONObject("USD");
                            rate = (String) usd.get("rate");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("debugyudi", "msg error" +":" +e.getMessage());
                        }
                        Log.d("debugyudi", "rate" +":" +rate);
                        binding.tvKeterangan.setText(rate);
                    }

                    public void onSuccess(int statusCode,
                                          cz.msebera.android.httpclient.Header[] headers,
                                          org.json.JSONArray response) {

                        Log.d("debugyudi","onSuccess jsonarray");

                    }

                    @Override
                    public  void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String err, Throwable throwable)  {
                        Log.e("debugyudi", "error " + ":" + statusCode +":"+ err);
                    }
                });

            }
        });

        return view;
    }

//    public void onClick(View v){
//        APIJoke.get("setup/", null, new JsonHttpResponseHandler(){
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
//                String joke = null;
//                try{
//                    joke = (String) response.get("setup");
//                }catch(JSONException e){
//                    e.printStackTrace();
//                }
//                Log.d("debug", joke);
//            }
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray jArr){
//                Log.d("debug", "onSuccess JSONArray");
//            }
//            @Override
//            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[]  headers, String err, Throwable throwable){
//                Log.e("debug", "error");
//            }
//        });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    

//    public int getContentView() {
//        return contentView;
//    }
}



