plugins {
	java
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.jetbrains.kotlin.jvm") version "1.9.20" apply false // 可选，若用 Kotlin
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenLocal()
	maven("https://maven.aliyun.com/repository/public") // 阿里云镜像
	mavenCentral()
}

dependencies {
	// Spring Boot 核心
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// 数据库
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	runtimeOnly("com.mysql:mysql-connector-j")

	// MyBatis-Plus
	implementation("com.baomidou:mybatis-plus-boot-starter:3.5.5")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// JWT
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	// 测试
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.test {
	useJUnitPlatform()
}