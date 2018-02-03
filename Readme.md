## spider center

### progress

- service register ![Progress](http://progressed.io/bar/99)   

- config center ![Progress](http://progressed.io/bar/10?)   
- service ![Progress](http://progressed.io/bar/100?) 
    - scheduler ![Progress](http://progressed.io/bar/100?)
    - engine ![Progress](http://progressed.io/bar/100?)   
        - fetch ![Progress](http://progressed.io/bar/100?)   
        - parse ![Progress](http://progressed.io/bar/100?)   
        - sotrage ![Progress](http://progressed.io/bar/100?)   
    


### 架构
![图呢](z-resources/imgs/我是图.png)

### 约定

#### api

scheduler& engine
``` 
scheduler: /receive?taskJson={TASKJSON} 
-> engine


engine: /receive?taskJson={TASKJSON} 
-> process

-> following tasks: /submit?taskJson={TASKJSON} 
-> scheduler
...

```

#### task

``` java

    meta: Map<String, String>

```


|name|site|source|entity|type|
|----|----|------|------|----|
|LjMobileHouseList|1|2|1|1|
|LjMobileHouseDetail|1|2|1|2|



### host&ports

- localhost:
    - 8100: register center
    - 820x: service provider: crawler engine
    - 830x: service consumer: scheduler
    - 880x: config server