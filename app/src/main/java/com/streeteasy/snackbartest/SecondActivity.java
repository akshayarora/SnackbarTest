package com.streeteasy.snackbartest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SecondActivity extends AppCompatActivity
{
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );
    }

    public void onDismissClick( View view )
    {
        Intent data = new Intent( );
        data.putExtra( "a", true );
        setResult( 0, data );
        finish();
    }

    public void onDismissWithTransitionClick( View view )
    {
        Intent data = new Intent( );
        data.putExtra( "a", true );
        setResult( 0, data );
        finish();
        overridePendingTransition( R.anim.hold, R.anim.slide_down );
    }
}
