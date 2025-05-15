package com.example.myapplication4

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var throwButton: Button
    private var lastSelectedMarkerTitle: String? = null

    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        throwButton = findViewById(R.id.throwButton)
        throwButton.visibility = View.GONE

        throwButton.setOnClickListener {
            Log.d("MapsActivity", "✅ 버튼 클릭됨")
            launchCamera()
        }

        cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val imageBitmap = result.data!!.extras?.get("data") as? Bitmap
                imageBitmap?.let { analyzeImage(it) }
            } else {
                Toast.makeText(this, "사진 촬영이 취소되었습니다", Toast.LENGTH_SHORT).show()
            }
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        try {
            val success = mMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style)
            )
            if (!success) Log.e("MapsActivity", "지도 스타일 적용 실패")
        } catch (e: Resources.NotFoundException) {
            Log.e("MapsActivity", "지도 스타일 파일 없음", e)
        }

        mMap.setOnMarkerClickListener { marker ->
            if (marker.title != "현재 위치") {
                lastSelectedMarkerTitle = marker.title
                throwButton.text = "Let's Throw at ${marker.title}!"
                throwButton.visibility = View.VISIBLE
            } else {
                throwButton.visibility = View.GONE
            }
            false
        }

        val locationRequest = LocationRequest.create().apply {
            interval = 5000
            fastestInterval = 2000
            priority = Priority.PRIORITY_HIGH_ACCURACY
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    mMap.clear()

                    val vectorDrawable = ContextCompat.getDrawable(this@MapsActivity, R.drawable.dot_marker)!!
                    val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
                    val canvas = Canvas(bitmap)
                    vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
                    vectorDrawable.draw(canvas)
                    val dotIcon = BitmapDescriptorFactory.fromBitmap(bitmap)

                    mMap.addMarker(
                        MarkerOptions()
                            .position(currentLatLng)
                            .title("현재 위치")
                            .icon(dotIcon)
                    )

                    val nearbyLocations = listOf(
                        Triple("카페 서울브루잉", 0.0007, 0.0007),
                        Triple("편의점 CU 광화문점", -0.0006, 0.0009),
                        Triple("한솥도시락 시청점", 0.0008, -0.0008),
                        Triple("스타벅스 덕수궁점", -0.0009, -0.0005),
                        Triple("올리브영 종로점", 0.0006, -0.0003)
                    )

                    for ((name, latOffset, lngOffset) in nearbyLocations) {
                        val pos = LatLng(currentLatLng.latitude + latOffset, currentLatLng.longitude + lngOffset)
                        mMap.addMarker(
                            MarkerOptions()
                                .position(pos)
                                .title(name)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        )
                    }

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 17f))
                }
            }
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1001)
        }
    }

    private fun launchCamera() {
        Log.d("MapsActivity", "📸 카메라 인텐트 시작")
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(packageManager) != null) {
            cameraLauncher.launch(cameraIntent)
        } else {
            Log.e("MapsActivity", "❌ 카메라 앱 없음")
            Toast.makeText(this, "카메라 앱이 없습니다", Toast.LENGTH_SHORT).show()
        }
    }


    private fun analyzeImage(bitmap: Bitmap) {
        val image = InputImage.fromBitmap(bitmap, 0)
        val options = ImageLabelerOptions.Builder()
            .setConfidenceThreshold(0.5f)
            .build()
        val labeler = ImageLabeling.getClient(options)
        labeler.process(image)
            .addOnSuccessListener { labels ->
                val validLabels = listOf("cigarette", "plastic", "paper")
                val matched = labels.any { label ->
                    validLabels.any { keyword -> label.text.contains(keyword, ignoreCase = true) }
                }

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("result", matched)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this, "분석 실패", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}
