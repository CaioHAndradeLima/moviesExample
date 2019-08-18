package com.test.btg.transition

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.transition.TransitionInflater
import androidx.core.app.ActivityOptionsCompat
import com.test.btg.R

object TransitionHelper {

    @JvmStatic
    fun enableTransition( activity : Activity ) {
        if(isPossibleUseTransition()) {

             activity
                .window
                .sharedElementExitTransition = TransitionInflater
                                                    .from( activity )
                                                    .inflateTransition( R.transition.transitions )
        }
    }

    @JvmStatic
    fun isPossibleUseTransition() : Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
    }

    /**
     * to create instance of ActivityOptionsCompat
     * use ActivityOptionsCompat.makeSceneTransitionAnimation() method
     */
    @JvmStatic
    inline fun startActivity(activity : Activity ,it: Intent ,crossinline getter : () -> ActivityOptionsCompat) {

        if(isPossibleUseTransition()) {
            activity.startActivity( it , getter().toBundle() )
        } else {
            activity.startActivity( it )
        }

    }


}
