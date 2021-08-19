package com.example.mechanic.ViewModels

import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mechanic.Interfacess.ConverttoPdf
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.coroutines.delay
import java.io.File
import java.io.FileOutputStream

class ConvertedPDfViewModle : ViewModel {
    var convertPdf:ConverttoPdf?=null
    constructor(convertPdf: ConverttoPdf){
        this.convertPdf=convertPdf
    }
   suspend fun converImaheToPdf(filer: String, namePdf: String) {
    delay(1000)

       var st =   Picasso.get().load(filer).transform(
           RoundedCornersTransformation(10, 10)
       ).centerCrop()
           .resize(300, 300)
       Log.i("type2", "converImaheToPdf: " + st)

       var paint=Paint()
       var bitmap=BitmapFactory.decodeFile(
           File(
               Environment.getExternalStorageDirectory().toString() + "/" + filer
           ).toString()
       )
       Log.i("type2", "converImaheToPdf: " + bitmap)
       var pdfDocument=PdfDocument()
       var pageInfo=PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
       var pdfpag=pdfDocument.startPage(pageInfo)
       paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
       pdfpag.canvas.drawBitmap(bitmap, 10.4f, 100.4f, paint)
       pdfDocument.finishPage(pdfpag)
         savepdfinfile(pdfDocument, namePdf)



    }

    fun savepdfinfile(MyPdf: PdfDocument, imagename: String){

        // creat file
        val direct = File(Environment.getExternalStorageDirectory().toString() + "/Foliate")//true

        Log.i("path", "saveImagesinFile: " + Environment.getExternalStorageDirectory().toString())
        // if file erlady here
        if (!direct.exists()) { //false
            val wallpaperDirectory = File("/sdcard/Foliate/Pdf")
            wallpaperDirectory.mkdirs()
        }

        val file = File("/sdcard/Foliate/Pdf", imagename + ".pdf")
        if (file.exists()) {
            file.delete()
        }
        try {
            val out = FileOutputStream(file)
            MyPdf.writeTo(out)
            convertPdf!!.convertTopdf(true)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            convertPdf!!.convertTopdf(true)
        }



    }
    fun convertpdf2(namePdf: String){
        val document = Document()

        val directoryPath =File( Environment.getExternalStorageDirectory().toString() + "/Foliate/Pdf")
        if (!directoryPath.exists()) { //false
            val wallpaperDirectory = File("/sdcard/Foliate/Pdf")
            wallpaperDirectory.mkdirs()
        }
        val file = File("/sdcard/Foliate/Pdf", namePdf + ".pdf")
        if (file.exists()) {
            file.delete()
        }
        PdfWriter.getInstance(
            document,
            FileOutputStream("$directoryPath/$namePdf.pdf")
        ) //  Change pdf's name.


        document.open()

        val image: Image =
            Image.getInstance(Environment.getExternalStorageDirectory().toString()+ "/Foliate/$namePdf.jpg") // Change image's name and extension.

        val scaler: Float = (document.getPageSize().getWidth() - document.leftMargin()
                - document.rightMargin() - 0) / image.getWidth() * 100 // 0 means you have no indentation. If you have any, change it.

        image.scalePercent(scaler)
        image.setAlignment(Image.ALIGN_CENTER or Image.ALIGN_TOP)

        document.add(image)
        document.close()
        convertPdf!!.convertTopdf(true)
    }


}