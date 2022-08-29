Protobuf，类似于json和xml，是一种序列化结构数据机制，可以用于数据通讯等场景，相对于xml而言更小，相对于json而言解析更快。下面学习下protobuf在安卓中的应用。

根目录build.gradle中buildscript配置protobuf插件依赖

```
buildscript {
  dependencies {
    classpath 'com.google.protobuf:protobuf-gradle-plugin:0.8.19'
  }
}
```

app目录build.gradle中应用插件 

```
plugins {
  id 'com.google.protobuf'
}
```

android节点添加

```
protobuf {
        protoc {
            artifact = 'com.google.protobuf:protoc:3.21.5'
        }

        generateProtoTasks {
            all().each { task ->
                task.builtins {
                    remove java
                }
                task.builtins {
                    java {}
//                cpp { }
                }
            }
        }
    }
```

添加依赖如下：

```
dependencies {
  implementation 'com.google.protobuf:protobuf-java:3.21.5'
  implementation 'com.google.protobuf:protoc:3.21.5'
}
```

File -> Setting -> Plugins 搜索安装Protobuf后重启AS IDE



app -> src -> main -> proto创建proto文件

```
//包名
package user;
//指定生成类所在的Java包名
option java_package = 'com.tangtang.proto';

message User{
    required string id = 1;
    optional string user_name = 2;
    optional int32 gender = 3;
    optional Grade grade = 4;
}
```

创建完成后点击Build -> Make Project生成java代码，基本调用如下：

```
UserOuterClass.User user = UserOuterClass.User.newBuilder()
                .setId("1")
                .setGender(0)
                .setUserName("Lily")
                .setGrade(UserOuterClass.Grade.GRADE_ONE)
                .build();
```

