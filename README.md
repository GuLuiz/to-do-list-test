# To-Do List - Full Stack (Angular + Spring Boot + PostgreSQL)

Este projeto é uma aplicação web de gerenciamento de tarefas (**To-Do List**) desenvolvida com **Angular 19 (standalone)** no front-end e **Spring Boot** no back-end. O banco de dados utilizado é o **PostgreSQL**. Toda a aplicação pode ser executada utilizando **Docker**.

## 🚀 Tecnologias Utilizadas

- **Frontend:** Angular 19 + SCSS
- **Backend:** Spring Boot + Java 17
- **Banco de Dados:** PostgreSQL
- **Docker:** Para conteinerização da aplicação

---

## 📥 Como baixar e rodar o projeto

### 1️⃣ **Pré-requisitos**
Antes de iniciar, você precisa ter instalado:
- **Git** ([Download](https://git-scm.com/))
- **Docker** ([Download](https://www.docker.com/))

### 2️⃣ **Clonar o repositório**
Abra um terminal e execute:

```sh
# Clonar o repositório do projeto
git clone https://github.com/seu-usuario/seu-repositorio.git

# Acesse a pasta do projeto
cd seu-repositorio
```

### 3️⃣ **Criar a Rede Docker**
Antes de subir os contêineres, crie a rede Docker para permitir a comunicação entre eles:
```sh
docker network create net-todolist
```

### 4️⃣ **Rodar o Banco de Dados (PostgreSQL)**
Execute o comando abaixo para iniciar o banco de dados no Docker:
```sh
docker run -d --network net-todolist --name dbtodolist \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=1533 \
  -e POSTGRES_DB=todolist \
  -p 5432:5432 postgres:17.3
```

### 5️⃣ **Construir e Rodar o Backend**
#### 5.1 Construir a Imagem Docker do Backend
```sh
docker build -t backendtodolist -f Dockerfile.backend .
```
#### 5.2 Rodar o Backend
```sh
docker run -d --network net-todolist --name backendtodolist -p 8080:8080 backendtodolist
```

### 6️⃣ **Construir e Rodar o Frontend**
#### 6.1 Construir a Imagem Docker do Frontend
```sh
docker build -t frontendtodolist -f Dockerfile.frontend .
```
#### 6.2 Rodar o Frontend
```sh
docker run -d --network net-todolist --name frontendtodolist -p 4200:4200 frontendtodolist
```

---

## ✅ **Testando a Aplicação**
Agora que todos os contêineres estão rodando, você pode acessar os seguintes serviços:

- **Backend (Spring Boot):** [http://localhost:8080](http://localhost:8080)
- **Frontend (Angular):** [http://localhost:4200](http://localhost:4200)
- **Banco de Dados (PostgreSQL):**
  - Para acessar via terminal:
    ```sh
    docker exec -it dbtodolist psql -U postgres -d todolist
    ```

---

## 🛠 **Gerenciando os Contêineres**

### **Parar todos os contêineres**
```sh
docker stop dbtodolist backendtodolist frontendtodolist
```

### **Remover todos os contêineres**
```sh
docker rm dbtodolist backendtodolist frontendtodolist
```

### **Remover as imagens Docker**
```sh
docker rmi backendtodolist frontendtodolist
```

### **Verificar logs de um contêiner**
```sh
docker logs backendtodolist
```

### **Acessar um contêiner**
```sh
docker exec -it backendtodolist sh
```

---

## 📌 **Conclusão**
Agora você tem um ambiente completo para rodar sua aplicação de To-Do List com **Docker**! 🚀 Caso tenha dúvidas ou precise de melhorias, sinta-se à vontade para contribuir.

---

### 📧 **Contato**
Se precisar de ajuda, entre em contato:
- ✉️ Email: [seuemail@email.com](mailto:seuemail@email.com)
- 🔗 LinkedIn: [linkedin.com/in/seu-perfil](https://linkedin.com/in/seu-perfil)
