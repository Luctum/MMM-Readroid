package palla_boitard_mubanzo.readroid.models

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var username: String? = "",
    var email: String? = "",
    var posts: MutableList<Post>? = arrayListOf(),
    val favorites: MutableList<Post>? = arrayListOf()
)

class FireBaseUserHandler : FireBaseHandler<User> {

    override fun getReference(ref: String): DatabaseReference {
        return FirebaseDatabase.getInstance().reference.child(ref)
    }

    override fun getChild(ref: String, child: String): DatabaseReference {
        return getReference(ref).child(child)
    }

    override fun create(ref:String, child: String?, obj: User) {
        val databaseReference = this.getChild(ref, child.toString())
        databaseReference.setValue(obj)
    }

}