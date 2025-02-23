Voici un fichier **README.md** détaillé pour expliquer comment mettre en place et exécuter le projet **Spring2-FraitFormation**.

---

# Spring2-FraitFormation

Projet de déclaration de frais réalisé avec **Spring Boot**, **Thymeleaf**, **Spring Security** et **Spring Webflow**.

## 🛠 Prérequis

Avant de commencer, assurez-vous d'avoir les éléments suivants installés sur votre machine :

- **Java 17** (JDK)
- **Maven** (ou utilisez le script `mvnw` fourni)
- **MySQL** (ou tout autre serveur compatible)
- **Un IDE** comme IntelliJ IDEA ou Eclipse (optionnel)

## 📥 Installation

### 1️⃣ Cloner le dépôt

```sh
git clone https://github.com/AdrienCarles/Spring2-FraitFormation.git
cd Spring2-FraitFormation
```

### 2️⃣ Configurer la base de données

Ce projet utilise **MySQL**. Vous devez créer une base de données et configurer les accès.

#### 🔹 Création de la base de données :

Connectez-vous à MySQL et exécutez :

```sql
CREATE DATABASE spring2_declarationfrais;
```

#### 🔹 Configuration des accès

Modifiez le fichier `src/main/resources/application.properties` si nécessaire :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/spring2_declarationfrais?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
```

> *Assurez-vous que `username` et `password` correspondent à vos identifiants MySQL.*

### 3️⃣ Installation des dépendances

Avec **Maven** :

```sh
mvn clean install
```


### 4️⃣ Exécuter l'application

Lancez l'application avec :

```sh
mvn spring-boot:run
```

L'application est maintenant accessible sur [http://localhost:8080](http://localhost:8080) 🚀.
