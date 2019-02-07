package palla_boitard_mubanzo.readroid.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Post (val user : User?, val comments: MutableList<Comment>?)