import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinPluginVersion = "1.6.0"
	kotlin("jvm") version kotlinPluginVersion
	kotlin("plugin.jpa") version kotlinPluginVersion
	kotlin("plugin.spring") version kotlinPluginVersion

	id("org.springframework.boot") version "2.6.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "io.juliodias"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.hibernate:hibernate-spatial:5.3.1.Final")
	implementation("com.bedatadriven:jackson-datatype-jts:2.2")
	runtimeOnly("org.postgresql:postgresql")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
