#!/bin/bash
set -e

# Загрузка переменных из .env
if [ -f .env ]; then
  export $(grep -v '^#' .env | xargs)
fi

# Проверка переменной
if [ -z "$DOCKER_REGISTRY" ]; then
  echo "DOCKER_REGISTRY не задан в .env"
  exit 1
fi

echo "Using DOCKER_REGISTRY: $DOCKER_REGISTRY"

echo "Uninstalling Helm releases..."
for ns in my-bank-app-dev my-bank-app-test my-bank-app-prod; do
  helm uninstall my-bank-app -n "$ns" || true
done

echo "Deleting PVCs and PVs..."
for ns in my-bank-app-dev my-bank-app-test my-bank-app-prod; do
  kubectl delete pvc --all -n "$ns" --ignore-not-found || true
done
kubectl delete pv --all || true

echo "Deleting namespaces..."
kubectl delete ns my-bank-app-dev --ignore-not-found
kubectl delete ns my-bank-app-test --ignore-not-found
kubectl delete ns my-bank-app-prod --ignore-not-found

echo "Shutting down Jenkins..."
docker compose down -v || true
docker stop jenkins && docker rm jenkins || true
docker volume rm jenkins_home || true

echo "Removing images..."
docker image rm ${DOCKER_REGISTRY}/accounts || true
docker image rm ${DOCKER_REGISTRY}/gateway-api || true
docker image rm ${DOCKER_REGISTRY}/cash || true
docker image rm ${DOCKER_REGISTRY}/transfer || true
docker image rm ${DOCKER_REGISTRY}/convert || true
docker image rm ${DOCKER_REGISTRY}/exchange || true
docker image rm ${DOCKER_REGISTRY}/blocker || true
docker image rm ${DOCKER_REGISTRY}/notifications || true
docker image rm ${DOCKER_REGISTRY}/front-ui || true
docker image rm jenkins/jenkins:lts-jdk21 || true

echo "Pruning system..."
docker system prune -af --volumes

echo "Done! All clean."
