plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("kotlin-android")
}


android {
    compileSdkVersion(Dependencies.Android.compileSdkVersion)
    buildToolsVersion(Dependencies.Android.buildToolsVersion)

    defaultConfig {
        applicationId = Dependencies.Android.applicationId
        minSdkVersion(Dependencies.Android.minSdkVersion)
        targetSdkVersion(Dependencies.Android.targetSdkVersion)
        versionCode = Dependencies.Android.versionCode
        versionName = Dependencies.Android.versionName


        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = Dependencies.TestLibs.androidJUnitRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = Dependencies.Android.jvmTarget
    }

    viewBinding.isEnabled = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.Kotlin.kotlinStd)
    implementation(Dependencies.SupportLibs.androidKtx)
    implementation(Dependencies.SupportLibs.appcompat)
    implementation(Dependencies.SupportLibs.constraintLayout)
    implementation(Dependencies.SupportLibs.material)
    implementation(Dependencies.SupportLibs.navigationFragment)
    implementation(Dependencies.SupportLibs.navigationUi)
    implementation(Dependencies.SupportLibs.androidxFragment)
    implementation(Dependencies.SupportLibs.androidXLifeCycle)
    implementation(Dependencies.SupportLibs.gson)
    implementation(Dependencies.DependencyInjection.koinAndroid)
    implementation(Dependencies.DependencyInjection.koinViewModel)
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.google.android.gms:play-services-location:19.0.1")
    implementation(Dependencies.Services.retrofit2)
    implementation(Dependencies.Services.retrofit2Adapter)
    implementation(Dependencies.Services.retrofit2Converter)
    implementation(Dependencies.Services.okhttp)
    implementation(Dependencies.Services.okhttpMockWebserver)
    implementation(Dependencies.SupportLibs.picasso)
    implementation(Dependencies.Persistence.roomRuntime)
    implementation(Dependencies.Persistence.roomKtx)
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.constraintlayout:constraintlayout:2.0.1")
    kapt(Dependencies.Persistence.roomCompiler)

    testImplementation(Dependencies.TestLibs.mockk)
    testImplementation(Dependencies.TestLibs.junit)
    testImplementation(Dependencies.TestLibs.junit5Api)
    testImplementation(Dependencies.TestLibs.junit5Params)
    testImplementation(Dependencies.TestLibs.junit5Engine)
    testImplementation(Dependencies.TestLibs.junit5VintageEngine)
    testImplementation(Dependencies.TestLibs.kotlinCoroutineTest)
    testImplementation(Dependencies.TestLibs.archCompTestLibraries)
    testImplementation(Dependencies.TestLibs.robolectric)

    testImplementation(Dependencies.TestLibs.androidxJunit)
    testImplementation(Dependencies.TestLibs.espresso)
    testImplementation(Dependencies.TestLibs.testRunner)
    testImplementation(Dependencies.TestLibs.testRules)
    testImplementation("io.vertx:vertx-codegen:3.6.0")

    androidTestImplementation(Dependencies.TestLibs.androidxJunit)
    androidTestImplementation(Dependencies.TestLibs.espresso)
    androidTestImplementation(Dependencies.TestLibs.testRunner)
    androidTestImplementation(Dependencies.TestLibs.testRules)

    debugImplementation(Dependencies.TestLibs.testFragment)
}
