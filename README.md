# MobileHub — Java + Maven web booking starter

This is a responsive, browser-accessible phone catalog and booking-enquiry starter built with Java 21, Spring Boot, Maven, and Thymeleaf. It is a web app, not a native Android APK.

## Project structure

```text
mobilehub/
├── pom.xml
├── Dockerfile
├── .dockerignore
└── src/
    └── main/
        ├── java/com/mobilehub/
        │   ├── MobileHubApplication.java
        │   ├── Phone.java
        │   ├── PhoneService.java
        │   ├── BookingRequest.java
        │   ├── CatalogController.java
        │   └── BookingController.java
        └── resources/
            ├── application.properties
            ├── templates/
            │   ├── index.html
            │   ├── booking.html
            │   └── confirmation.html
            └── static/
                ├── css/styles.css
                └── js/app.js
```

## Run locally

Requirements: JDK 21 and Maven 3.9+.

```bash
mvn clean test
mvn spring-boot:run
```

Open `http://localhost:8080`. Health check: `http://localhost:8080/actuator/health`.

## Build and run with Docker

From the project root:

```bash
docker build -t mobilehub:1.0 .
docker run -d --name mobilehub \
  --restart unless-stopped \
  -p 8080:8080 \
  mobilehub:1.0
```

Then open `http://YOUR_EC2_PUBLIC_IP:8080`. In the EC2 security group, allow inbound TCP 8080 only from your testing IP while testing. For production, prefer HTTPS through a reverse proxy or load balancer and restrict direct access to the app port.

View logs and stop the container:

```bash
docker logs -f mobilehub
docker stop mobilehub
```

## Important production checklist

- Phone data/prices are sample data in `PhoneService`; verify or replace them with an authorized live catalog.
- Booking submissions currently show a confirmation page only. No personal data is stored or sent. Connect a database and secure follow-up workflow before collecting real enquiries.
- Use HTTPS, a domain, managed secrets, database backups, monitoring, rate limiting/WAF as appropriate, and a privacy/retention policy before public production.
- Review security, accessibility, dependency versions, and legal/privacy obligations before launch.
- If running behind HTTPS, set `COOKIE_SECURE=true`.
- External Google Fonts are used as a visual enhancement; local/system fallback fonts remain available if they cannot load.
