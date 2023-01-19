mvn clean install
docker build -t participant .

ACR_REGISTRY=b3evtgriddemocr.azurecr.io
ACR_IMAGE_TAG=1.8.0
docker tag participant:latest $ACR_REGISTRY/participant:$ACR_IMAGE_TAG
docker push $ACR_REGISTRY/participant:$ACR_IMAGE_TAG