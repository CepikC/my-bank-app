Приложения my-bank-app

для запуска приложения
mvn clean package
docker-compose up

для входа
login: user1
password: password1

Включите Kubernetes в Docker Desktop

Переключитесь на контекст docker-desktop
kubectl config use-context docker-desktop

Создайте файл jenkins/.env
KUBECONFIG_PATH=/путь/к/вашему/jenkins_kubeconfig.yaml
GHCR_TOKEN=your_github_token
GITHUB_USERNAME=your_github_username
GITHUB_TOKEN=your_github_token
GITHUB_REPOSITORY=your_username/bankapp
DOCKER_REGISTRY=ghcr.io/your_username
DB_PASSWORD=postgres

Создайте файл jenkins_kubeconfig.yaml
cp ~/.kube/config jenkins_kubeconfig.yaml
В созданном файле добавить строки server и insecure-skip-tls-verify:
clusters:
  cluster:
    server: https://host.docker.internal:6443
    insecure-skip-tls-verify: true
  name: docker-desktop

Запустите Jenkins
cd jenkins
docker-compose up -d
http://localhost:8080

новая ветка