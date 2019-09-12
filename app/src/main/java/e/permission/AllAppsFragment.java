package e.permission;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapters.AppAdapter;
import Models.AppList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllAppsFragment extends Fragment {

    ListView userInstalledApps ;


    public AllAppsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_apps, container, false);

        userInstalledApps =(ListView)view.findViewById(R.id.installed_app_list);

        List<AppList> installedApps = getInstalledApps();
        AppAdapter installedAppAdapter = new AppAdapter(getContext(), installedApps);
        userInstalledApps.setAdapter(installedAppAdapter);


        userInstalledApps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                AppList app = (AppList) parent.getItemAtPosition(position);
                Toast.makeText(getContext(),app.getAppPackage(),Toast.LENGTH_SHORT).show();
                //List<PackageInfo> p = getActivity().getPackageManager().getInstalledPackages(0);

               //String appPackage = p.get(position).applicationInfo.packageName;
               getPermissionsByPackageName(app.getAppPackage());


            }
        });


        return view;

    }



    private List<AppList> getInstalledApps() {
        List<AppList> res = new ArrayList<AppList>();
        List<PackageInfo> packs = getActivity().getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((isSystemPackage(p) == false)) {
                String appName = p.applicationInfo.loadLabel(getActivity().getPackageManager()).toString();
                String appPackage = p.applicationInfo.packageName;
                Drawable icon = p.applicationInfo.loadIcon(getActivity().getPackageManager());

                res.add(new AppList(appName,appPackage, icon));
            }
        }
        return res;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }

    protected void getPermissionsByPackageName(String packageName){

        // Initialize a new string builder instance
        StringBuilder builder = new StringBuilder();

        try {
            // Get the package info
            PackageManager pm = getActivity().getPackageManager();

            List<PermissionGroupInfo> group = pm.getAllPermissionGroups(0);

            for (PermissionGroupInfo pgInfo : group) {
                Toast.makeText(getContext(), pgInfo.loadLabel(pm), Toast.LENGTH_SHORT).show();
            }

            PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            int index = 1;
            for(String permission : packageInfo.requestedPermissions) {
                if (pm.checkPermission(permission, packageName) == PackageManager.PERMISSION_GRANTED) {
                    PermissionInfo perInfo = pm.getPermissionInfo(permission, 0);
                    if (perInfo.group != null) {
                        PermissionGroupInfo pginfo = pm.getPermissionGroupInfo(perInfo.group, 0);
                        String groupName = pginfo.loadLabel(pm).toString();
                        List<String> permissionList = new ArrayList<String>();
                        if(!permissionList.contains(groupName))
                        {
                            permissionList.add(groupName);
                        }
                        Log.e("group", "index:" + index + "Group:" + pginfo.loadLabel(pm));
                    }
                } else {
                    //Toast.makeText(getContext(), "index: " + index + "Permission:" + permission,
                    //Toast.LENGTH_SHORT).show();
                }
                index++;
            }
                // PermissionGroupInfo pgInfo = pm.getPermissionGroupInfo(permission,0);
                //Toast.makeText(getContext(),perInfo.loadLabel(pm), Toast.LENGTH_SHORT).show();
            // Permissions counter


            // Loop through the package info requested permissions
//            for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
//                if ((packageInfo.requestedPermissionsFlags[i] & PackageInfo.REQUESTED_PERMISSION_GRANTED) != 0) {
//                    String permission =packageInfo.requestedPermissions[i];
//
//                    builder.append("" + counter + ". " + permission + ".\n");
//                    //builder.append(""+counter + ". " + permission+".\n");
//                    counter++;
//                }
//
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(getContext(),packageName,Toast.LENGTH_SHORT).show();
        Log.e("builder",builder.toString()) ;
    }



}



