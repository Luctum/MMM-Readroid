package palla_boitard_mubanzo.readroid.models

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Comment (val content : String?, val timestamp: String?)

class FireBaseCommentHandler: FireBaseHandler<Comment> {
    override fun getReference(ref: String): DatabaseReference {
        return FirebaseDatabase.getInstance().reference.child(ref)
    }

    override fun getChild(ref: String, child: String): DatabaseReference {
        return this.getReference(ref).child(child)
    }

    override fun create(ref: String, child: String?, obj: Comment) {
        this.getChild(ref, child.toString()).setValue(obj)
    }

}