package com.devsteammaster.poi.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.devsteammaster.poi.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}