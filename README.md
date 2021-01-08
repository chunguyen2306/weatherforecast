# Weather Forecast App
Android Weather Forecast app for current location during the last 7 days
- [X] Programming language: Kotlin is required, Java is optional. 
- [X] Design app's architecture (suggest MVVM)
- [X] Apply LiveData mechanism
- [X] UI should look like attachment
- [X] Exception handling
- [X] Caching handling
- [X] Secure Android app from:
- [X] a.Decompile APK
- [X] b.Rooted device
- [X] c.Data transmission via network.
- [X] Solution diagrams for the components, infrastructure design.

## Software development principles, patterns & practices being applied 

### Software development principles 

Development Principles:
Analyze functionalities -> Design App structure -> Development -> Manual Testing -> Improvements and Bug fixing -> Deliver

![Development Principles](/images/development_principles.png)

### Patterns & Practices being applied

![Development Principles](/images/MVVM_architecture.png)

The pattern used in this application is MVVM (Model - View - View Model). MVVM consists of three main components :

#### Model
This class holds the data of the application. It doens't communicate directly with the View. 

#### View
This class represents the UI of the application, completely seperate from any business logic update. It observes the ViewModel.

#### ViewModel
This class acts as a link between the Model and the View. It's responsible for transforming the data from the Model, providing data to the View. It’ll also ask for the data from the Model.

Also, this application apply Android Architecture Components with the use of LiveData and ViewModel

### Code folder structure

![Code Folder Structure](/images/code_folder_structure.png)

#### builder 
This package contains Classes responsible for  

#### constants
This package contains constants values of applications. Such as URL, API Params, ...

#### data 
This package contains data related functionalities. Such as API Fetching Services and Data Repository.

####  |_ api
This package contains API Fetching Services. Including Retrofit Service Provider and Request Service Interface.

####  |_ repository
This package contains classes which act as data providers for other layers, such as View, containing business logics

#### model 
This package contains data models.

####  |_ weatherdata
This package contains WeatherData related models. Used in parsing Json objects from network repsonses.

#### ui
This package contains Activities.

####  |_ adapter
This package contains Adapter for RecyclerView.Adapter implementation.

####  |_ view
This package contains the Activities of the application.

####  |_ viewmodel
This package contains the ViewModels for each activities.

#### util
This package contains utilities functions.

### Libraries and frameworks used

#### Retrofit

Retrofit is a library for Android and Java aiming to make it easier to make network requests.

#### Coroutines

Coroutines is a library to perform asynchronous tasks efficiently.

#### LiveData

LiveData provides an observable and lifecycle-aware data holder class.

#### ViewModel

ViewModel is class that is responsible for preparing and managing data for Activity.

#### Gson

Gson is a library for serializing and deserializing Java objects to (and from) JSON.

### Required steps in order to get the application to run on a local computer

1. Android Studio Version 4.1 or higher, (you can get it via this link : https://developer.android.com/studio/preview).
2. An Android Device or An Android Emulator.
3. A copy of this directory. 
4. Open the <b>weatherforecast</b> directory using Android Studio Version 4.1
5. Waiting for Gradle to Sync then build project. 
