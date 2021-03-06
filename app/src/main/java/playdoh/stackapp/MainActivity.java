package playdoh.stackapp;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.view.*;
import android.content.Context;


public class MainActivity extends AppCompatActivity {

    //Input field text - wiping after clicking push button
    public EditText editText;

    //Declare Data Model
    private StackDataModel myStack = new StackDataModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listenButtonExit();
        listenButtonPop();
        listenButtonPush();
        listenButtonHelp();

    }

    public void listenButtonHelp(){
        Button helpButton = (Button) findViewById(R.id.button_help);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View nilArg) {

                //Pop up a window
                Point anchor = new Point(0,0);

                showPopup(MainActivity.this, anchor);
            }
        });
    }

    private void showPopup(final Activity context, Point p) {
        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup_layout);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

        // Create the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setFocusable(true);

        // Display
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        // Gett a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.Popup_close_button);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }

    public void listenButtonPush() {
        Button pushButton = (Button)findViewById(R.id.Button_Push);
        pushButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Integer inputNumber;
                editText = (EditText)findViewById(R.id.Number_Input);

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
                    //Clear input field
                    editText.getText().clear();
                } else {
                    show_message(inputNumber + " is pushed.");
                    show_stack("Stack: " + myStack.getStackContent());
                    //Clear input field
                    editText.getText().clear();

                }
            }
        });
    }

    public void listenButtonPop() {
        Button popButton = (Button) findViewById(R.id.Button_Pop);
        popButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer popped = myStack.pop();
                if (popped != null) {
                   if (myStack.isStackEmpty()) {
                       show_message("Popped: " + popped + ".");
                       show_stack("Stack: Empty.");
                   } else {
                       show_message("Popped: " + popped + ".");
                        show_stack("Stack: " + myStack.getStackContent());
                   }
                } else {
                    show_message("Popped: null");
                    show_stack("Stack: Empty.");
                }
            }
        });
    }

    public void listenButtonExit() {
        Button exitButton = (Button) findViewById(R.id.Button_Quit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    private String getInputText() {
        EditText inputView = (EditText) findViewById(R.id.Number_Input);
        return inputView.getText().toString();
    }

    private void invalidInputs() {
        show_message("Invalid input,you have to input a number between 0 and 9.");
    }

    private void stackIsFull(){
        show_message("Stack is full!");
    }

    private void show_message(String message){
        TextView messageView = (TextView) findViewById(R.id.message_view);
        messageView.setText(message);
    }

    //Separating input value and stack content
    private void show_stack(String message) {
        TextView messageStackView = (TextView) findViewById(R.id.stack_view);
        messageStackView.setText(message);
    }
}
