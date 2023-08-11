import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.judahben149.motiontraction"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.judahben149.motiontraction"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        buildConfigField ("String", "API_KEY", getApiKey())
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    val timberVersion = "5.0.1"
    val hiltVersion = "2.46.1"
    val lifecycleVersion = "2.6.1"
    val roomVersion = "2.5.2"
    val retrofitVersion = "2.9.0"
    val okhttpVersion = "4.10.0"
    val gsonVersion = "2.9.0"
    val pagingVersion = "3.2.0"
    val glideVersion = "4.15.1"
    val epoxyVersion = "5.0.0"
    val rxJavaVersion = "2.2.13"
    val rxAndroidVersion = "2.1.1"
    val swipeRefreshVersion = "1.2.0-alpha01"
    val shimmerVersion = "0.5.0"
    val lottieVersion = "5.2.0"
    val mockitoVersion = "4.3.0"
    val jUnitVersion = "4.13.2"
    val truthVersion = "1.1.2"
    val splashVersion = "1.0.1"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")

    //Swipe to Refresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:$swipeRefreshVersion")

    //Lottie animation library
    implementation("com.airbnb.android:lottie:$lottieVersion")

    // Shimmer layout
    implementation("com.facebook.shimmer:shimmer:$shimmerVersion")

    //Splash
    implementation("androidx.core:core-splashscreen:$splashVersion")

    // Timber
    implementation("com.jakewharton.timber:timber:$timberVersion")

    // Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")

    // ViewModel & LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")

    // Retrofit & OkHttp
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")

    // Gson
    implementation("com.squareup.retrofit2:converter-gson:$gsonVersion")

    // Paging 3
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")
    implementation("androidx.paging:paging-common-ktx:$pagingVersion")

    // Rx Java
    implementation("io.reactivex.rxjava2:rxjava:$rxJavaVersion")
    implementation("io.reactivex.rxjava2:rxandroid:$rxAndroidVersion")
    implementation("androidx.paging:paging-rxjava2:$pagingVersion")
    implementation("androidx.room:room-rxjava2:$roomVersion")

    // Glide
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    kapt("com.github.bumptech.glide:compiler:$glideVersion")

    // Epoxy
    implementation("com.airbnb.android:epoxy:$epoxyVersion")
    implementation("com.airbnb.android:epoxy-paging:$epoxyVersion")
    implementation("com.airbnb.android:epoxy-paging3:$epoxyVersion")

    // Mockito
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.mockito:mockito-inline:3.11.2")
    testImplementation("org.mockito:mockito-android:$mockitoVersion")

    // J-Unit
    testImplementation("junit:junit:$jUnitVersion")

    // Truth - Assertion
    testImplementation("com.google.truth:truth:$truthVersion")
    androidTestImplementation("com.google.truth:truth:$truthVersion")

    testImplementation("junit:junit:4.13.2")
    testImplementation("android.arch.core:core-testing:1.1.1")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}

fun getApiKey(): String {
    val propFile = rootProject.file("./local.properties")
    val properties = Properties()
    properties.load(FileInputStream(propFile))
    return properties.getProperty("TMDB_API_KEY")
}