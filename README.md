# 动物 规则 识别（概率 匹配） 初议

Enum：{[规则，规则...], 结论, 是否结论可当作规则}

ForwardResult: [{规则, 结果， 匹配概率}, {规则, 结果， 匹配概率},...]
``` 
    输出 依据 匹配概率最大的 Result 
```

ReverseResult：{规则，...}

---
Implements:
```
    // TODO 正向
    requestParams: [规则,......] 
        >>> 匹配Enum 用于确定 tempParams
    tempParams: [规则，......]
        >>> 匹配Enum 
            >>> 计算概率 
                >>> 输出 Result Array
    Result：[]
        >>> 最大概率 确定结果
    
    // TODO 逆向
    requestParams: '结论'
        >>> 匹配Enum 输出 isRequestByResultEnum (结论可当作规则)
    isRequestByResultEnum
        >>> 匹配Enum 
            >>> 输出 Result
    Result：{} 
        >>> 结果

```

---
* 重点
```
    1：已知规则，不需要像网上案例，一个一个问地形式 逆向；
    2：正向是通过 不同随机 问题 计算概率 得出的  更接近 随机概率识别 而非 网上规定问题试识别
```








