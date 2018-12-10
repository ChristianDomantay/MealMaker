package com.example.christian.mealmaker;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfIndirectObject;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.itextpdf.text.Image.getInstance;

public class Main5Activity extends AppCompatActivity {
    private static final String TAG = "PdfCreatorActivity";
    private EditText mContentEditText;
    private File pdfFile;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    ImageView selectedImage1;
    TextView name;
    ListView list,step;
    ArrayList<String> ing = new ArrayList<String>();
    public String[] in,aw,aw1;
    ImageButton mCreateButton,imageButton,closebtn;
    String str,sname,video,link;
    public int img;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        step = (ListView) findViewById(R.id.step);
        name = (TextView) findViewById(R.id.name);
        list = (ListView) findViewById(R.id.list);
        selectedImage1 = (ImageView) findViewById(R.id.selectedImage1); // init a ImageView
        Intent intent = getIntent(); // get Intent which we set from Previous Activity
        selectedImage1.setImageResource(intent.getIntExtra("image", 0));
        video =intent.getStringExtra("video");
        link =intent.getStringExtra("link");
        name.setText(intent.getStringExtra("name"));
        sname=intent.getStringExtra("name");
        str = intent.getStringExtra("ingredient");
        aw =  str.split("\\s*%\\s*");
        //number
        ArrayList<String> away = new ArrayList<String>();
        for(int i = 0;i<aw.length;i++){
            String awayy = "•   " + aw[i];
            away.add(awayy);
        }
        String str1 = intent.getStringExtra("step");
         aw1 =  str1.split("\\s*%\\s*");
        //number
        ArrayList<String> away1 = new ArrayList<String>();
        for(int i = 0;i<aw1.length;i++){
            String awayy = i+1+".  " + aw1[i];
            away1.add(awayy);
        }

         img = intent.getIntExtra("image", 0);
        String im= toString();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,away );
        ArrayAdapter adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,away1 );
        step.setAdapter(adapter1);
        list.setAdapter(adapter);
        mCreateButton = (ImageButton) findViewById(R.id.download);
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str.isEmpty()){
                    name.setError("Body is empty");
                    name.requestFocus();
                    return;
                }
                try {
                    createPdfWrapper();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
        closebtn = (ImageButton) findViewById(R.id.closebtn);
      closebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              finish();
            }
        });

        imageButton = (ImageButton) findViewById(R.id.watch);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WebView webView = (WebView)
                        findViewById(R.id.webview);
                webView.loadUrl(video);
            }
        });

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }
    public void shareText(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBodyText = link;
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, sname);
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(intent, "Choose sharing method"));
    }

private void createPdfWrapper() throws FileNotFoundException,DocumentException{
        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }else {
            createPdf();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    try {
                        createPdfWrapper();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Permission Denied
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdf() throws FileNotFoundException, DocumentException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }
        pdfFile = new File(docsFolder.getAbsolutePath(),sname+".pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter writer =  PdfWriter.getInstance(document, output);
        document.open();

        try {

            Drawable d = getResources().getDrawable(img);
            BitmapDrawable bitDw = ((BitmapDrawable) d);
            Bitmap bmp = bitDw.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress( Bitmap.CompressFormat.JPEG, 100, stream);
            Image image = getInstance(stream.toByteArray());
            image.setAlignment(Element.ALIGN_CENTER);
            image.scaleToFit(500f, 300f);
            document.add(image);


            // fonts
            Font largeBold = new Font(Font.FontFamily.HELVETICA, 32,
                    Font.BOLD);
            Font mediumBOLD= new Font(Font.FontFamily.COURIER, 22,
                    Font.BOLD);
            Font smallBold = new Font(Font.FontFamily.COURIER, 12,
                    Font.BOLD);
            Font mediumItalic = new Font(Font.FontFamily.HELVETICA, 22,
                    Font.ITALIC);
            Font smallItalic = new Font(Font.FontFamily.HELVETICA, 12,
                    Font.ITALIC);
            Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                    Font.ITALIC | Font.UNDERLINE, BaseColor.RED);

            Paragraph paragraph = new Paragraph();
            paragraph.setAlignment(Element.ALIGN_CENTER);
            writer.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);


            paragraph.add(new Paragraph(sname,largeBold));
            paragraph.add(new Paragraph(Chunk.NEWLINE));


            paragraph.add(new Paragraph(Chunk.NEWLINE));

            paragraph.add(new Paragraph("Ingredients",mediumItalic));
            paragraph.add(new Paragraph(Chunk.NEWLINE));

            for(int i=0;i<aw.length;i++){

                paragraph.add(new Paragraph("•  "+ aw[i],smallBold));
                paragraph.add(new Paragraph(Chunk.NEWLINE));

            }
            paragraph.add(new Paragraph(Chunk.NEWLINE));
            paragraph.add(new Paragraph(Chunk.NEWLINE));
            paragraph.add(new Paragraph("Steps",mediumItalic));
            paragraph.add(new Paragraph(Chunk.NEWLINE));


            for(int i=0;i<aw1.length;i++){
             int q = i +1;
                paragraph.add(new Paragraph(q+".   "+aw1[i],smallBold));
                paragraph.add(new Paragraph(Chunk.NEWLINE));
            }

            document.add(paragraph);
            document.close();
            previewPdf();
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
    private void previewPdf() {

        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");

            startActivity(intent);
        }else{
            Toast.makeText(this,"Download a PDF Viewer to see the generated PDF",Toast.LENGTH_LONG).show();
        }
    }
}
