## spider center

### progress

- service register ![Progress](http://progressed.io/bar/99)   

- config center ![Progress](http://progressed.io/bar/1?title=init)   
- service ![Progress](http://progressed.io/bar/10?title=framework) 
    - provider ![Progress](http://progressed.io/bar/35?title=ing...)
    
    - consumer ![Progress](http://progressed.io/bar/75?title=init)   
    

### 架构
![图呢](z-resources/imgs/我是图.png)

### 约定

#### api
scheduler:
```
接收任务: 
post /crawlTask
body:
task json str
```


#### task

``` java

    meta: Map<String, String>

```



### host&ports

- localhost:
    - 8100: register center
    - 820x: service provider: crawler engine
    - 830x: service consumer: scheduler