# Business Finder

An Android app that uses the [Yelp API](https://www.yelp.com/developers) to find business at a specified location.

Business Finder is a single page app. User can search for a particular business by entering a name or enter a geneic term at a specified location/neighbourhood. 
Once the results are displayed, user can view details of any business.

## Screenshots

<p float="left">
  <img src="https://user-images.githubusercontent.com/89879294/145737830-3b308027-c132-428a-ad7b-328fa23f434a.png" width="300" height="600"/> 
  &nbsp &nbsp &nbsp &nbsp
     <img src="https://user-images.githubusercontent.com/89879294/145737946-ea7e4f95-5085-4d44-ae32-1efbb2dac35e.png" width="300" height="600"/>
    &nbsp &nbsp &nbsp &nbsp
     <img src="https://user-images.githubusercontent.com/89879294/145738009-64c764e0-8bfa-4332-a466-17fc706307bc.png" width="300" height="600" />
     &nbsp &nbsp &nbsp &nbsp
    <img src="https://user-images.githubusercontent.com/89879294/145738117-d7c04f5e-ae32-42fd-be56-3c144b8257d1.png" width="300" height="600"/>
   
</p>


## Overview

The app does the following:
1. User enters a optional search term (Salon, Restaurants, Pizza) and a mandatory location(Toronto,Montreal).
2. Fetch the business data asynchronously from [Yelp Business search API](https://api.yelp.com/v3/businesses/search).
3. Display the scrollable list of businesses grouped under categories in a sorted mannerin a nested RecyclerView.
4. A business could appear in multiple categories based on data from the API.
5. User can further view details of a business including name, rating, categories and image by clicking on a business name.
6. In case no response is returned, a message is displayed.

## Building the Business Finder App

First, clone the repo:

`https://github.com/Sahana2502/businessFinder/`

### Android Studio 

(The app was built using Android Studio and tested on Android P)

* Open Android Studio and select `File->Open...` or from the Android Launcher select `Import project (Eclipse ADT, Gradle, etc.)` and navigate to the root directory of your project.
* Select the directory
* Click 'OK' to open the the project in Android Studio.
* A Gradle sync should start, or force a sync and build the 'app' module as needed.

### Gradle (command line)

* Build the APK: `./gradlew build`

## Running the App

Connect an Android device to your desktop/laptop.

### Android Studio

* Select `Run -> Run 'app'` (or `Debug 'app'`) from the menu bar
* Select the device you wish to run the app on and click 'OK'

## Libraries

This app leverages the following third-party library:

 * [Glide](https://github.com/bumptech/glide) - For displaying image under business details.
 * [Retrofit](https://square.github.io/retrofit/) - Http client to fetch API data.

