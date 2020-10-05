# NavigationPractice
안드로이드 navigation 연습 프로젝트

## set up
```
def nav_version = "2.3.0"
 // Kotlin
  implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
  implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

  // Feature module Support
  implementation "androidx.navigation:navigation-dynamic-features-fragment:$nav_version"

  // Testing Navigation
  androidTestImplementation "androidx.navigation:navigation-testing:$nav_version"

```
- safe args
```
      def nav_version = "2.3.0"
      classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
      
      apply plugin: "androidx.navigation.safeargs.kotlin"
```

## activity_main.xml
- android:name 속성은 NavHost 구현의 클래스 이름을 포함합니다.
- app:navGraph 속성은 NavHostFragment를 탐색 그래프와 연결합니다.
탐색 그래프는 사용자가 이동할 수 있는 이 NavHostFragment의 모든 대상을 지정합니다.
- app:defaultNavHost="true" 속성을 사용하면 NavHostFragment에서 시스템 뒤로 버튼을 차단합니다. 하나의 NavHost만 기본값으로 지정할 수 있습니다.
동일한 레이아웃에 여러 개의 호스트가 있다면(예: 창 두 개 레이아웃) 하나만 기본 NavHost로 지정해야 합니다.


## navController 가져오기
- Fragment.findNavController()
- View.findNavController()
- Activity.findNavController(viewId: Int)

## 다른 그래프 참조
```
    <include app:graph="@navigation/nav_graph2" />
```

## 전역 작업
```
    <navigation>
        <fragment
        android:id="@+id/mainGlobalFragment2"
        android:name="com.wpfl5.navigationpractice.graph1.MainGlobalFragment"
        android:label="fragment_main_global"
        tools:layout="@layout/fragment_main_global" />

      <action android:id="@+id/action_global_mainFragment"
              app:destination="@id/mainGlobalFragment2"/>
    </navigation>
```

## 탐색 및 백 스택
- Android는 방문한 대상을 포함하는 백 스택을 유지
- navigate() 메서드는 호출될 때마다 또 다른 대상을 스택의 맨 위에 놓습니다.
- 위로 또는 뒤로를 탭하면 NavController.navigateUp()과 NavController.popBackStack() 메서드가 각각 호출되어 스택 최상단의 대상을 삭제(또는 팝)합니다.
- NavController.popBackStack() : 백 스택에서 팝한 후 다른 대상이 최상단에 성공적으로 다시 배치되었는지를 나타냄

- a->b->c->a 로 순환하는 fragment가 있을 때 
```
 <action
            android:id="@+id/action_c_to_a"
            app:destination="@id/a"
            app:popUpTo="@id/a"
            app:popUpToInclusive="true"/>
```
- app:popUpTo : navigate() 호출의 일부로 백 스택에서 몇 개의 대상을 팝하도록 탐색 라이브러리에 알려줍니다. 속성값은 스택에 남아 있어야 하는 가장 최근 대상의 ID입니다.
- app:popUpToInclusive : 스택에서 첫 번째 A도 팝하여 효과적으로 스택을 비웁니다.

## deep link
- 생성
```
 val pendingIntent = NavDeepLinkBuilder(context)
        .setGraph(R.navigation.nav_graph)
        .setDestination(R.id.android)
        .setArguments(args)
        .createPendingIntent()
        
-------------------------------------------------------        
 NavController.createDeepLink()
```

## 애니메이션
- Enter: A화면에서 B화면으로 이동할 때, B화면이 사용할 애니메이션. slide_from_right
- Exit: A화면에서 B화면으로 이동할 때, A화면이 사용할 애니메이션. slide_to_left
- Pop Enter: B화면에서 A화면으로 돌아올 때, A화면이 사용할 애니메이션. slide_from_left
- Pop Exit: B화면에서 A화면으로 돌아올 때, B화면이 사용할 애니메이션. slide_to_right

- https://developer.android.com/training/basics/fragments/animate

## NavigationUI
- toolbar
```
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)
```

- bottom layout
```
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
```

## 뒤로가기 처리
```
// This callback will only be called when MyFragment is at least Started.
            val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
                // Handle the back button event
            }         
```

## 참고 자료
- https://developer.android.com/guide/navigation?hl=ko
