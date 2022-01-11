private const val kotlinVersion = "1.4.32"
private const val androidGradleVersion = "3.6.0"

private const val koinVersion = "2.1.6"
private const val roomVersion = "2.2.0-alpha02"

//support libs
private const val appcompatVersion = "1.2.0"
private const val constraintLayoutVersion = "2.0.4"
private const val androidKtxVersion = "1.3.2"
private const val materialVersion = "1.3.0"
private const val navigationVersion = "2.3.0"
private const val navigationSafeArgsVersion = "2.3.5"
private const val androidxFragmentVersion = "1.3.3"
private const val androidXLifeCycleVersion = "2.2.0"
private const val gsonVersion = "2.8.6"
private const val picassoVersion = "2.71828"
private const val circleImageVersion = "3.0.0"

//service libs
private const val retrofitVersion = "2.7.1"
private const val okhttpVersion = "4.9.1"
private const val okhttpMockServerVersion = "4.3.1"

//test libs
private const val junitVersion = "4.13.2"
private const val espressoVersion = "3.3.0"
private const val androidxJunitVersion = "1.1.2"
private const val mockkVersion = "1.11.0"
private const val junit5Version = "5.7.1"
private const val kotlinCoroutineTestVersion = "1.4.3"
private const val testRunnerVersion = "1.1.0"
private const val testRulesVersion = "1.1.0"
private const val testFragmentVersion = "1.3.3"
private const val testCoreTestingVersion = "2.1.0"
private const val lifecycleVersion = "2.1.0"
private const val robolectricVersion = "4.3"

object Dependencies {
    object Android {
        const val minSdkVersion = 21
        const val targetSdkVersion = 30
        const val compileSdkVersion = 30
        const val buildToolsVersion = "30.0.2"
        const val applicationId = "com.picpay.desafio.android"
        const val versionCode = 1
        const val versionName = "1.0"
        const val jvmTarget = "1.8"
    }

    object BuildPlugins {
        const val androidGradle = "com.android.tools.build:gradle:$androidGradleVersion"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    }

    object Kotlin {
        const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    }

    object SupportLibs {
        const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        const val androidKtx = "androidx.core:core-ktx:$androidKtxVersion"
        const val material = "com.google.android.material:material:$materialVersion"
        const val androidxFragment = "androidx.fragment:fragment-ktx:$androidxFragmentVersion"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
        const val safeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationSafeArgsVersion"
        const val androidXLifeCycle =
            "androidx.lifecycle:lifecycle-viewmodel:$androidXLifeCycleVersion"
        const val gson = "com.google.code.gson:gson:$gsonVersion"
        const val picasso = "com.squareup.picasso:picasso:$picassoVersion"
    }

    object DependencyInjection {
        const val koinAndroid = "org.koin:koin-android:$koinVersion"
        const val koinViewModel = "org.koin:koin-androidx-viewmodel:$koinVersion"
    }

    object Services {
        const val retrofit2 = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val retrofit2Converter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val retrofit2Adapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"

        const val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
        const val okhttpMockWebserver = "com.squareup.okhttp3:mockwebserver:$okhttpMockServerVersion"
    }

    object Persistence{
        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    }

    object TestLibs {
        const val junit = "junit:junit:$junitVersion"
        const val androidxJunit = "androidx.test.ext:junit:$androidxJunitVersion"
        const val androidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val mockk = "io.mockk:mockk:$mockkVersion"
        const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
        const val testRunner = "androidx.test:runner:$testRunnerVersion"
        const val testRules = "androidx.test:rules:$testRulesVersion"
        const val testFragment = "androidx.fragment:fragment-testing:$testFragmentVersion"
        const val robolectric = "org.robolectric:robolectric:$robolectricVersion"

        //Junit5
        const val junit5Api = "org.junit.jupiter:junit-jupiter-api:$junit5Version"
        const val junit5Engine = "org.junit.jupiter:junit-jupiter-engine:$junit5Version"
        const val junit5Params = "org.junit.jupiter:junit-jupiter-params:$junit5Version"
        const val junit5VintageEngine = "org.junit.vintage:junit-vintage-engine:$junit5Version"
        const val junit5Plugin = "de.mannodermaus.gradle.plugins:android-junit5:1.6.2.0"

        //Kotlin coroutine
        const val kotlinCoroutineTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinCoroutineTestVersion"
        const val androidxCoreTest = "androidx.arch.core:core-testing:$testCoreTestingVersion"

        //Architecture components testing libraries
        const val archCompTestLibraries = "androidx.arch.core:core-testing:$lifecycleVersion"
    }
}