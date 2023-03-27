# Autogarage-BE

**Installatiehandleiding:**

### Lijst van benodigdheden:

1.	De applicatie maakt gebruik van Java versie 17 en Spring Boot versie 2.7.5.
2.	De applicatie maakt gebruik van Spring Boot framework en Maven.
3.	De applicatie maakt gebruik van een PostgreSQL database.
4.	Authorisatie is verplicht d.m.v. Bearer(JWT) token.  Deze is ook te vinden in de lijst met endpoints.
5.	De endpoints van de applicatie kunnen gedraaid worden in Postman. Een lijst met export van de endpoints voor Postman is ook in het Github-project toegevoegd in de feature branch. 
In Postman kun je deze openen d.m.v. file -> import -> vervolgens het Postman-bestand kiezen.
 
### Stappenplan installatie:


•	Deze applicatie is te runnen via IntelliJ van Jetbrains. Je kunt hier: https://www.jetbrains.com/idea/download/#section=windows de Community Edition downloaden.

•	De applicatie maakt verder gebruik van een database van PostgreSQL. Deze kun je hier: https://www.postgresql.org/download/ downloaden.

•	Verder maakt deze applicatie gebruik van postman om je endpoints te runnen. Deze kun je hier: https://www.postman.com/downloads/ downloaden. 

•	Zorg ervoor dat je IntelliJ, PostgreSql en Postman hebt geïnstalleerd.  
Voor het instaleren van PostgreSQL kun je ook deze korte video bekijken voor windows: https://www.youtube.com/watch?v=1q5-8O7WvZs&list=PLwvrYc43l1MxAEOI_KwGe8l42uJxMoKeS&index=5 
en voor de Mac: https://www.youtube.com/watch?v=wCMXbM5J0X8&list=PLwvrYc43l1MxAEOI_KwGe8l42uJxMoKeS&index=4


•	In Postman kun je de applicatie draaien via localhost: 8085/. Een lijst met export van de endpoints voor Postman is ook in het Github-project toegevoegd. In Postman kun je dit bestand openen d.m.v. file -> import -> en vervolgens het Json-bestand openen.

•	In PostgreSQL kun je vervolgens een nieuwe database aanmaken. Ga op Servers Database staan links boven zodat deze uitvouwt -> database -> rechtermuisknop klikken -> create -> Database -> je kunt nu een naam van je database opgeven. Voor mijn applicatie heb ik de naam Autogarage gekozen. En daarna op save klikken. Je database is nu aangemaakt. 

•	Nu is alles ingesteld. Ga naar IntelliJ en klik bovenin op het groene driehoekje (play knop). De applicatie runt nu. In Postman kun je nu de geïmporteerde endpoints aanroepen. 

•	Verander in IntelliJ in de map application.properties de PostgreSQL credentials naar jouw eigen settings. Voor nu staan deze op:
-	server.port=8085
-	spring.sql.init.platform=postgres
-	spring.datasource.url=jdbc:postgresql://localhost:5432/Autogarage
-	spring.datasource.username=postgres
-	spring.datasource.password=PostgreSQLetje
Verander je username en password naar je eigen gegevens van PostgreSQL. Als je in PostgreSQL een andere naam aan je database hebt gegeven, dan moet je de naam van Autogarage ook aanpassen. 

#### De users die gebruikt kunnen worden in de applicatie zijn:

Username : monteur<br>
Wachtwoord: password 	
Rol: ROLE_MONTEUR  	
Toestemming: Repairs ; inspections ; Deficiencies  ; Parts <br> 
 			 
Username: administratief_medewerker<br>
Wachtwoord: password 	
Rol: ROLE_ADMINISTRATIEFMEDEWERKER 	
Toestemming: Users ; Cars ;	Customers;	Repairs ; Inspections

