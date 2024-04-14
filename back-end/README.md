# Aplicações back-end do findfoodtosave

### Especificação técnica

* JDK 17
* Maven 3.9.6

### Como executar as aplicações individualmente:

    Incluir no vmArgs o parametro: -Dspring.profiles.active=local

    mvn clean package

### Como executar a solução completa:

    Dentro do diretorio ./infra executar:
    
    docker-compose up --build

### Endpoints:

#### Aplicação de Usuários

**Cadastrar usuário:**

    curl --location 'http://localhost:8081/api/v1/users' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name": "name",
        "email": "email@gmail.com",
        "password": "password"
    }'

**Autenticar usuário:**

    curl --location 'http://localhost:8081/api/v1/login' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "email": "email@gmail.com",
        "password": "password"
    }'

**Recuperar usuário autenticado**

    curl --location 'http://localhost:8081/api/v1/users/me' \
    --header 'Authorization: Bearer token'

#### Aplicação de Lojas

**Criar loja:**

    curl --location 'http://localhost:8084/api/v1/users/{{id_user}}/stores' \
    --header 'Authorization: Bearer token' \
    --header 'Content-Type: application/json' \
    --data '{
        "name": "name",
        "timeOpen": "00:00",
        "timeClose": "00:00",
        "address": {
            "state": "state",
            "city": "city",
            "district": "district",
            "street": "street",
            "number": 99,
            "complement": ""
        }
    }'

**Atualizar loja:**

    curl --location --request PUT 'http://localhost:8084/api/v1/users/{{id_user}}/stores/{{id_store}}' \
    --header 'Authorization: Bearer token' \
    --header 'Content-Type: application/json' \
    --data '{
        "name": "name",
        "timeOpen": "00:00",
        "timeClose": "00:00",
        "address": {
            "state": "state",
            "city": "city",
            "district": "district",
            "street": "street",
            "number": 99,
            "complement": ""
        }
    }'

**Deletar loja:**

    curl --location --request DELETE 'http://localhost:8084/api/v1/users/{{id_user}}/stores/{{id_store}}' \
    --header 'Authorization: Bearer token'

**Recuperar loja do usuário:**

    curl --location 'http://localhost:8084/api/v1/users/{{id_user}}/stores' \
    --header 'Authorization: Bearer token'

**Buscar lojas por endereço:**

    curl --location 'http://localhost:8084/api/v1/stores?state=example&city=example&district=example' \
    --header 'Authorization: Bearer token'

#### Aplicação de Produtos

**Cadastrar produto:**

    curl --location 'http://localhost:8084/api/v1/users/{{id_user}}/stores/{{id_store}}/products' \
    --header 'Authorization: Bearer token' \
    --header 'Content-Type: application/json' \
    --data '{
        "name": "example",
        "expirationDate": "2024-12-31",
        "quantity": 99
    }'

**Atualizar produto:**

    curl --location --request PUT 'http://localhost:8084/api/v1/users/{{id_user}}/stores/{{id_store}}/products/{{id_product}}' \
    --header 'Authorization: Bearer token' \
    --header 'Content-Type: application/json' \
    --data '{
        "name": "example",
        "expirationDate": "2024-12-31",
        "quantity": 99
    }'

**Deletar produto:**

    curl --location --request DELETE 'http://localhost:8084/api/v1/users/{{id_user}}/stores/{{id_store}}/products/{{id_product}}' \
    --header 'Authorization: Bearer token'

**Recuperar produto por id:**

    curl --location 'http://localhost:8084/api/v1/users/{{id_user}}/stores/{{id_store}}/products/{{id_product}}' \
    --header 'Authorization: Bearer token'

**Listar produtos por usuário e loja:**

    curl --location 'http://localhost:8084/api/v1/users/{{id_user}}/stores/{{id_store}}/products' \
    --header 'Authorization: Bearer token'

**Listar produtos por loja:**

    curl --location 'http://localhost:8084/api/v1/stores/{{id_store}}/products' \
    --header 'Authorization: Bearer token'
