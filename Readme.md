## spider center

### progress

- service register ![Progress](http://progressed.io/bar/99)   

- config center ![Progress](http://progressed.io/bar/1?)   
- service ![Progress](http://progressed.io/bar/75?) 
    - scheduler ![Progress](http://progressed.io/bar/70?)
    - engine ![Progress](http://progressed.io/bar/80?)   
        - fetch ![Progress](http://progressed.io/bar/100?)   
        - parse ![Progress](http://progressed.io/bar/50?)   
        - sotrage ![Progress](http://progressed.io/bar/0?)   
    


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



### host&ports

- localhost:
    - 8100: register center
    - 820x: service provider: crawler engine
    - 830x: service consumer: scheduler