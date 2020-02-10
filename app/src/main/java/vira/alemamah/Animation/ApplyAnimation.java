package vira.alemamah.Animation;

import android.animation.Animator;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class ApplyAnimation {
    public static void MoveWithAnimationClass(View view,float fromXDelta,float toXDelta,float fromYDelta,float toYDelta,int duration){
        Animation animation = new TranslateAnimation(fromXDelta,toXDelta,fromYDelta,toYDelta);
        animation.setDuration(duration);
        view.startAnimation(animation);
    }
    public static void circularAnimation(View view,int duration){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = ViewAnimationUtils.createCircularReveal(view,view.getWidth()/2,view.getHeight()/2,0,view.getHeight());
            animator.setDuration(duration);
            animator.start();
        }
    }
}
