# Getting Started

1. Clone the repository
```
git clone https://github.com/loktev-d/alphabank-test
 ```
2. Go to repo folder
```
cd ./alphabank-test
```
3. Set your API keys in `./src/main/resources/application.yaml` or use mine by default
4. Build docker image
```
docker build -t alphabank-test:latest .
```
5. Run docker image
```
docker run -d -p 8080:8080 alphabank-test:latest
```
6. Go to `localhost:8080/api?code=<YOUR_CURRENCY_CODE>` or just `localhost:8080/api` (RUB currency code is used by default) in your browser
7. Done!
