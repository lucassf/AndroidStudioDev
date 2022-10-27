package com.fiapon.helloworld.multimedia

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import com.fiapon.helloworld.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_google_maps.*


class GoogleMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    var googleMap: GoogleMap? = null

    private val fiapVilaOlimpia = LatLng(-23.5955843, -46.6851937)
    private val fiapPaulista = LatLng(-23.5643721, -46.652857)
    private val fiapVilaMariana = LatLng(-23.5746685, -46.6232043)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_maps)

        val mapFragment = userMap as SupportMapFragment
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val units = arrayOf(
            arrayOf(
                "FIAP Campus Vila Olimpia", "Rua Olimpíadas,186\nSão Paulo - SP\nCEP: 04551-000"
            ),
            arrayOf("FIAP Campus Paulista", "Av. Paulista,1106\nSão Paulo - SP\nCEP: 01311-000"),
            arrayOf(
                "FIAP Campus Vila Mariana",
                "Av. Lins de Vasconcelos,1264\nSão Paulo - SP\nCEP: 01538-001"
            )
        )

        this.googleMap = googleMap;

        googleMap.addMarker(
            MarkerOptions().position(fiapVilaOlimpia).title(units[0][0]).snippet(units[0][1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
        )

        googleMap.addMarker(
            MarkerOptions().position(fiapPaulista).title(units[1][0]).snippet(units[1][1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        )

        googleMap.addMarker(
            MarkerOptions().position(fiapVilaMariana).title(units[2][0]).snippet(units[2][1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        )

        getFiapLocation(null)

        googleMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            override fun getInfoContents(marker: Marker): View? {
                val info = LinearLayout(applicationContext)
                info.orientation = LinearLayout.VERTICAL

                val title = TextView(applicationContext)
                title.setTextColor(Color.BLACK)
                title.gravity = Gravity.LEFT
                title.setTypeface(null, Typeface.BOLD)
                title.text = marker.title

                val snippet = TextView(applicationContext)
                snippet.setTextColor(Color.GRAY)
                snippet.text = marker.snippet

                //--Adiciona o titulo e o complemento na marca
                info.addView(title)
                info.addView(snippet)

                return info
            }

            override fun getInfoWindow(p0: Marker): View? {
                return null
            }

        })
    }

    fun getLocation(view: View) {
        val task = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
            )
            return
        }

        task.addOnSuccessListener {
            Log.i("GoogleMaps", "User current location: (${it.longitude}, ${it.latitude})")

            googleMap?.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        it.latitude, it.longitude
                    ), 12.5f
                )
            )
        }
    }

    fun getFiapLocation(view: View?) {
        googleMap?.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                fiapPaulista, 12.5f
            )
        )
    }

    fun getFiapDirections(view: View) {
        getFiapLocation(view)

        val url = getURL(fiapPaulista, fiapVilaOlimpia)

        val options = PolylineOptions()
        options.color(Color.BLACK)
        options.width(7f)

        Toast.makeText(this, "Fuck this", Toast.LENGTH_SHORT).show()
    }


    private fun getURL(from: LatLng, to: LatLng): String {
        val origin = "origin=" + from.latitude + "," + from.longitude
        val dest = "destination=" + to.latitude + "," + to.longitude
        val sensor = "sensor=false"
        var key: String? = "null"
        key?.let {
            val params = "$origin&$dest&$sensor&key=$it"
            return "https://maps.googleapis.com/maps/api/directions/json?$params"
        }
        throw IllegalArgumentException("Change the key value")
    }

}