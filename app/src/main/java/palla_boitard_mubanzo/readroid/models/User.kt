package palla_boitard_mubanzo.readroid.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var username: String? = "",
    var email: String? = "",
    var post : Post?,
    val favorites : MutableList<Post>
)