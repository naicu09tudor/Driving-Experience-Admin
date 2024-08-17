# Driving-Experience-Admin

## Driving Experience Dashboard

The "Driving Experience" web application provides users with an intuitive interface to enroll in driving courses offered by the academy. The application supports multiple roles with the following features:

- **For Students:** Enrollment in courses proposed by instructors.
- **For Instructors:** Creation of new driving courses.
- **For Admins:** Administration and management of the application.

## Technical Architecture

The application is built using a multi-layered architecture, as depicted in the attached diagram:

- **Core:** Developed with Java 17, Spring Framework, and Spring Boot.
- **Model Layer:** Utilizes Spring Data JPA for data persistence.
- **Business Layer:** Implements business logic using Spring REST services.
- **Front-End Layer:** Built with Angular 14.
- **Security:** Secured with Spring Security and JWT (JSON Web Token).
- **Database:** Uses MySQL for relational data management.
