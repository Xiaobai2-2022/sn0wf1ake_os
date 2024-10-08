# Sn0wf1ake VM

Sn0wf1ake VM is a virtual machine system designed, implemented by Fangxia Technology Ltd., the virtual machine system is used to simulate Linux-liked system which is used online.

## Table of Contents

- [About the Project](#about-this-project)
  - [Build Tools](#build-tools)
  - [Tools Installation](#tools-installation)

## About this Project

### Build Tools

- Frontend:
  - [React/TS](https://react.dev/)

- Backend:
  - [Java Spring](https://spring.io/)

- DBMS:
  - [MySQL](https://www.mysql.com/)

### Tools installation

The system is built on a WSL Machine with the tools installed by the following commands:

```shell
# Update System
sudo apt update

# Install Java Development Kit 17
sudo apt install openjdk-17-jdk

# Install MySQL Server
sudo apt install mysql-server

# Install Node.js, npm
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.4/install.sh | bash
source ~/.bashrc
nvm install 20
nvm use 20
nvm alias defult 20

# Verify Java Installation
java -version

# Verify Node.js Installation
node -v

# Verify npx Installation
npx -v

```

