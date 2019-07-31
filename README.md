# mapflux-manager
mapflux-manager

## 项目架构
本项目采用 spring cloud 微服务架构，其中包含以下模块：
- mapflux-basic-data：负责基础数据相关操作，即shp、gdb、pg等文件的管理
- mapflux-vector-data：负责各种矢量数据发布，即根据shp、gdb、pg等基础数据，选择在线切片、缓存切片等方式，选择图层，字段，生成geojson的过程
- mapflux-map-data：负责地图数据的发布，即根根据矢量数据生成stuylejson的过程
- mapflux-getway：微服务网关
- mapflux-oauth：微服务认证授权服务
- mapflux-user：用户管理服务
- mapflux-model：存放pojo
- mapflux-common：存放公共类，例如工具类等
- mapflux-eureka：注册中心
- mapflux-parent：pom项目，提供模块统一管理


项目技术选型：
- spring boot ：2.1.6.RELEASE
- pgsql：9.6
- eureka：2.1.6.RELEASE

## 项目说明
本项目是一个地图文件，上传即服务发布的系统，本项目完全使用sping cloud来实现微服务，值得注意的是分布式的事务该如何实现，这个需要后期考量，其次就是地图数据中的图层与字段数据量可能极大，其效率需要考证，必要时可以引入ELK。
