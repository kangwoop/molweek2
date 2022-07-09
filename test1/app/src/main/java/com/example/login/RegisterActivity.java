package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.test1.LoginRes;
import com.example.test1.MainActivity;
import com.example.test1.R;
import com.example.test1.RetrofitAPIInterface;
import com.example.test1.ValidateRequest;
import com.kakao.sdk.user.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    private EditText join_id, join_password, join_name, join_pwck;
    private Button join_button, check_button, cancel_button;
    private AlertDialog dialog;
    private boolean is_valid = false; ////////
    private final String Tag = "RegisterActivityLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_join );

        Retrofit retrofit = ((MainActivity)MainActivity.context_main).retrofit;
        RetrofitAPIInterface service = ((MainActivity)MainActivity.context_main).service;

        //아이디값 찾아주기
        join_id = findViewById( R.id.join_email );
        join_password = findViewById( R.id.join_password );
        join_name = findViewById( R.id.join_name );
        join_pwck = findViewById(R.id.join_pwck);

        //아이디 중복 체크
        check_button = findViewById(R.id.check_button);
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserId = join_id.getText().toString();
                HashMap<String, Object> param = new HashMap<>();
                param.put("Id", UserId);
                Call<LoginRes> call_post = service.JoinReqFunc("signup",param);
                call_post.enqueue(new Callback<LoginRes>() {
                    @Override
                    public void onResponse(Call<LoginRes> call, retrofit2.Response<LoginRes> response) {
                        if(response.isSuccessful()){
                            String result  = response.body().toString();
                            Log.v(Tag,"result = " + result);
                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                            if(response.body().result.equals("false")){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("중복된 아이디가 있는지 확인하세요.").setNegativeButton("확인", null).create();
                                dialog.show();
                                return;
                            }

                            is_valid = true;
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



        //회원가입 버튼 클릭 시 수행
        join_button = findViewById( R.id.join_button );
        join_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String UserId = join_id.getText().toString();
                final String UserPwd = join_password.getText().toString();
                final String UserName = join_name.getText().toString();
                final String PassCk = join_pwck.getText().toString();

                if(!is_valid){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("아이디 중복 확인을 해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }
                //한 칸이라도 입력 안했을 경우
                if (UserId.equals("") || UserPwd.equals("") || UserName.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                HashMap<String, Object> param = new HashMap<>();
                param.put("Id", UserId);
                param.put("Name", UserName);
                param.put("Pwd", UserPwd);
                //Log.v("UserId,Name,Pwd", UserId);
                Call<LoginRes> call_post = service.JoinReqFunc("endofsignup",param);
                call_post.enqueue(new Callback<LoginRes>() {
                    @Override
                    public void onResponse(Call<LoginRes> call, retrofit2.Response<LoginRes> response) {
                        if(response.isSuccessful()){
                            String result  = response.body().toString();
                            Log.v(Tag,"result = " + result);
                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), String.format("%s님 가입을 환영합니다.", UserName), Toast.LENGTH_SHORT).show();
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





                /*

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            //회원가입 성공시
                            if(UserPwd.equals(PassCk)) {
                                if (success) {

                                    Toast.makeText(getApplicationContext(), String.format("%s님 가입을 환영합니다.", UserName), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);

                                    //회원가입 실패시
                                } else {
                                    Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("비밀번호가 동일하지 않습니다.").setNegativeButton("확인", null).create();
                                dialog.show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                //서버로 Volley를 이용해서 요청
                RegisterRequest registerRequest = new RegisterRequest( UserId, UserPwd, UserName, responseListener);
                RequestQueue queue = Volley.newRequestQueue( RegisterActivity.this );
                queue.add( registerRequest );*/
        }
        });
    }
}