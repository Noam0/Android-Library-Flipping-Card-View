package com.example.flippingcardlibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
public class FlippingCardView extends ConstraintLayout {

    private CardView frontView;
    private CardView backView;
    private ImageView frontImageView;
    private ImageView backImageView;
    private boolean isFlipped = false;
    private int flipDuration = 150; // Default flip duration

    private int flipStyle = 0;  // 0 for normal, 1 for with elevation

    public FlippingCardView(Context context) {
        super(context);
        init(context, null);
    }

    public FlippingCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FlippingCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        frontView = (CardView) inflater.inflate(R.layout.front_card, this, false);
        backView = (CardView) inflater.inflate(R.layout.back_card, this, false);

        frontImageView = frontView.findViewById(R.id.front_image);
        backImageView = backView.findViewById(R.id.back_image);

        addView(frontView);
        addView(backView);

        backView.setVisibility(GONE);
        backView.setRotationY(-90);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlippingCardView);

            float cardRadius = a.getDimension(R.styleable.FlippingCardView_cardRadius, 0);
            flipDuration = a.getInt(R.styleable.FlippingCardView_flipDuration, 150);
            flipStyle = a.getInt(R.styleable.FlippingCardView_flipStyle, 0);

            frontView.setRadius(cardRadius);
            backView.setRadius(cardRadius);

            int frontImageResId = a.getResourceId(R.styleable.FlippingCardView_frontImage, -1);
            int backImageResId = a.getResourceId(R.styleable.FlippingCardView_backImage, -1);
            frontImageView.setImageResource(frontImageResId != -1 ? frontImageResId : android.R.color.transparent);
            backImageView.setImageResource(backImageResId != -1 ? backImageResId : android.R.color.transparent);

            a.recycle();
        }
    }

    public void setFlipDuration(int duration) {
        this.flipDuration = duration;
    }

    public void flipCard() {
        if (isFlipped) {
            if (flipStyle == 0) {
                flipToFront();
            } else {
                performFlipAnimationWithElevation(backView, frontView);
            }
        } else {
            if (flipStyle == 0) {
                flipToBack();
            } else {
                performFlipAnimationWithElevation(frontView, backView);
            }
        }
        isFlipped = !isFlipped;
    }

    public void setFlipStyle(int style) {
        this.flipStyle = style;  // 0 for normal, 1 for with elevation
    }

    private void flipToFront() {
        performFlipAnimation(backView, frontView);
    }

    private void flipToBack() {
        performFlipAnimation(frontView, backView);
    }

    private void performFlipAnimation(final CardView fromView, final CardView toView) {
        fromView.animate().rotationY(90).setDuration(flipDuration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                fromView.setVisibility(GONE);
                toView.setVisibility(VISIBLE);
                toView.setRotationY(-90);
                toView.animate().rotationY(0).setDuration(flipDuration).setListener(null);
            }
        });
    }



    private void performFlipAnimationWithElevation(final CardView fromView, final CardView toView) {
        // Set initial elevation for the view that is about to flip
        updateElevation(fromView, 10); // Raise the card a bit before flipping

        fromView.animate().rotationY(90).setDuration(flipDuration / 2)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        fromView.setVisibility(GONE);
                        toView.setVisibility(VISIBLE);
                        toView.setRotationY(-90);

                        // Start lowering the elevation as the card starts to face up
                        animateElevation(toView, 10, 0);

                        toView.animate().rotationY(0).setDuration(flipDuration / 2)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        // Reset or adjust elevation if necessary after animation
                                        updateElevation(toView, 5); // Example of setting it lower than initial
                                    }
                                });
                    }
                });
    }

    private void updateElevation(View view, float elevation) {
        view.setElevation(elevation);
    }

    private void animateElevation(final View view, float startElevation, float endElevation) {
        ObjectAnimator elevationAnimator = ObjectAnimator.ofFloat(view, "elevation", startElevation, endElevation);
        elevationAnimator.setDuration(flipDuration);
        elevationAnimator.start();
    }


}