package com.example.myapplication4

import android.Manifest
<<<<<<< HEAD
import android.widget.ImageView
import android.widget.TextView
=======
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17
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
<<<<<<< HEAD
    private lateinit var throwButton: ImageView
    private lateinit var throwButtonHolder: ImageView
    private lateinit var trashcanInfo: TextView
=======
    private lateinit var throwButton: Button
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17
    private var lastSelectedMarkerTitle: String? = null

    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
<<<<<<< HEAD
        val backButtonArea = findViewById<View>(R.id.backButtonArea)

        backButtonArea.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish() // 현재 액티비티 종료 = 뒤로가기
        }

        // 카메라 권한 요청
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                1002
            )
        }

        throwButton = findViewById<ImageView>(R.id.throwButton)
        throwButtonHolder = findViewById<ImageView>(R.id.throwButtonHolder)
        trashcanInfo = findViewById<TextView>(R.id.trashcanInfo)
        throwButton.visibility = View.GONE
        throwButtonHolder.visibility = View.GONE
        trashcanInfo.visibility = View.GONE

        throwButton.setOnClickListener {
            val prefs = getSharedPreferences("user_data", MODE_PRIVATE)
            val lastTime = prefs.getLong("last_time", 0L)
            val currentTime = System.currentTimeMillis()
            val coolTimeMillis = 30 * 60 * 1000  // 30분 = 1800000ms

            if (lastTime != 0L && currentTime - lastTime < coolTimeMillis) {
                val remaining = (coolTimeMillis - (currentTime - lastTime)) / 1000  // 초 단위
                val minutes = (remaining / 60).toInt()
                val seconds = (remaining % 60).toInt()

                Toast.makeText(this@MapsActivity, "⏳ 인증은 ${minutes}분 ${seconds}초 후에 다시 시도할 수 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("MapsActivity", "✅ 버튼 클릭됨")
                launchCamera()
            }
=======

        throwButton = findViewById(R.id.throwButton)
        throwButton.visibility = View.GONE

        throwButton.setOnClickListener {
            Log.d("MapsActivity", "✅ 버튼 클릭됨")
            launchCamera()
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17
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
<<<<<<< HEAD
                trashcanInfo.text = "${marker.title}"
                trashcanInfo.visibility = View.VISIBLE
                throwButton.visibility = View.VISIBLE
                throwButtonHolder.visibility = View.VISIBLE
            } else {
                trashcanInfo.visibility = View.GONE
                throwButton.visibility = View.GONE
                throwButtonHolder.visibility = View.GONE
=======
                throwButton.text = "Let's Throw at ${marker.title}!"
                throwButton.visibility = View.VISIBLE
            } else {
                throwButton.visibility = View.GONE
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17
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
<<<<<<< HEAD
                        Triple("정운오IT교양관 지하 중앙", 37.58464437589525, 127.02845156886644),
                        Triple("정운오IT교양관 지상 1층", 37.58455421422469, 127.02870910414879),
                        Triple("정운오IT교양관 2층 출입구", 37.584534046410845, 127.02827603994534),
                        Triple("정운오IT교양관 중앙 계단", 37.584534046400845, 127.02857603994534),
                        Triple("정운오IT교양관 3충 화장실", 37.58454421422469, 127.02860910414879)
                    )


                    for ((name, lat, lng) in nearbyLocations) {
                        val pos = LatLng(lat, lng)
=======
                        Triple("카페 서울브루잉", 0.0007, 0.0007),
                        Triple("편의점 CU 광화문점", -0.0006, 0.0009),
                        Triple("한솥도시락 시청점", 0.0008, -0.0008),
                        Triple("스타벅스 덕수궁점", -0.0009, -0.0005),
                        Triple("올리브영 종로점", 0.0006, -0.0003)
                    )

                    for ((name, latOffset, lngOffset) in nearbyLocations) {
                        val pos = LatLng(currentLatLng.latitude + latOffset, currentLatLng.longitude + lngOffset)
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17
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
<<<<<<< HEAD
                val matchedLabel = labels.find { label ->
                    listOf("cigarette", "plastic", "paper", "cam").any { keyword ->
                        label.text.contains(keyword, ignoreCase = true)
                    }
                }?.text

                if (matchedLabel == null) {
                    // ❌ 쓰레기 인식 실패 → FailActivity로 이동
                    startActivity(Intent(this, FailActivity::class.java))
                    return@addOnSuccessListener
                }

                // ✅ 쓰레기 인식 성공
                val (coin, exp) = when {
                    matchedLabel.contains("cigarette", ignoreCase = true) -> 5 to 10
                    matchedLabel.contains("cam", ignoreCase = true) -> 7 to 15
                    matchedLabel.contains("plastic", ignoreCase = true) -> 2 to 5
                    matchedLabel.contains("paper", ignoreCase = true) -> 1 to 2
                    else -> 0 to 0
                }

                val locationName = lastSelectedMarkerTitle ?: "unknown"
                val timestamp = System.currentTimeMillis()

                updateStats(coin, exp)

                val prefs = getSharedPreferences("user_data", MODE_PRIVATE)
                prefs.edit().apply {
                    putString("last_label", matchedLabel)
                    putString("last_location", locationName)
                    putLong("last_time", timestamp)
                    putInt("last_coin", coin)
                    putInt("last_exp", exp)
                    apply()
                }

                // ✅ 인식 성공 → SuccessActivity로
                startActivity(Intent(this, SuccessActivity::class.java))
=======
                val validLabels = listOf("cigarette", "plastic", "paper")
                val matched = labels.any { label ->
                    validLabels.any { keyword -> label.text.contains(keyword, ignoreCase = true) }
                }

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("result", matched)
                startActivity(intent)
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17
            }
            .addOnFailureListener {
                Toast.makeText(this, "분석 실패", Toast.LENGTH_SHORT).show()
            }
    }

<<<<<<< HEAD
    private fun updateStats(coin: Int, exp: Int) {
        val prefs = getSharedPreferences("user_data", MODE_PRIVATE)
        val currentCoin = prefs.getInt("coin", 0)
        val currentExp = prefs.getInt("exp", 0)

        prefs.edit().apply {
            putInt("coin", currentCoin + coin)
            putInt("exp", currentExp + exp)
            apply()
        }

        Log.d("MapsActivity", "🪙 Coin: ${currentCoin + coin}, EXP: ${currentExp + exp}")
    }

=======
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17
    override fun onDestroy() {
        super.onDestroy()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}
