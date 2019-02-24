# Kite
A networking library based on OkHttp3 with lifecycle aware component and coroutines for better multiprocessing.

<b>To add kite to your project add following library</b>
<pre>
  In project level gradle add jitpack.io
   repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
        
    }
    
    allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
    
   In app gradle add following library
   dependencies {
	        implementation 'com.github.raipankaj:Kite:1.0.0'
	}
}
</pre>

Kite is an advance version of networking library powered by Android architecture component and coroutines. It uses OkHttp3 under the hood to perform all network calls but because of architecture component this give you the lifecycle awareness elimates worry about performing network call in a background thread or getting result from the network when activity or fragment is in onResume state.

*This is still an early version of this library and it can have major changes as it graduate to stable version*
