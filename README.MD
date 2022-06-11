# Microsserviços

## Descrição

O projeto acima possui vários microsserviços cada um com um propósito específico. Abaixo há uma simples explicação sobre cada um deles.

### ApiGateway

Este microsserviço simplesmente recebe as requisições do cliente (frontend) e encaminha para algum dos vários microsserviços internos. Ele também cuida da parte de autorização em que um token de usuário pode ou não acessar ceras URLs.
