package com.example.tentandojogo
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var gameEnd = false
    var isPlayer1 = true

    lateinit var topoStart: ImageView
    lateinit var topoCentro: ImageView
    lateinit var topoEnd: ImageView

    lateinit var center: ImageView
    lateinit var centerStart: ImageView
    lateinit var centerEnd: ImageView

    lateinit var bottomStart: ImageView
    lateinit var bottom: ImageView
    lateinit var bottomEnd: ImageView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        centerStart = findViewById(R.id.centerStart)
        center = findViewById(R.id.center)
        centerEnd = findViewById(R.id.centerEnd)

        topoStart = findViewById(R.id.topoStart)
        topoCentro = findViewById(R.id.topoCentro)
        topoEnd = findViewById(R.id.topoEnd)

        bottomStart = findViewById(R.id.bottomStart)
        bottom = findViewById(R.id.bottom)
        bottomEnd = findViewById(R.id.bottomEnd)

        configureBox(centerStart)
        configureBox(center)
        configureBox(centerEnd)

        configureBox(topoStart)
        configureBox(topoCentro)
        configureBox(topoEnd)

        configureBox(bottomStart)
        configureBox(bottom)
        configureBox(bottomEnd)

        val btnReset = findViewById<Button>(R.id.button_reset)
        btnReset.setOnClickListener { resetGame() }

    }



    fun configureBox(box: ImageView) {
        box.setOnClickListener {
            if (box.tag == null && !gameEnd) {
                if (isPlayer1) {
                    box.setImageResource(R.drawable.baseline_close_24)
                    isPlayer1 = false
                    box.tag = 1
                } else {
                    box.setImageResource(R.drawable.baseline_remove_circle_outline_24)
                    isPlayer1 = true
                    box.tag = 2
                }

                gameEnd = playerWin(1) || playerWin(2)

                if (gameEnd) {
                    if (playerWin(1)) {
                        Toast.makeText(this@MainActivity, "player 1 venceu", Toast.LENGTH_SHORT).show()
                    } else if (playerWin(2)) {
                        Toast.makeText(this@MainActivity, "player 2 venceu", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    fun playerWin(value: Int): Boolean {
         if ((topoStart.tag == value && topoCentro.tag == value && topoEnd.tag == value) ||
            (centerStart.tag == value && center.tag == value && centerEnd.tag == value) ||
            (bottomStart.tag == value && bottom.tag == value && bottomEnd.tag == value)) {
            return true
        }

         if ((topoStart.tag == value && centerStart.tag == value && bottomStart.tag == value) ||
            (topoCentro.tag == value && center.tag == value && bottom.tag == value) ||
            (topoEnd.tag == value && centerEnd.tag == value && bottomEnd.tag == value)) {
            return true
        }

         if ((topoStart.tag == value && center.tag == value && bottomEnd.tag == value) ||
            (topoEnd.tag == value && center.tag == value && bottomStart.tag == value)) {
            return true
        }

        return false
    }

    fun resetGame() {
         isPlayer1 = true
        gameEnd = false

         val allBoxes = listOf(
            centerStart, centerEnd, center,
            topoStart, topoCentro, topoEnd,
            bottomStart, bottom, bottomEnd
        )

        for (box in allBoxes) {
            box.setImageResource(0)
            box.tag = null
        }

         Toast.makeText(this@MainActivity, "Jogo reiniciado", Toast.LENGTH_SHORT).show()
    }
}




