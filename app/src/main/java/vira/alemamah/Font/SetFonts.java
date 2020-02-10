package vira.alemamah.Font;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetFonts {
    private Context context;
    private String fontPath;
    private Typeface font;
    public SetFonts(Context context, String fontPath) {
        this.context = context;
        this.fontPath = fontPath;
        font = Typeface.createFromAsset(context.getAssets(),fontPath);
    }
    public void setFont(String Object,View view)
    {
        switch (Object)
        {
            case "TextView":
                TextView textView = (TextView) view;
                textView.setTypeface(font);
                break;
            case "Button":
                Button button = (Button) view;
                button.setTypeface(font);
                break;
            case "EditText":
                EditText editText = (EditText) view;
                editText.setTypeface(font);
                break;
        }
    }
}
