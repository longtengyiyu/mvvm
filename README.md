#### 古语云，工欲善其事，必先利其器。一个好的开发框架可以节约很多开发时间，提高开发效率，达到事半功倍的效果。从最早的MVC到后来的MVP，再到现在的MVVM框架,技术一直在更新，我们的技术储备也要一直进步。


#### 公司一直在使用MVP框架开发android,可以说现在的MVP框架已经非常成熟，但是在实际开饭工程中，MVP还是会有很多不便之处。例如，需要写大量的presenter+model,非常消耗体力；异步请求数据回调造成的内存泄漏等等问题。此时，MVVM框架的出现，给开发带来了很多便利。在实际开发中，效率明显提升。于是乎，我手写了一个MVVM开发框架，并且在两个实际开发的项目中使用,非常顺手。


- ### MVVM与MVP对比

##### MVVM:   View: 对应于Activity和XML，负责View的绘制以及与用户交互。 Model: 实体模型。 ViewModel: 负责完成View与Model间的交互，负责业务逻辑。

##### MVP: View: 对应于Activity和XML，负责View的绘制以及与用户的交互。 Model: 依然是实体模型。 Presenter: 负责完成View与Model间的交互和业务逻辑。

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
  
#### 下面，我们使用MVVM框架开发一个天气app
新建一个WeatherListActivity页面继承BaseMVVMActivity（代码已经封装好）关键代码：
```
public class WeatherListActivity extends BaseMVVMActivity<WeatherViewModel, ActivityWeatherListBinding, Weather> {

    @Override
    protected Class<WeatherViewModel> getModelClass() {
        return WeatherViewModel.class;
    }

    @Override
    protected int getContentLayoutRes() {
        return R.layout.activity_weather_list;
    }
}    
```
WeatherModel 代码如下：
```
public class WeatherModel extends AbstractBaseModel {

    public void getWeather(String city, ApiCallback<Weather> callback) {
        executeHTTP(
                API.getInstance().create(ApiStore.class).getWeather(city), new ResponseTransformer(),
                        callback
        );
    }
}
```
WeatherViewModel 代码如下:
```
public class WeatherViewModel extends BaseViewModel<Weather> {

//    public MutableLiveData<Weather> mWeather = new MutableLiveData<>();

    @Override
    protected void prepareModels(Set<AbstractBaseModel> list) {
        super.prepareModels(list);
        list.add(new WeatherModel());
    }

    public void loadWeather(String city){
        getModel(WeatherModel.class).getWeather(city, new ApiCallback<Weather>() {
            @Override
            protected void onSuccess(Weather weather) {
                getData().setValue(weather);
            }

            @Override
            protected void onFailed(String message) {
                getErrorMsg().setValue(message);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
```
直接在WeatherListActivity里调用 
```
 viewModel.loadWeather("上海");
```


