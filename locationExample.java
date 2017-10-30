/** Attribution: The code to implement location services and updates
 * was adapted from "Location Strategies" on the Android Developer website,
 * unattributed author.
 * https://developer.android.com/guide/topics/location/strategies.html
 */

/* TODO: get Google Map API key by following instructions at this link:
 * https://developers.google.com/maps/documentation/javascript/get-api-key
 */

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import com.google.android.gms.maps.model.LatLng;
import static android.content.Context.LOCATION_SERVICE;

private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
private static final int ONE_SECOND = 1000;
private static final int ONE_MINUTE = 60 * ONE_SECOND;

/* TODO: Set your accuracy constraints by changing the next two variables */
private static final int DEFAULT_RESOLUTION = ONE_SECOND;
private static final int DEFAULT_DISTANCE_RESOLUTION = 1;


private Location currentBestLocation;

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Check for location permission, request if necessary
    if (ActivityCompat.checkSelfPermission(
                    this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
    } else initiateLocation();
}

/* Handles the user's response to the location permission request */
@Override
public void onRequestPermissionsResult(int requestCode,
                                       String permissions[],
                                       int[] grantResults) {
    if(requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {

        if(grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            try {
                // We were granted permission. Set up location services.
                initiateLocation();
            } catch(SecurityException exception) {
                exception.printStackTrace();
            }
        }
        // We were denied permission. Launch function to deal with it
        else noLocationServices();
    }
}

@Override
public void onDestroy() {
    super.onDestroy();
    if(locationManager != null) locationManager.removeUpdates(locationListener);
}

/* Starts location services */
public void initiateLocation() {
    locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

    locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateCurrentLocation(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
            noLocationServices();
        }
    };
    try {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                DEFAULT_LOCATION_RESOLUTION,
                DEFAULT_DISTANCE_RESOLUTION,
                locationListener);
        // Set initial location to be the device's last reported location
        currentBestLocation =
                locationManager
                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
    } catch (SecurityException e) {
        e.printStackTrace();
    }
}

private void noLocationServices() {
    /* TODO: The code below simply exits the app. If you want to do something
     * different (such as run at diminished capacity) replace the rest of this
     * method with something else)
    */
    new AlertDialog.Builder(getActivity())
            .setMessage("Cannot run without location services.")
            .setPositiveButton("Close", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    this.finish();
                }
            })
            .show();
}

/* Function to execute methods upon receipt of a new location */
public void updateCurrentLocation(Location location) {
    if(isBetterLocation(location, currentBestLocation)) {
        currentBestLocation = location;

        /* TODO: This is where your code goes. For instance, if you wanted
         * to make a call to a Backend/Mongo DB, this is where you'd do it. If
         * you have any internal procedures that need to run/variables to be
         * updated at each new location, put those function calls here.
        */

    }
}

/* Determines if new location is more accurate than previous best. */
protected boolean isBetterLocation(Location location,
                                   Location currentBestLocation) {
    if (currentBestLocation == null) {
        // A new location is always better than no location
        return true;
    }

    // Check whether the new location fix is newer or older
    long timeDelta = location.getTime() - currentBestLocation.getTime();
    boolean isSignificantlyNewer = timeDelta > ONE_MINUTE;
    boolean isSignificantlyOlder = timeDelta < -ONE_MINUTE;
    boolean isNewer = timeDelta > 0;

    // If it's been >  1 min since the current location, use the new location
    if (isSignificantlyNewer) {
        return true;
    // If the new location is more than one minute older, it must be worse
    } else if (isSignificantlyOlder) {
        return false;
    }

    // Check whether the new location fix is more or less accurate
    int accuracyDelta = (int) (location.getAccuracy() -
                        currentBestLocation.getAccuracy());
    boolean isLessAccurate = accuracyDelta > 0;
    boolean isMoreAccurate = accuracyDelta < 0;
    boolean isSignificantlyLessAccurate = accuracyDelta > 200;

    // Check if the old and new location are from the same provider
    boolean isFromSameProvider = isSameProvider(location.getProvider(),
            currentBestLocation.getProvider());

    // Determine location quality using a combination of timeliness and accuracy
    if (isMoreAccurate) {
        return true;
    } else if (isNewer && !isLessAccurate) {
        return true;
    } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
        return true;
    }
    return false;
}

/* Determines if two different location services providers are the same */
private boolean isSameProvider(String provider1, String provider2) {
    if (provider1 == null) {
        return provider2 == null;
    }
    return provider1.equals(provider2);
}
