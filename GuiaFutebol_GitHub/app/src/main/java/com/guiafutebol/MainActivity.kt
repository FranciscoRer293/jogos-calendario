package com.guiafutebol

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(60, 80, 60, 80)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        val title = TextView(this).apply {
            text = "Guia de Jogos - Brasileirão"
            textSize = 26f
            gravity = Gravity.CENTER
        }
        layout.addView(title)

        val jogo = TextView(this).apply {
            text = "\n⚽ Flamengo x Palmeiras\n📅 14/09 - 21h30\n📺 Globo / Premiere\n"
            textSize = 20f
        }
        layout.addView(jogo)

        val btnGlobo = Button(this).apply {
            text = "Assistir na Globo"
            setOnClickListener { abrirApp("com.globo.globotv") }
        }
        layout.addView(btnGlobo)

        val btnPremiere = Button(this).apply {
            text = "Assistir no Premiere"
            setOnClickListener { abrirApp("br.com.globo.globosatplay") }
        }
        layout.addView(btnPremiere)

        setContentView(layout)
    }

    private fun abrirApp(packageName: String) {
        val pm: PackageManager = packageManager
        val launchIntent: Intent? = pm.getLaunchIntentForPackage(packageName)
        if (launchIntent != null) {
            startActivity(launchIntent)
        } else {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("market://details?id=$packageName")
            if (intent.resolveActivity(pm) != null) {
                startActivity(intent)
            } else {
                val web = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName"))
                startActivity(web)
            }
        }
    }
}
