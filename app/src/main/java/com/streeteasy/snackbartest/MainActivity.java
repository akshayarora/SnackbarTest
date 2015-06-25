package com.streeteasy.snackbartest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.*;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks
{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private static final int ACTION_NONE = 0;
    private static final int ACTION_SNACKBAR = 1;

    private int mPendingAction = ACTION_NONE;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById( R.id.navigation_drawer );
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById( R.id.drawer_layout ) );
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace( R.id.container, PlaceholderFragment.newInstance() )
                .commit();
    }

    @Override
    public void onNavigationDrawerItemSelected( int position )
    {
    }

    public void restoreActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode( ActionBar.NAVIGATION_MODE_STANDARD );
        actionBar.setDisplayShowTitleEnabled( true );
        actionBar.setTitle( mTitle );
    }


    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        if ( !mNavigationDrawerFragment.isDrawerOpen() )
        {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate( R.menu.main, menu );
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if ( id == R.id.action_settings )
        {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    public void onOpenSecondClick( View view )
    {
        Intent intent = new Intent( this, SecondActivity.class );
        startActivityForResult( intent, 0 );
        overridePendingTransition( R.anim.slide_up, R.anim.slide_down );
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data )
    {
        super.onActivityResult( requestCode, resultCode, data );
        if ( data != null )
        {
            boolean show = data.getBooleanExtra( "a", false );
            mPendingAction = show ? ACTION_SNACKBAR : ACTION_NONE;
        }
    }

    @Override
    protected void onPostResume()
    {
        super.onPostResume();
        switch ( mPendingAction )
        {
            case ACTION_SNACKBAR:
                displaySnackbar();
                break;
            default:
                break;
        }
        mPendingAction = ACTION_NONE;
    }

    private void displaySnackbar()
    {
        // We use a short delay because then the user can see the
        // incoming animation for the Snackbar
        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected Void doInBackground( Void... params )
            {
                try{
                    Thread.sleep( 250 );
                } catch( InterruptedException e ){}
                runOnUiThread( new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Snackbar.make( findViewById( R.id.scroll_view ), "This is a Snackbar", Snackbar.LENGTH_SHORT )
                                .show();
                    }
                } );
                return null;
            }
        }.execute();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment
    {
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance()
        {
            PlaceholderFragment fragment = new PlaceholderFragment();
            return fragment;
        }

        public PlaceholderFragment()
        {
        }

        @Override
        public View onCreateView( LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState )
        {
            View rootView = inflater.inflate( R.layout.fragment_main, container, false );
            return rootView;
        }
    }
}
