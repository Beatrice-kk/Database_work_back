# Database_work_back

## 项目简介

本项目为"学生成绩管理系统"后端，基于 Spring Boot + MyBatis-Plus + PostgreSQL 实现，支持学生、教师、管理员等多角色的成绩、课程、班级等信息的管理。

## 技术栈

- Java 17+
- Spring Boot
- MyBatis-Plus
- PostgreSQL
- Lombok
- Sa-Token（权限认证）
- Maven

## 主要功能

- 学生、教师、管理员的登录与权限管理
- 学生成绩的录入、查询、统计
- 课程、班级、学院、专业等基础信息管理
- 教师课程表、成绩录入与查询
- 管理员对全校数据的统计与管理
- 支持分页、条件查询、数据导出等功能

## 目录结构

```
back/
├── src/
│   ├── main/
│   │   ├── java/cn/lonesome/sms/
│   │   │   ├── config/         # 配置类
│   │   │   ├── constant/       # 常量
│   │   │   ├── controller/     # 控制器
│   │   │   ├── exception/      # 自定义异常
│   │   │   ├── handler/        # 全局异常处理
│   │   │   ├── interceptor/    # 拦截器
│   │   │   ├── mapper/         # MyBatis-Plus Mapper接口
│   │   │   ├── model/          # DTO/实体类
│   │   │   ├── service/        # 业务逻辑
│   │   │   ├── utils/          # 工具类
│   │   │   └── SMSApplication.java # 启动类
│   │   ├── resources/
│   │   │   ├── application.yml # 主配置文件
│   │   │   ├── logback.xml     # 日志配置
│   │   │   └── ...             # 其他资源
│   └── test/                   # 测试代码
├── log/                        # 日志文件
├── pom.xml                     # Maven依赖
└── README.md                   # 项目说明
```

## 启动与开发

1. **环境准备**
   - JDK 17 及以上
   - PostgreSQL 数据库
   - Maven 3.6+

2. **数据库配置**
   - 修改 `src/main/resources/application.yml`，配置数据库连接信息
   - 初始化数据库表结构（可根据实体类或SQL脚本）

3. **启动项目**
   ```bash
   mvn clean spring-boot:run
   ```
   或使用 IDE 运行 `SMSApplication.java`

4. **接口文档**
   - 推荐使用 Postman 或 Swagger 进行接口调试（如有集成Swagger，可访问 `/swagger-ui.html`）

## 常见问题

- **端口冲突**：默认端口为 8888，如需修改请在 `application.yml` 中调整。
- **数据库连接失败**：请检查数据库配置、网络和账号密码。
- **依赖问题**：请确保 Maven 能正常下载依赖。

## 联系方式

如有问题或建议，请联系作者：  
- 邮箱：wk-chen@qq.com

---

> 本项目仅供学习交流，禁止用于商业用途。
