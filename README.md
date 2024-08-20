# Spring AI RAG 知识库
> Spring AI是一个强大的框架，它简化了AI和机器学习模型在Java应用程序中的集成。本项目基于Openai api开发。

## 项目简介
基于Spring AI和OpenAI API开发的综合性知识库系统。
它旨在展示Spring AI框架的强大功能，并提供一个实用的RAG（检索增强生成）解决方案。
该项目集成了多种AI功能，包括自然语言处理、图像生成、语音处理等，
同时还实现了知识库的建立和检索增强。
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