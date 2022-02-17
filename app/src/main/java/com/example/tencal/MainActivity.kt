package com.example.tencal

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnCalc = findViewById<Button>(R.id.calc)
        btnCalc.setOnClickListener{
            fun onClick(v: View?) {
                var order: Int = 0
                var number: Int = 0
                val c1l = findViewById<EditText>(R.id.editTextNumber)
                val c1t = findViewById<EditText>(R.id.editTextNumber2)
                val c2l = findViewById<EditText>(R.id.editTextNumber3)
                val c2t = findViewById<EditText>(R.id.editTextNumber4)
                val tt = findViewById<EditText>(R.id.editTextNumber5)
                if(c1l.text.isBlank()){
                    order=1
                    number+=1
                }
                if(c1t.text.isBlank()){
                    order=2
                    number+=1
                }
                if(c2l.text.isBlank()){
                    order=3
                    number+=1
                }
                if(c2t.text.isBlank()){
                    order=4
                    number+=1
                }
                if(tt.text.isBlank()){
                    order=5
                    number+=1
                }
                if(number==1) {
                    findViewById<TextView>(R.id.info).text = ""
                    val dd = DecimalFormat("#.##")
                    when (order) {
                        1->{
                            c1l.setText(dd.format(litCalc((c2l.text.toString().replace(',','.')).toFloat(),(c2t.text.toString().replace(',','.')).toFloat(),(c1t.text.toString().replace(',','.')).toFloat(), (tt.text.toString().replace(',','.')).toFloat())).toString())
                        }
                        2->{
                            c1t.setText(dd.format(tenCalc((c2l.text.toString().replace(',','.')).toFloat(),(c1l.text.toString().replace(',','.')).toFloat(),(c2t.text.toString().replace(',','.')).toFloat(),(tt.text.toString().replace(',','.')).toFloat())).toString())
                        }
                        3->{
                            c2l.setText(dd.format(litCalc((c1l.text.toString().replace(',','.')).toFloat(),(c1t.text.toString().replace(',','.')).toFloat(),(c2t.text.toString().replace(',','.')).toFloat(), (tt.text.toString().replace(',','.')).toFloat())).toString())
                        }
                        4->{
                            c2t.setText(dd.format(tenCalc((c1l.text.toString().replace(',','.')).toFloat(),(c2l.text.toString().replace(',','.')).toFloat(),(c1t.text.toString().replace(',','.')).toFloat(),(tt.text.toString().replace(',','.')).toFloat())).toString())
                        }
                        5->{
                            tt.setText(dd.format(totalCalc((c1l.text.toString().replace(',','.')).toFloat(),(c2l.text.toString().replace(',','.')).toFloat(),(c1t.text.toString().replace(',','.')).toFloat(),(c2t.text.toString().replace(',','.')).toFloat())).toString())
                        }
                    }
                }
                else if(number==0){
                    findViewById<TextView>(R.id.info).text = "Tout les champs sont pleins !"
                }
                else if(number>1){
                    println("number > 1")
                    findViewById<TextView>(R.id.info).text = "Il faut plus de valeurs !"
                }
            }
            onClick(findViewById(R.layout.activity_main))
        }
    }
    private fun totalCalc(X:Float, Y:Float, Tx:Float, Ty:Float): Float {
        return if(Tx>1 && Ty>1){
            (X/(X+Y)*(Tx/100)+Y/(X+Y)*(Ty/100))*100
        } else {
            X / (X + Y) * (Tx) + Y / (X + Y) * (Ty)
        }
    }
    private fun tenCalc(X:Float, Y:Float, Tx:Float, Tt:Float): Float {
        return if(Tx>1 && Tt>1){
            (((Tt/100) - (X / (X + Y) * (Tx/100)))/(Y / (X + Y)))*100
        } else {
            (Tt - (X / (X + Y) * (Tx)))/(Y / (X + Y))
        }
    }
    private fun litCalc(X:Float, Tx:Float, Ty:Float, Tt:Float): Float {
        var Y: Float = 0.0F
        var add: Float = 1.0F
        if(Ty<Tx) {
            add = -add
        }
        while(totalCalc(X,Y,Tx,Ty)<Tt){
            Y+=add
            }
        while(totalCalc(X,Y,Tx,Ty)>Tt){
            Y-=add/100
        }
        Y+=add/100
        return Y
        }
    }