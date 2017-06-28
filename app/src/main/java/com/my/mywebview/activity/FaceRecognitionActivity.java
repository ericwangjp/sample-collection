package com.my.mywebview.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.my.mywebview.R;

public class FaceRecognitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_face_recognition);
        setContentView(new myView(this));
    }
    private class myView extends View {

        private int imageWidth, imageHeight;
        private int numberOfFace = 5;
        private FaceDetector myFaceDetect;
        private FaceDetector.Face[] myFace;
        float myEyesDistance;
        int numberOfFaceDetected;

        Bitmap myBitmap;

        public myView(Context context) {
            super(context);
            BitmapFactory.Options BitmapFactoryOptionsbfo = new BitmapFactory.Options();
            BitmapFactoryOptionsbfo.inPreferredConfig = Bitmap.Config.RGB_565;
            myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img2, BitmapFactoryOptionsbfo);
            imageWidth = myBitmap.getWidth();
            imageHeight = myBitmap.getHeight();
            myFace = new FaceDetector.Face[numberOfFace];
            myFaceDetect = new FaceDetector(imageWidth, imageHeight, numberOfFace);
            numberOfFaceDetected = myFaceDetect.findFaces(myBitmap, myFace);
        }

        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub

            canvas.drawBitmap(myBitmap, 0, 0, null);

            Paint myPaint = new Paint();
            myPaint.setColor(Color.GREEN);
            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(3);

            for(int i=0; i < numberOfFaceDetected; i++)
            {
                FaceDetector.Face face = myFace[i];
                PointF myMidPoint = new PointF();
                face.getMidPoint(myMidPoint);
                myEyesDistance = face.eyesDistance();
                canvas.drawRect(
                        (int)(myMidPoint.x - myEyesDistance*2),
                        (int)(myMidPoint.y - myEyesDistance*2),
                        (int)(myMidPoint.x + myEyesDistance*2),
                        (int)(myMidPoint.y + myEyesDistance*2),
                        myPaint);
            }
        }
    }
}
