package com.example.calculadoraflex

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var etAutonomiaCombustivel1: EditText
    private lateinit var etAutonomiaCombustivel2: EditText
    private lateinit var etValorLitroCombustivel1: EditText
    private lateinit var etValorLitroCombustivel2: EditText
    private lateinit var tvCombustivelSelecionado1: TextView
    private lateinit var tvCombustivelSelecionado2: TextView
    private lateinit var tvCombustivelEficiente: TextView
    private var botaoClicado: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etAutonomiaCombustivel1 = findViewById(R.id.etAutonomiaCombustivel1)
        etAutonomiaCombustivel2 = findViewById(R.id.etAutonomiaCombustivel2)
        etValorLitroCombustivel1 = findViewById(R.id.etValorLitroCombustivel1)
        etValorLitroCombustivel2 = findViewById(R.id.etValorLitroCombustivel2)
        tvCombustivelSelecionado1 = findViewById(R.id.tvCombustivelSelecionado1)
        tvCombustivelSelecionado2 = findViewById(R.id.tvCombustivelSelecionado2)
        tvCombustivelEficiente = findViewById(R.id.tvCombustivelEficiente)
        startActivity(intent)
    }

    fun btBuscarOnClick(view: View) {
        botaoClicado = view.id;
        val intent = Intent(this, ListarActivity::class.java)
        getResult.launch(intent)
    }

    private fun validarCombustiveis() {
        val combustivelSelecionado1 = tvCombustivelSelecionado1.text.toString().trim()
        val combustivelSelecionado2 = tvCombustivelSelecionado2.text.toString().trim()
        if (combustivelSelecionado1 == combustivelSelecionado2) {
            Toast.makeText(this, "Este combustível já foi selecionado!", Toast.LENGTH_SHORT).show()
            tvCombustivelSelecionado1.setText("")
            tvCombustivelSelecionado2.setText("")
        }
    }

    private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { it ->
        if (it.resultCode == RESULT_OK) {
            val combustivelSelecionado = it.data?.getStringExtra("combustivelSelecionado")
            when (botaoClicado) {
                R.id.btBuscarCombustivel1 -> tvCombustivelSelecionado1.setText(combustivelSelecionado)
                R.id.btBuscarCombustivel2 -> tvCombustivelSelecionado2.setText(combustivelSelecionado)
            }
            validarCombustiveis()
        }
    }

    /*
    implementar uma classe para evitar repetições?
     */
    private fun calcularValorGastoCombustivel(){
        val autonomiaCombustivel1 = etAutonomiaCombustivel1.text.toString().toDouble()
        val combustivelSelecionado1 = tvCombustivelSelecionado1.text
        val valorLitroCombustivel1 = etValorLitroCombustivel1.text.toString().toDouble()
        val valorPorQuilometroCombustivel1 = autonomiaCombustivel1 / valorLitroCombustivel1;
        val autonomiaCombustivel2 = etAutonomiaCombustivel2.text.toString().toDouble()
        val combustivelSelecionado2 = tvCombustivelSelecionado2.text
        val valorLitroCombustivel2 = etValorLitroCombustivel2.text.toString().toDouble()
        val valorPorQuilometroCombustivel2 = autonomiaCombustivel2 / valorLitroCombustivel2;
        if (valorPorQuilometroCombustivel1 < valorPorQuilometroCombustivel2) {
            tvCombustivelEficiente.setText("Abasteça com $combustivelSelecionado1")
        } else {
            tvCombustivelEficiente.setText("Abasteça com $combustivelSelecionado2")
        }
    }

    fun btCalcularOnClick(view: View) {
       calcularValorGastoCombustivel()
    }
}