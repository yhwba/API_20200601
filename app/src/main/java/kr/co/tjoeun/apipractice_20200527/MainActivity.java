package kr.co.tjoeun.apipractice_20200527;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjoeun.apipractice_20200527.databinding.ActivityMainBinding;
import kr.co.tjoeun.apipractice_20200527.datas.Topic;
import kr.co.tjoeun.apipractice_20200527.datas.User;
import kr.co.tjoeun.apipractice_20200527.utils.ContextUtil;
import kr.co.tjoeun.apipractice_20200527.utils.ServerUtil;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContextUtil.setLoginUserToken(mContext, "");

                Intent myIntent = new Intent(mContext, LoginActivity.class);
                startActivity(myIntent);

                finish();

            }
        });

    }

    @Override
    public void setValues() {

        ServerUtil.getRequestMainInfo(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                Log.d("메인화면응답", json.toString());


                try {
                    int code = json.getInt("code");

                    if (code == 200) {
                        JSONObject data = json.getJSONObject("data");

                        JSONObject user = data.getJSONObject("user");

                        final User me = User.getUserFromJson(user);

                        JSONObject topic = data.getJSONObject("topic");

                        final Topic thisWeekTopic = Topic.getTopicFromJson(topic);


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.nickNameTxt.setText(me.getNickName());
                                binding.emailTxt.setText(me.getEmail());

                                Glide.with(mContext).load(thisWeekTopic.getImageUrl()).into(binding.topicImg);
                                binding.topicTitleTxt.setText(thisWeekTopic.getTitle());
                            }
                        });


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}
