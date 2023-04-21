package com.example.lesson001

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp         //передает в том числе контекст приложения
class NotesApplication : Application()