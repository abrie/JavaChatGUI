all: build-client build-server build-frontend

build-frontend:
	javac frontend/Frontend.java
build-client:
	javac client/ChatClient.java
build-server:
	javac server/ChatServer.java
