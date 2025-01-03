package com.example.coupleexpenses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.coupleexpenses.ui.theme.CoupleExpensesTheme
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.ui.text.input.KeyboardType
import java.text.NumberFormat
import java.util.Locale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoupleExpensesTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var sueldo_persona_A by remember { mutableStateOf("") }
    var sueldo_persona_B by remember { mutableStateOf("") }
    var monto_a_pagar by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                // Sueldo persona A
                TextField(
                    value = sueldo_persona_A,
                    onValueChange = {
                        if (it.isEmpty() || it.matches(Regex("^\\d*(\\.\\d*)?$"))) {
                            sueldo_persona_A = it
                        }
                    },
                    label = { Text("Sueldo de la persona A: ") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Sueldo persona B
                TextField(
                    value = sueldo_persona_B,
                    onValueChange = {
                        if (it.isEmpty() || it.matches(Regex("^\\d*(\\.\\d*)?$"))) {
                            sueldo_persona_B = it
                        }
                    },
                    label = { Text("Sueldo de la persona B: ") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Monto a pagar
                TextField(
                    value = monto_a_pagar,
                    onValueChange = {
                        if (it.isEmpty() || it.matches(Regex("^\\d*(\\.\\d*)?$"))) {
                            monto_a_pagar = it
                        }
                    },
                    label = { Text("Monto a pagar: ") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Resultado
                Button(onClick = {
                    val sueldoA = sueldo_persona_A.toDoubleOrNull()
                    val sueldoB = sueldo_persona_B.toDoubleOrNull()
                    val monto = monto_a_pagar.toDoubleOrNull()
                    resultado = if (sueldoA != null && sueldoB != null && monto != null) {
                        val totalSueldos = sueldoA + sueldoB
                        val porcentaje = monto / totalSueldos
                        val pagoPersonaA = sueldoA * porcentaje
                        val pagoPersonaB = sueldoB * porcentaje

                        // Formatear los montos
                        val formatter = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
                        val pagoAFormateado = formatter.format(pagoPersonaA)
                        val pagoBFormateado = formatter.format(pagoPersonaB)
                        val montoFormateado = formatter.format(monto)

                        """
                            Total a pagar: $montoFormateado
                            Porcentaje del sueldo a pagar: ${(porcentaje * 100).toInt()}%
                            Persona A debe pagar: $pagoAFormateado
                            Persona B debe pagar: $pagoBFormateado                            
                        """.trimIndent()
                    } else {
                        "Por favor, ingresa bien los datos."
                    }
                }) {
                    Text("Enter")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (resultado.isNotEmpty()) {
                    Text(
                        text = resultado,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    CoupleExpensesTheme {
        MainScreen()
    }
}