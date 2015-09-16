package playdoh.stackapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //Declare Data Model
    private StackDataModel myStack = new StackDataModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listenButtonExit();
        listenButtonPop();
        listenButtonPush();
    }

    public void listenButtonPush(){
        Button pushButton = (Button) findViewById(R.id.Button_Push);
        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer inputNumber;

                //Deal with non-number inputs
                try {
                    inputNumber = Integer.parseInt(getInputText());
                } catch (NumberFormatException nfe) {
                    //Invalid Inputs!
                    invalidInputs();
                    return;
                }

                if (!myStack.push(inputNumber)) {
                    stackIsFull();
                } else {
                    show_message(inputNumber + " is pushed,now stack is "
                            + myStack.getStackContent());

                }
            }
        });
    }

    public void listenButtonPop(){
        Button popButton = (Button) findViewById(R.id.Button_Pop);
        popButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer popped = myStack.pop();
                if(popped != null){
                   if (myStack.isStackEmpty()){
                       show_message("Popped:" + popped + "empty");
                   }
                    else{
                       show_message("Popped:" + popped + myStack.getStackContent());

                   }

                }else{
                    show_message("Poped: null, Stack is already empty");
                }
            }
        });
    }

    public void listenButtonExit(){
        Button exitButton = (Button) findViewById(R.id.Button_Quit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    private String getInputText(){
        EditText inputView = (EditText) findViewById(R.id.Number_Input);
        return inputView.getText().toString();
    }

    private void invalidInputs(){
        show_message("Invalid inputs,You have to input a number between 0 and 9");
    }

    private void stackIsFull(){
        show_message("Stack is full, Now Stack is:" + myStack.getStackContent());
    }

    private void show_message(String message){
        TextView messageView = (TextView) findViewById(R.id.message_view);
        messageView.setText(message);
    }
}
