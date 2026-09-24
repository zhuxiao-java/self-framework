# self-framework

一个基于 **Spring Boot 4 + MyBatis-Plus** 的通用后端基础框架，通过分层模块化设计，提供开箱即用的 CRUD 基础能力：统一响应结构、通用分页/条件查询、实体-DTO 映射、自动填充、全局异常处理、分布式锁接口等，帮助业务项目快速搭建标准三层架构。

## 技术栈

| 技术 | 版本 | 说明 |
| --- | --- | --- |
| Java | 17 | 编译/运行版本 |
| Spring Boot | 4.1.0 | 基础框架 |
| MyBatis-Plus | 3.5.15 | ORM 框架（spring-boot4-starter） |
| MapStruct | 1.6.3 | Entity / DTO 映射 |
| Lombok | 1.18.32 | 样板代码简化 |
| Guava | 31.1-jre | 通用工具库 |
| Commons Lang3 / Collections4 | 3.17.0 / 4.4 | 常用工具库 |

## 模块结构

```
self-framework
├── self-framework-model      # 模型层：基础 DTO / Request / Response / 查询条件定义
├── self-framework-util       # 工具层：Stream 流式处理等通用工具
├── self-framework-common     # 公共层：统一响应码、业务异常、锁服务接口
├── self-framework-dao        # 持久层：基础实体、Mapper、MyBatis-Plus 自动配置
├── self-framework-service    # 服务层：通用 CRUD 服务接口与实现、映射基接口
└── self-framework-web        # 接口层：通用 CRUD Controller、全局异常处理
```

### self-framework-model

- `BaseDTO`：基础数据传输对象，包含 `id`、`createTime`、`updateTime`
- `BaseRequest` / `PageRequest` / `RangeRequest`：通用请求参数
- `BaseResponse` / `DataResponse` / `PageResponse`：统一响应结构
- `Query` 枚举：声明式查询条件（eq / like / in / between / ge / le 等 13 种），支持 JSON 序列化
- `SearchQuery` / `SortQuery` / `Sort`：通用搜索与排序参数

### self-framework-common

- `RespInfo` / `CommonResp`：统一响应码定义（成功、增删改查、启用禁用等）
- `BaseException`：业务异常基类，携带响应码
- `LockService`：锁服务接口（`lock(key, time, lockRun)`），内置 `MemoryLock` 内存实现，可扩展 Redis 等分布式实现

### self-framework-dao

- `BaseEntity<S>`：基础实体，主键 `f_id` 自增，`f_create_time` / `f_update_time` 自动填充
- `BaseStatusEntity`：带状态字段的扩展实体，配合 `Status` 常量
- `IBaseMapper<E>`：继承 MyBatis-Plus `BaseMapper` 的基础 Mapper
- `SelfDaoConfiguration`：自动配置（分页插件 `MybatisPlusInterceptor`、字段填充 `SelfMetaObjectHandler`），通过 `AutoConfiguration.imports` 注册，`@ConditionalOnMissingBean` 支持业务侧覆盖
- `QueryWrapperUtil`：QueryWrapper 构建工具

### self-framework-service

- `IBaseCrudService<D>`：通用 CRUD 接口（详情 / 全量查询 / 新增 / 修改 / 删除 / 计数 / 分页）
- `IBaseCrudServiceImpl`：基于 MyBatis-Plus `ServiceImpl` 的通用实现，将 `SearchQuery` / `SortQuery` 列表动态绑定为 `QueryWrapper`
- `IBaseMapping<E, D>`：基于 MapStruct 的 Entity / DTO 双向映射基接口

### self-framework-web

- `IBaseController<D, S>`：通用 REST Controller，内置 `page` / `selectAll` / `save` / `update` / `detail/{id}` / `delete` 六个标准接口
- `ControllerAdvice`：全局异常捕获，将 `BaseException` 与未知异常统一转换为 `BaseResponse`

## 快速开始

业务项目引入 `self-framework-web`（会传递依赖其他模块），按以下步骤接入标准 CRUD：

**1. 定义实体与 Mapper**

```java
public class UserEntity extends BaseEntity<Long> { }

public interface UserMapper extends IBaseMapper<UserEntity> { }
```

**2. 定义 DTO 与映射**

```java
public class UserDTO extends BaseDTO<Long> { }

@Mapper(componentModel = "spring")
public interface UserMapping extends IBaseMapping<UserEntity, UserDTO> { }
```

**3. 实现 Service**

```java
@Service
public class UserServiceImpl extends IBaseCrudServiceImpl<UserDTO, UserEntity, UserMapper, UserMapping>
        implements UserService {
    public UserServiceImpl(UserMapping mapping) {
        super(mapping);
    }
}
```

**4. 实现 Controller**

```java
@RestController
@RequestMapping("/user")
public class UserController extends IBaseController<UserDTO, UserService> {
    public UserController(UserService service) {
        super(service);
    }
}
```

完成后即自动获得分页查询、条件搜索、排序、增删改查等标准接口，请求示例：

```json
POST /user/page
{
  "pageNum": 1,
  "pageSize": 10,
  "queryList": [{ "field": "f_name", "query": "like", "value": "张" }],
  "sortList": [{ "field": "f_create_time", "sort": "desc" }]
}
```

## 响应码

统一响应码定义在 `CommonResp` 中（`S0806` 起），业务响应码建议从 `S0818` 之后继续编排。

| Code | 说明 |
| --- | --- |
| S0806 | 成功 |
| S0807 | 失败 |
| S0808 / S0809 | 保存成功 / 失败 |
| S0810 / S0811 | 修改成功 / 失败 |
| S0812 / S0813 | 删除成功 / 失败 |
| S0814 | 未知异常 |
| S0815 ~ S0818 | 启用 / 禁用 成功、失败 |

## 构建

```bash
mvn clean install
```

要求本地环境为 JDK 17 与 Maven 3.x。

## License

（未声明）
