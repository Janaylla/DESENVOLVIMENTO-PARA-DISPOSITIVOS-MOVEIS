package com.example.mypaint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {

    SimplePaint simplePaint;
    ImageButton colorPickerButton;
    ImageButton colorPickerFillButton;
    ImageButton undoButton;
    ImageButton redoButton;
    ImageButton clearButton;
    LineWidthFragment lineWidthFragment;
    ShapeFragment shapeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simplePaint = findViewById(R.id.simplePaint);
        colorPickerButton = findViewById(R.id.colorPicker);
        colorPickerFillButton = findViewById(R.id.colorPickerFill);
        undoButton = findViewById(R.id.btn_undo);
        clearButton = findViewById(R.id.btn_clear);
        redoButton = findViewById(R.id.btn_redo);

        colorPickerButton.setColorFilter(Color.argb(255, 0, 0, 0));
        setEnabledButton(undoButton, false);
        setEnabledButton(redoButton, false);
        setEnabledButton(clearButton, false);
        colorPickerButton.setOnClickListener(this::onClickColorPicker);
        colorPickerFillButton.setOnClickListener(this::onClickColorPickerFill);
        undoButton.setOnClickListener(this::onClickColorUndo);
        clearButton.setOnClickListener(this::onClickColorClear);
        redoButton.setOnClickListener(this::onClickColorRedo);

        lineWidthFragment = new LineWidthFragment();
        shapeFragment = new ShapeFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_line_width, lineWidthFragment)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_shape, shapeFragment)
                .commit();
    }

    public void colorPickerSelectColor() {

        new ColorPickerDialog.Builder(this)
                .setTitle("Escolha a Cor da Borda")
                .setPositiveButton(getString(R.string.confirm),
                        new ColorEnvelopeListener() {
                            @Override
                            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                                setLayoutColor(envelope);
                            }
                        })
                .setNegativeButton(getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                .attachAlphaSlideBar(true)
                .attachBrightnessSlideBar(true)
                .setBottomSpace(12)
                .show();
    }

    public void colorPickerFillSelectColor() {
        new ColorPickerDialog.Builder(this)
                .setTitle("Escolhar a cor da Linha")
                .setPositiveButton(getString(R.string.confirm),
                        new ColorEnvelopeListener() {
                            @Override
                            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                                setLayoutFillColor(envelope);
                            }
                        })
                .setNegativeButton(getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                .attachAlphaSlideBar(true)
                .attachBrightnessSlideBar(true)
                .setBottomSpace(12)
                .show();
    }
    private void setLayoutColor(ColorEnvelope colorEnvelope) {
        Color color = Color.valueOf(colorEnvelope.getColor());
        simplePaint.setColor(color);
        colorPickerButton.setColorFilter(color.toArgb());
    }
    private void setLayoutFillColor(ColorEnvelope colorEnvelope) {
        Color color = Color.valueOf(colorEnvelope.getColor());
        simplePaint.setColorFill(color);
        colorPickerFillButton.setColorFilter(color.toArgb());
    }

    public void setLayoutShape(Shape shape) {
        simplePaint.setShape(shape);
    }

    public void setSimplePaintSize(int size) {
        simplePaint.setStroke(size);
    }

    public void onClickColorPicker(View view) {
        colorPickerSelectColor();
    }
    public void onClickColorPickerFill(View view) {
        colorPickerFillSelectColor();
    }

    public void onClickColorUndo(View view) {
        this.simplePaint.undo();
        hasChanges();
    }
    public void hasChanges(){
        setEnabledButton(redoButton, !simplePaint.isEmptySubsequent());
        setEnabledButton(undoButton, !simplePaint.isEmptyPrevious());
        setEnabledButton(clearButton, !simplePaint.isEmptyPrevious());
    }
    public void onClickColorRedo(View view) {
        this.simplePaint.redo();
        hasChanges();
    }

    public void onClickColorClear(View view) {
        this.simplePaint.clear();
        hasChanges();
    }

    private void setEnabledButton(ImageButton button, boolean enable) {
        button.setEnabled(enable);
        int color = enable ? Color.BLACK : Color.GRAY;
        ColorStateList tintList = ColorStateList.valueOf(color);
        button.setImageTintList(tintList);
        button.setColorFilter(color);
    }
}