package com.example.bt_lon.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bt_lon.R;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.category.Category;
import com.example.bt_lon.model.forgotPassword.ForgotPassword;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.question.Question;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DAO.CartDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.UserDAO;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView imageBack = findViewById(R.id.imageBack);
        Button button5 = findViewById(R.id.button5);
        Button button = findViewById(R.id.button);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDAO userDAO = new UserDAO(LoginActivity.this);

                User user = userDAO.getUser("buixuanhien", "123456");
                if (user != null) {
                    finish();
                    RepositoryUser.createNewAccount(user);
                } else {
                    Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu.", Toast.LENGTH_LONG).show();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


//    Bitmap profileImage = BitmapFactory.decodeResource(getResources(), R.drawable.cargo);
//    User user = new User(1, "Bui Xuan Hien", "buixuanhien", "12345", true, new Date(), "Ha Noi", "0763433779", profileImage);
//                databaseConnector.storeUser(user);
//
//    List<Question> questionList = databaseConnector.getAllQuestions();
//                for (int i = 0; i < questionList.size(); i++) {
//        Log.d("questionList", "id: " + questionList.get(i).getQuestion_id() + "\tcâu hỏi: " + questionList.get(i).getQuestion());
//
//        }


//

//                List<Question> questionList = databaseConnector.getAllQuestions();
//                for (int i = 0; i < questionList.size(); i++) {
//                    Log.d("questionList", "id: " + questionList.get(i).getQuestion_id() + "\tcâu hỏi: " + questionList.get(i).getQuestion());
//
//                }
//                Question question = new Question(11, "hioe");
//                databaseConnector.deleteQuestion(question);

//    Question question1 = new Question(1, "1");
//    Question question2 = new Question(2, "2");
//    Question question3 = new Question(3, "3");
//    ForgotPassword forgotPassword1 = new ForgotPassword(1, user, question1, "15/03/2003");
//    ForgotPassword forgotPassword2 = new ForgotPassword(2, user, question2, "cho");
//    ForgotPassword forgotPassword3 = new ForgotPassword(3, user, question3, "ngu");
//                databaseConnector.storeForgotPassword(forgotPassword1);
//                        databaseConnector.storeForgotPassword(forgotPassword2);
//                        databaseConnector.storeForgotPassword(forgotPassword3);
//                        List<ForgotPassword> list = databaseConnector.getAllForgotPasswordsByUserId(user);
//        for (int i = 0; i < list.size(); i++) {
//        Log.d("questionList", "id: " + list.get(i).getAnswer() + "\tcâu hỏi: " + list.get(i).getUser().getUsername());
//        }


//    Category category = new Category(1, "dien thoai", "iphone 15 promax");
//                databaseConnector.insertCategory(category);


//    Category category = new Category(1, "Books", "Category for books");
//    Bitmap productImage = BitmapFactory.decodeResource(LoginActivity.this.getResources(), R.drawable.girl);
//    List<Product> productList = new ArrayList<>();
//
//                for (int i = 0; i < 10; i++) {
//        Product product = new Product(
//        i, category,
//        "Book Title" + i,
//        "Description of the book" + i,
//        19.99, productImage);
//        databaseConnector.insertProduct(product);
//        }