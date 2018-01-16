## spider center

### progress

- service register ![Progress](http://progressed.io/bar/99)   

- config center ![Progress](http://progressed.io/bar/1?title=init)   
- service ![Progress](http://progressed.io/bar/10?title=framework) 
    - provider ![Progress](http://progressed.io/bar/35?title=ing...)
    
    - consumer ![Progress](http://progressed.io/bar/75?title=init)   
    

### 架构
![图呢](z-resources/imgs/我是图.png)

### 消息格式约定

#### task

``` java

    meta: Map<String, String>

```
### host&ports

- localhost:
    - 8100: register center
    - 820x: service provider: crawler engine
    - 830x: service consumer: scheduler