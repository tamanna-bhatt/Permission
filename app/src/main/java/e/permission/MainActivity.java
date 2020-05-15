package e.permission;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottombar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottombar = (BottomNavigationView)findViewById(R.id.bottombar);

        bottombar.setOnNavigationItemSelectedListener(this);
        bottombar.setSelectedItemId(R.id.home);


    }
    HomeFragment  homeFragment = new HomeFragment();
    AllAppsFragment allAppsFragment = new AllAppsFragment();
    PermissionFragment permissionFragment = new PermissionFragment();
    SpecialAccessFragment specialAccessFragment = new SpecialAccessFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.home :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,homeFragment).commit();
                return true;
            case R.id.all_apps :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,allAppsFragment).commit();
                return true;
            case R.id.permissions :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,permissionFragment).commit();
                return true;
            case R.id.specil_access :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,specialAccessFragment).commit();
                return true;
        }
        return false;
    }
}
