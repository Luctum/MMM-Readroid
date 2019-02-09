package palla_boitard_mubanzo.readroid.models

import com.google.firebase.database.DatabaseReference

interface FireBaseHandler<T> {
    fun getReference(ref: String): DatabaseReference

    fun getChild(ref: String, child: String): DatabaseReference

    fun create(ref:String, child: String?, obj: T)

}