package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.test1.HomeActivity;
import com.example.test1.LoginRes;
import com.example.test1.MainActivity;
import com.example.test1.R;
import com.example.test1.RetrofitAPIInterface;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private EditText login_email, login_password;
    private Button login_button, join_button;

    private static final String Tag = "LoginActivity";

    private View KakaologinButton;
    //private TextView nickName;
    //private ImageView profileImage;

    private Retrofit retrofit;
    private RetrofitAPIInterface service;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Context mContext;
        mContext = getApplicationContext();

        Retrofit retrofit = ((MainActivity)MainActivity.context_main).retrofit;
        RetrofitAPIInterface service = ((MainActivity)MainActivity.context_main).service;
//        String token = ((MainActivity)MainActivity.context_main).token;



        KakaologinButton = findViewById(R.id.login);
        //nickName = findViewById(R.id.nickname);

        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>(){
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(oAuthToken != null){
                }
                if(throwable != null){
                }
                updateKakaoLoginUi(retrofit, service);
                return null;
            }
        };

        KakaologinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(mContext)){
                    UserApiClient.getInstance().loginWithKakaoTalk(mContext, callback);
                }else{
                    UserApiClient.getInstance().loginWithKakaoAccount(mContext, callback);
                }
            }
        });


        /* Default Login */
        login_email = findViewById(R.id.login_email);
        login_password = findViewById( R.id.login_password );
        join_button = findViewById( R.id.join_button );
        join_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginActivity.this, RegisterActivity.class);
                startActivity( intent );
            }
        });
        login_button = findViewById( R.id.login_button );
        login_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserId = login_email.getText().toString();
                String UserPwd = login_password.getText().toString();

                HashMap<String, Object> param = new HashMap<>();
                param.put("Id", UserId);
                param.put("Pwd", UserPwd);
                param.put("type", "default");

                Call<LoginRes> call_post = service.LoginReqFunc("login",param);
                call_post.enqueue(new Callback<LoginRes>() {
                    @Override
                    public void onResponse(Call<LoginRes> call, retrofit2.Response<LoginRes> response) {
                        if(response.isSuccessful()){
                            String result  = response.body().toString();
                            Log.v(Tag,"result = " + result);
                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                            if(response.body().token.equals(" ")){
                                Toast.makeText(getApplicationContext(),"Invalid user",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else if(response.body().token.equals("1")){
                                Toast.makeText(getApplicationContext(),"Wrong password",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else{
                                token = response.body().token;
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                        }
                        else{
                            Log.v(Tag,"error = " + String.valueOf(response.code()));
                            Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginRes> call, Throwable t) {
                        Log.v(Tag,"Fail");
                        Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void updateKakaoLoginUi(Retrofit retrofit, RetrofitAPIInterface service){
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if(user!=null){
                    Log.i(Tag, "invoke: id=" + user.getId());
                    Log.i(Tag, "invoke: email=" + user.getKakaoAccount().getEmail());
                    Log.i(Tag, "invoke: nickname=" + user.getKakaoAccount().getProfile().getNickname());
                    Log.i(Tag, "invoke: gender=" + user.getKakaoAccount().getGender());
                    Log.i(Tag, "invoke: age=" + user.getKakaoAccount().getAgeRange());

                    HashMap<String, Object> param = new HashMap<>();
                    param.put("Id", user.getId());
                    param.put("Pwd", "");
                    param.put("type", "kakao");

                    Call<LoginRes> call_post = service.LoginReqFunc("login",param);
                    call_post.enqueue(new Callback<LoginRes>() {
                        @Override
                        public void onResponse(Call<LoginRes> call, retrofit2.Response<LoginRes> response) {
                            if(response.isSuccessful()){
                                String result  = response.body().toString();
                                Log.v(Tag,"result = " + result);
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                                if(response.body().token.equals(" ")){
                                    Toast.makeText(getApplicationContext(),"Invalid user",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                else if(response.body().token.equals("1")){
                                    Toast.makeText(getApplicationContext(),"Wrong password",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                else{
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                }
                            }
                            else{
                                Log.v(Tag,"error = " + String.valueOf(response.code()));
                                Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(i);
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginRes> call, Throwable t) {
                            Log.v(Tag,"Fail");
                            Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
                        }

                    });

                }
                return null;}
            });

        }
}
