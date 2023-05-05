package com.example.pushnotificationfcm


import android.content.res.Resources
import android.net.Uri
import android.provider.MediaStore
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.intent.IntentCallback
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailActivityTest{

    private val dummyKeterangan = "Maintance Masook Personal"

    @Before
    fun setup(){
        ActivityScenario.launch(PermissionActivity::class.java)
    }

    @Test
    fun absenWFH() {
        onView(withId(R.id.okay_btn)).perform(click())
        onView(withId(R.id.btnClick)).perform(click())
        onView(withId(R.id.clAbsenWfh)).perform(click())
        onView(withId(R.id.clAbsenWfh)).perform(click())
        onView(withId(R.id.etPlanning)).perform(typeText(dummyKeterangan), closeSoftKeyboard())
        onView(withId(R.id.btnSaveKeterangan)).perform(click())
//        intended(hasAction(equalTo(MediaStore.ACTION_IMAGE_CAPTURE)));
//        Intents.init()
//        val expectedIntent = allOf(
//            hasAction(MediaStore.ACTION_IMAGE_CAPTURE),
//            hasExtra(MediaStore.EXTRA_OUTPUT, Matchers.anything())
//        )
////        Intents.intending(expectedIntent).respondWith(mockedResult)
//        onView(withId(R.id.clAbsenWfh)).perform(click())
//        intended(expectedIntent)
//        Intents.release()


    }

    //pengecekan untuk absen WFO
    @Test
    fun absenWFO() {
        onView(withId(R.id.okay_btn)).perform(click())
        onView(withId(R.id.btnClick)).perform(click())
        onView(withId(R.id.clAbsenWfo)).perform(click())
        onView(withId(R.id.clAbsenWfo)).perform(click())

//        intended(hasAction(equalTo(MediaStore.ACTION_IMAGE_CAPTURE)));
//        Intents.init()
//        val expectedIntent = allOf(
//            hasAction(MediaStore.ACTION_IMAGE_CAPTURE),
//            hasExtra(MediaStore.EXTRA_OUTPUT, Matchers.anything())
//        )
////        Intents.intending(expectedIntent).respondWith(mockedResult)
//        onView(withId(R.id.clAbsenWfh)).perform(click())
//        intended(expectedIntent)
//        Intents.release()
    }


}