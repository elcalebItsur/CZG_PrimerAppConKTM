package com.example.czg_primerappconktm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

import czg_primerappconktm.composeapp.generated.resources.Res
import czg_primerappconktm.composeapp.generated.resources.jp
import czg_primerappconktm.composeapp.generated.resources.mx
import czg_primerappconktm.composeapp.generated.resources.eg
import czg_primerappconktm.composeapp.generated.resources.fr
import czg_primerappconktm.composeapp.generated.resources.id
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.DrawableResource
import kotlinx.datetime.LocalTime
import kotlin.time.Clock

data class Country(val name: String, val zone: TimeZone, val image: DrawableResource)

fun currentTimeAt(location: String, zone: TimeZone): String {
    fun LocalTime.formatted() = "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}:${second.toString().padStart(2, '0')}"

    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(zone).time

    return "La hora en $location es ${localTime.formatted()}"
}

val defaultCountries = listOf(
    Country("Japón", TimeZone.of("Asia/Tokyo"), Res.drawable.jp),
    Country("Francia", TimeZone.of("Europe/Paris"), Res.drawable.fr),
    Country("México", TimeZone.of("America/Mexico_City"), Res.drawable.mx),
    Country("Indonesia", TimeZone.of("Asia/Jakarta"), Res.drawable.id),
    Country("Egipto", TimeZone.of("Africa/Cairo"), Res.drawable.eg)
)

@Composable
@Preview
fun App(countries: List<Country> = defaultCountries) {
    MaterialTheme {
        var showCountries by remember { mutableStateOf(false) }
        var timeAtLocation by remember { mutableStateOf("Ninguna ubicación seleccionada") }

        Column(
            modifier = Modifier
                .padding(20.dp)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                timeAtLocation,
                style = TextStyle(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            
            Button(
                modifier = Modifier.padding(top = 10.dp),
                onClick = { showCountries = !showCountries }
            ) {
                Text("Seleccionar Ubicación")
            }

            DropdownMenu(
                expanded = showCountries,
                onDismissRequest = { showCountries = false }
            ) {
                countries.forEach { (name, zone, image) ->
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painterResource(image),
                                    modifier = Modifier.size(30.dp).padding(end = 10.dp),
                                    contentDescription = "Bandera de $name"
                                )
                                Text(name)
                            }
                        },
                        onClick = {
                            timeAtLocation = currentTimeAt(name, zone)
                            showCountries = false
                        }
                    )
                }
            }
        }
    }
}
