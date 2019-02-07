package palla_boitard_mubanzo.readroid.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Comment (val content : String?, val timestamp: String?)