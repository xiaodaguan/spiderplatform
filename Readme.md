## spider center

### progress

- service register ![Progress](http://progressed.io/bar/50)   

- config center ![Progress](http://progressed.io/bar/5?title=init)   
- service ![Progress](http://progressed.io/bar/10?title=framework) 
    - provider ![Progress](http://progressed.io/bar/5?title=ing...)
    
        提交任务
    - consumer ![Progress](http://progressed.io/bar/5?title=init)   
    
        接收任务成功

### 架构
![图呢](resources/imgs/我是图.png)

### 消息格式约定

#### task

### host&ports

- localhost:
    - 8100: register center
    - 820x: service provider: crawler engine
    - 830x: service consumer: scheduler