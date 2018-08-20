# 犯罪记录管理系统服务端
crms是个java工程，通过spring mvc提供的rest api向外提供服务接口。

##  一、组件的工程目录结构


```
crms-workspace/                        - 业务子系统的根目录
	├─ gradle/                         - gradle工作目录，存放一些gradle命令和gradle-wrapper包。
	├─ .idea/                          - idea工作目录，存放了idea项目的一些描述文件等。
	├─ .build/                         - gradle构建结果的输出目录。
	├─ .gitgnore                       - git忽略文件
	├─ docs                            - 项目相关文档
	├─ scripts                         - 通用脚本组件(git,publish,sonar,jacoco...)
	├─ crms-backend                    - 工程宿主程序
	├─ crms-applycertify-contract      - crms-applycertify-contract业务模块契约，包含服务和数据契约
	├─ crms-applycertify-service       - crms-applycertify-service业务模块服务实现，含此业务模块对应的datamapper,entity,repository,serviceimpl
	├─ crms-applycertify-webapi        - crms-applycertify-webapi业务模块webapi-controller
	├─ crms-crimenotice-contract       - crms-crimenotice-contract业务模块契约，包含服务和数据契约
    ├─ crms-crimenotice-service        - crms-crimenotice-service业务模块服务实现，含此业务模块对应的datamapper,entity,repository,serviceimpl
    ├─ crms-crimenotice-webapi         - crms-crimenotice-webapi业务模块webapi-controller
    ├─ crms-systemmanager-contract     - crms-systemmanager-contract业务模块契约，包含服务和数据契约
    ├─ crms-systemmanager-service      - crms-systemmanager-service业务模块服务实现，含此业务模块对应的datamapper,entity,repository,serviceimpl
    ├─ crms-systemmanager-webapi       - crms-systemmanager-webapi业务模块webapi-controller
	├─ crms-common                     - crms-common系统公共模块
	├─ gradlew                         - Gradle start up script for UN*X
	├─ gradlew.bat                     - Gradle startup script for Windows
	├─ build.gradle                    - gradle构建脚本
```

#### 1.contract工程结构
```
	src/                                                                              - 源代码目录（该目录中的目录结构是由gradle java plugin约定的）
	 ├─main/                                                                          - *实现代码*
	 |  ├─java/                                                                       - java代码
	 |  |  ├─com/gs/crms/webapi/contract/model                                   - dto
	 |  |  ├─com/gs/crms/webapi/contract/service                                 - service interface
	 |  |
	 |  ├─resources/                                                                  - 静态资源文件(以平级的方式存放*.properties文件，不分文件夹)
	 ├─test/                                                                          - 测试代码
	 |  ├─java/                                                                       - java代码（应该一个Controller类，就对应一个测试类）
	 |  |  └─***Test.java                                                             - 测试代码文件
	 |  └─resources/                                                                  - 静态资源文件和配置文件
```

#### 2. service工程结构
```
	src/                                                                              - 源代码目录（该目录中的目录结构是由gradle java plugin约定的）
	 ├─main/                                                                          - *实现代码*
	 |  ├─java/                                                                       - java代码
	 |  |  ├─com/gs/crms/service/entity                                          - 领域对象
	 |  |  ├─com/gs/crms/service/repository                                      - 数据访问层
	 |  |  ├─com/gs/crms/service/datamapper                                      - 实体<-->dto关系映射
	 |  |  ├─com/gs/crms/service/serviceimpl                                     - 服务实现层
	 |  └─resources/                                                                  - 静态资源文件
	 ├─test/                                                                          - 测试代码
	 |  ├─java/                                                                       - java代码
	 |  |  └─***Test.java                                                             - 测试代码文件
	 |  └─resources/                                                                  - 静态资源文件和配置文件
```

##### 在services中按照package划分为datamapper、entity、repository和serviceimpl ：
* datamapper用于数据实体entity和数据契约dto的转换。
* entity为数据映射实体
* repository为数据存储仓储服务，负责数据的CRUD操作。
* serviceimpl中定义业务逻辑实现类。

#### 3. webApi工程结构
```
	src/                                                                              - 源代码目录（该目录中的目录结构是由gradle java plugin约定的）
	 ├─main/                                                                          - *实现代码*
	 |  ├─java/                                                                       - java代码
	 |  |  ├─com/gs/crms/webapi/controller                                       - webapi
	 |  |
	 |  ├─resources/                                                                  - 静态资源文件(以平级的方式存放*.properties文件，不分文件夹)
	 ├─test/                                                                          - 测试代码
	 |  ├─java/                                                                       - java代码（应该一个Controller类，就对应一个测试类）
	 |  |  └─***Test.java                                                             - 测试代码文件
	 |  └─resources/                                                                  - 静态资源文件和配置文件
```





  