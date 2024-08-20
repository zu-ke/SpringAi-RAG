# Spring AI RAG 知识库
> Spring AI是一个强大的框架，它简化了AI和机器学习模型在Java应用程序中的集成。本项目基于Openai api开发。

## 项目功能
- 基本对话、流式对话（文生文）；
- 文生图
- 文转声音
- FunctionCall
- 声音转文字
- 支持上传文件、zip压缩包自动解压向量化存储
- RAG检索增强、知识库
- 多模态对话
- 历史消息记录(内存、数据库)
- 对接Ollama的开源大模型

## 技术栈
- SpringBoot3
- SpringAI1.0.0-SNAPSHOT
- postgresql数据库

docker部署postgresql
```yaml
docker run --name my-postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
```