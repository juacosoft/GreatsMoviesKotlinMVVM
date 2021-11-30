# GreatMovies Kotlin MVVM

Simple overview of use/purpose.

## Description

Greats Movie es un codigo de ejemplo para prueba tecnica empresa, sus funciones no estan completadas

Requerimientos solicitados


Requerimientos completados (Para Peliculas unicamente)
<ul>
  <Li>Peliculas y/o series categorizadas por Popular y Top Rated )</Li>
  <li>Detalle de pelicula y/o serie </Li>
  <li>Buscador de Pelicula y/o serie por nombre</Li>
  <li>Visualizacion de video en Detalle</Li>  
  <li>Api https://developers.themoviedb.org/</Li>
  <li>Usar Kotlin </Li>
  <li>Bibliotecas usadas (ver Dependencias)</Li>
  <li>Subir proyecto a Girhub</Li>
  <li>La app debe ser Escalable : arquitectura usada MVVM (Model - View - View Model)</Li>
  <li>Injectar Dependencias : Dagger Hilt</Li>
  <li>La app debe funcionar offline</li>
  <li>-Pruebas Unitarias</li>
</ul> 
-Requerimientos no Completados
<ul>
 <li>transiciones /Animaciones entre un listado y detalle y carga inicial de peliculas (Recycler animation)</Li>
 
</ul>


## Desarrollado en java android MVVM, RxAndroid, Dagger Hilt, RxJava , ButterKnife, api desde https://developers.themoviedb.org proceso de consumo de respuestas JSON 

#Consideraciones
Basado en patron de diseño MVVM y programacion reactiva se realizo aplicacion online, la implementacion o sincronizacion de archivos de manera offline realizado 
con persistencia de datos DAO la clase AppDataBase (Extiende de RoomDatabase)
Sincroniza los datos traidos desde la api y estos estarian disponibles para ser injectados en los viewmodels correspondientes 
interface MoviesDao contiene las sentencias SQLite pertinentes para: insercion de datos, consulta y actualizacion

### Dependencies

* Android studio Artic Fox </br>
// Fragment
   * implementation "androidx.fragment:fragment-ktx:1.3.2"
   * implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'
   * implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'
   * // Activity
   * implementation "androidx.activity:activity-ktx:1.2.2"
   * // ViewModel
   * implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
   * // LiveData
   * implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"

    // Retrofit
   * implementation "com.squareup.retrofit2:retrofit:2.9.0"
   * implementation "com.squareup.retrofit2:converter-gson:2.9.0"
   * //Corrutinas
   * implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.6'
   * //daggerhilt
   * implementation "com.google.dagger:hilt-android:$hilt_version"
   * implementation 'androidx.test.ext:junit-ktx:1.1.3'
   * kapt "com.google.dagger:hilt-android-compiler:$hilt_version"

   * //Room Library
   * //implementation "androidx.room:room-runtime:2.2.6"
   * implementation "androidx.room:room-ktx:2.3.0"
   * kapt "androidx.room:room-compiler:2.3.0"
   * kapt "android.arch.persistence.room:compiler:1.0.0-alpha4"

   * //Glide
   * implementation 'com.github.bumptech.glide:glide:4.12.0'
   * annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'

   * //Swipy
   * implementation "com.github.omadahealth:swipy:1.2.3@aar"

   * //ratingbar
   * implementation 'me.zhanghai.android.materialratingbar:library:1.4.0'

   * //youtube player
   * implementation 'com.pierfrancescosoffritti.androidyoutubeplayer:core:10.0.5'


   * testImplementation 'junit:junit:4.+'
   * androidTestImplementation 'androidx.test.ext:junit:1.1.3'
   * androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

   * testImplementation  "com.google.dagger:hilt-android-testing:$hilt_version"
   * testImplementation "org.robolectric:robolectric:4.7.2"
   * kaptTest "com.google.dagger:hilt-compiler:$hilt_version"

   * testImplementation 'com.squareup.okhttp3:mockwebserver:4.9.1'
    
    
### Installing</br>

* Archivo de configuracion de api_key  y url constants.ServerUrls</br>
* Cualquier modificacion requiere actualizacion en este folder</br>

### Descargar Apk de prueba

*<a href= "https://drive.google.com/file/d/1cCrmz-dARQTqAhBxIbuMmdgNaJoO48_P/view?usp=sharing">Descargar Apk</a>


## Joaquin Martinez Marulanda



## Version History

* 0.1
    * Initial Release

## License

Este proyecto fue realizado para prueba practica de programacion en patron de diseño MVVM, RxJava, RXAndroid,Inyeccion de dependencia con Daggerhilyt

## Acknowledgments



