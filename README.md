- ### JetPack

    databinding+LiveData是MVVM+Jetpack进行进行app整体架构设计中非常重要的两个库。

- ### MVVM与MVP

  **MVVM**:   **View: **对应于Activity和XML，负责View的绘制以及与用户交互。 **Model: **实体模型。 **ViewModel: **负责完成View与Model间的交互，负责业务逻辑。

  
  
  **MVP**: **View: **对应于Activity和XML，负责View的绘制以及与用户的交互。 **Model: **依然是实体模型。 **Presenter: **负责完成View与Model间的交互和业务逻辑。
  
- ### ViewDataBinding配置

  app/build.gradle中android节点配置

  ```
  dataBinding {
      enabled = true
  }
  ```

  xml配置layout节点会自动生成对应的dataBinding类，如activity_weather对应生成ActivityWeatherBinding

- ### Model

  ​    通过继承RYBaseModel 实现Model

- ### ViewModel

   通过new ViewModelProvider(this).get(Class<T> modelClass)获取ViewModel

  同一activity加载的多个fragment通过上面的方式创建的ViewModel数据是共享的。

  

  注：多个请求数据可以通过定义LiveData或者MutableLiveData获取

- ### LiveData与MutableLiveData区别

  MutableLiveData是LiveData的子类；LiveData是抽象类（abstract）

  MutableLiveData可以通过postValue()和setValue()更新数据；其中postValue()在子线程执行；setValue()在主线程执行；

  多个数据源可以通过创建多个model及对应的MutableLiveData实现

  ​    

     **ViewModel.Data.observe方法可以回调变更的数据，当且仅当livecycleowner的状态为STARTED和RESUME的时候才可以接收到数据**（如果当前activity进入后台，重新回到前台，仍然会回调observe），这里有一个好处，就是activity被销毁不会因为异步加载数据而造成崩溃；并且LifecycleOwner的状态为DESTROYED时,`observer`会被自动移除。

  

- ### Kotlin Flow

     Flow结合协程可以代替Rxjava在Android中的地位。

- ### 参考文档

     https://tech.meituan.com/2016/11/11/android-mvvm.html

     https://developer.android.com/topic/libraries/data-binding/architecture?hl=zh_cn#java

