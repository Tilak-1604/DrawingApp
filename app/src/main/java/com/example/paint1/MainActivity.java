//package com.example.myapp;
package com.example.paint1;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView drawingArea;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;

    private float startX, startY, endX, endY;
    private String currentShape = "Line";
    private int currentColor = Color.RED;
    private float strokeWidth = 5f;

    // Extra for preview
    private Bitmap previewBitmap;
    private Canvas previewCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingArea = findViewById(R.id.drawingArea);

        // Setup paint
        paint = new Paint();
        paint.setColor(currentColor);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE); //Uses STROKE style so only borders of shapes are drawn.

        // Create bitmap when ImageView is ready
        drawingArea.post(new Runnable() { //aa chhe to oncreate load thy jay view thy jay pchi tene load krshe etle height width male nkr 0 0 hse so post
            @Override//mul apde image view load thy jay pachi karavuu chhe etle aa bdhu
            public void run() { // runable ni method chhe run and apde thodok time pchi karavu hoy to runable use thay
                bitmap = Bitmap.createBitmap(drawingArea.getWidth(),
                        drawingArea.getHeight(),
                        Bitmap.Config.ARGB_8888);
                canvas = new Canvas(bitmap);
                canvas.drawColor(Color.WHITE); // start with white background

                previewBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                previewCanvas = new Canvas(previewBitmap);

                drawingArea.setImageBitmap(previewBitmap);
            }
        });

        // Shape buttons
        Button btnRect = findViewById(R.id.btnRect);
        Button btnLine = findViewById(R.id.btnLine);
        Button btnCircle = findViewById(R.id.btnCircle);

        btnRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentShape = "Rect";
            }
        });
        btnLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentShape = "Line";
            }
        });
        btnCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentShape = "Circle";
            }
        });

        // Color buttons
        Button btnRed = findViewById(R.id.btnRed);
        Button btnYellow = findViewById(R.id.btnYellow);
        Button btnGreen = findViewById(R.id.btnGreen);

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentColor = Color.RED;
                paint.setColor(currentColor);
            }
        });
        btnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentColor = Color.YELLOW;
                paint.setColor(currentColor);
            }
        });
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentColor = Color.GREEN;
                paint.setColor(currentColor);
            }
        });

        // Stroke width buttons
        Button btnSmall = findViewById(R.id.btnSmall);
        Button btnMedium = findViewById(R.id.btnMedium);
        Button btnThick = findViewById(R.id.btnThick);

        btnSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strokeWidth = 5f;
                paint.setStrokeWidth(strokeWidth);
            }
        });
        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strokeWidth = 15f;
                paint.setStrokeWidth(strokeWidth);
            }
        });
        btnThick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strokeWidth = 30f;
                paint.setStrokeWidth(strokeWidth);
            }
        });

        // Clear button
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCanvas();
            }
        });

        // Touch for drawing
        drawingArea.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://finger muki
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE://screen pr drag kri
                        endX = event.getX();
                        endY = event.getY();
                        showPreview();
                        break;
                    case MotionEvent.ACTION_UP://finger upadi
                        endX = event.getX();
                        endY = event.getY();
                        commitShape();
                        break;
                }
                return true;
            }
        });
    }

    // Live preview while dragging
    private void showPreview() {
        previewBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        previewCanvas = new Canvas(previewBitmap);

        paint.setColor(currentColor);
        paint.setStrokeWidth(strokeWidth);

        switch (currentShape) {
            case "Line":
                previewCanvas.drawLine(startX, startY, endX, endY, paint);
                break;
            case "Rect":
                previewCanvas.drawRect(startX, startY, endX, endY, paint);
                break;
            case "Circle":
                float radius = (float) Math.sqrt(Math.pow(endX - startX, 2)
                        + Math.pow(endY - startY, 2));
                previewCanvas.drawCircle(startX, startY, radius, paint);
                break;
        }

        drawingArea.setImageBitmap(previewBitmap);
    }

    // Finalize shape when finger is lifted
    private void commitShape() {
        if (canvas == null) return;

        paint.setColor(currentColor);
        paint.setStrokeWidth(strokeWidth);

        switch (currentShape) {
            case "Line":
                canvas.drawLine(startX, startY, endX, endY, paint);
                break;
            case "Rect":
                canvas.drawRect(startX, startY, endX, endY, paint);
                break;
            case "Circle":
                float radius = (float) Math.sqrt(Math.pow(endX - startX, 2)
                        + Math.pow(endY - startY, 2));
                canvas.drawCircle(startX, startY, radius, paint);
                break;
        }

        // After committing, update preview to match final canvas
//        previewBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
//        previewCanvas = new Canvas(previewBitmap);
//        drawingArea.setImageBitmap(previewBitmap);
    }

    private void clearCanvas() {
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            previewBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            previewCanvas = new Canvas(previewBitmap);
            drawingArea.setImageBitmap(previewBitmap);
        }
    }
}
