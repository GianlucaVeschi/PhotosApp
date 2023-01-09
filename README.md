# PhotosApp

# StockPricesTrackerCompose

This project showcases an app which displays a list of images from the public api ```https://jsonplaceholder.typicode.com```.

Things I am satisfied with
- Architecture: The overall architecture is based on MVVM, so that the complexity of retrieving data from an API and displaying it in the UI is distributed among different layers.
- Navigation: The UI is rendered using Jetpack Compose, so technically there is no need to use fragments and the XML navigation style, but IMO the declarative navigation system based on "screens" is still confusing and not as intuitive as the one leveraging the XML nav_graph.

Things I am NOT so satisfied with
- Two core ViewModel tests are failing and I couldn't find out why, more information is contained in their respective classes.
- The API responses are wrapped in a sealed class, but the errors are simply logged and not really handled or passed to the UI.
- Didn't implement a "Loading" state.


# Tech Stack
- Kotlin
- Flow & Coroutines
- Jetpack Compose
- Constraint Layout
- OkHttp 
- Retrofit
- Mockk
- Dagger Hilt
- Timber

# ToDos & Workflow
## Completed
- ~~Create Data Layer~~
- ~~Create Domain Layer~~
- ~~Create Presentation Layer~~

- ~~Add Fragments as backbone to Screens~~
- ~~Add Navigation~~
- ~~Create Detail Screen~~
- ~~Navigate to Detail Screen~~
- ~~Add Error Screen~~

- ~~Handle Airplane mode / Check connectivity~~
- ~~Wrap api responses~~

- ~~Test ViewModels~~
- ~~Test Repository~~
- ~~Test UseCase~~

# Resources & Credits

- How to handle connectivity in Android https://www.youtube.com/watch?v=TzV0oCRDNfM&t=281s

