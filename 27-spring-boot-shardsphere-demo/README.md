# SpringBoot + ShardSphere 分库分表示例 

---

- [x] 分库：ds-0, ds-1，对应 /resources 下的 ds-0.sql 和 ds-1.sql
- [x] 分表：每个库下都有 t_user, t_blog 两张逻辑表的三个分片表
- [x] 广播表：t_config，在 ds-0 和 ds-1 下具有完全相同的内容
