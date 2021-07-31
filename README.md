# spring-data-neo4j-masterclass

## A good understanding of the usage of spring-data with REST Service and using Neo4j as local Database

### Software Required
* [Java 1.8](https://www.oracle.com/in/java/technologies/javase/javase-jdk8-downloads.html)
* [Spring tool Suite](https://spring.io/tools) or [Eclipse](https://www.eclipse.org/downloads/packages/release/helios/sr1/eclipse-ide-java-developers)
* [Apache Maven](https://maven.apache.org/download.cgi)
* [Git Bash](https://gramfile.com/git-bash-download/)
* [Neo4j](https://neo4j.com/download/) - This is the Neo4j database. It uses a different query language named as <strong>Cypher Query Language</strong> or in short it is called as <strong>CQL</strong>.
* [Postman](https://www.postman.com/downloads/)

### Steps to install and run Neo4j database
* Download the Software from the above URL
* After downloading install the software in Windows or in Mac machine
* Once it installed in your local system, run the Neo4j program
* Neo4j provides a default database named as <strong>Movie</strong>
* You can create your own database or you can also use the existing database as provided by Neo4j
* I have used the existing <strong>Movie</strong> database of Neo4j

### Typical Neo4j Queries
I have already told that Neo4j Database provides a default database known as `Movie` database. Lets discuss a little bit about the Cypher queries
<ol>
<li>Simple Cypher Query Language Examples</li>
* <strong>MATCH (p:Person) RETURN p LIMIT 1</strong> - It means it will return only one person
* <strong>MATCH (tom:Person {name: 'Tom Hanks'}) RETURN tom</strong> - It will return Tom Hanks
* <strong>MATCH (:Person {name: 'Tom Hanks'})-[:DIRECTED]->(movie:Movie) RETURN movie</strong> - It will return the Movie that Tom Hanks has directed
* <strong>MATCH (:Person {name: 'Tom Hanks'})-[:DIRECTED]->(movie:Movie) RETURN movie.title</strong> - It will return the title of the Movie that Tom Hanks has directed
<li>Complex Cypher Query Language Examples</li>
* <strong>"MATCH (m:Movie) WHERE m.title =~ ('(?i).*'+{title}+'.*') RETURN m"</strong> - It means it will return the list of  movies matching the movie title
* <strong>"MATCH (m:Movie)<-[:ACTED_IN]-(a:Person) RETURN m.title as movie, collect(a.name) as cast LIMIT {limit}"</strong> - It is creating a Relationship between Movie and Person Nodes, so that it can show the list of actors acted in that movie
* <strong>"MATCH (m:Movie)<-[:ACTED_IN]-(a:Person) WHERE m.title =~ ('(?i).*'+$title+'.*') RETURN a.name"</strong> - It will return just the list of names who has acted in that particular Movie
</ol>

### Problems faced while connecting with Movie DBMS from Spring Boot Application
* In certain cases it can happen that from your application you can't connect to Movie Database
* If you face such type of scenario then reset the password once
* Click on the <strong>Movie</strong> DBMS, it will open a panel at the right hand side where there is an option that you can Reset the DBMS Password
* Hit that option, reset the password, use the new password in your application and it will connect.

### Steps to clone and run the application
* Install Neo4j Database as discussed in the [Steps to Install and run Neo4j database](https://github.com/c86amik/spring-data-neo4j-masterclass#steps-to-install-and-run-Neo4j-database).
* Run the Neo4j Database
* Open Git Bash or even you can open Command Prompt (if you are using Windows) or Terminal (if you are using MAC) in your machine
* Clone the application from github.com as   
<code>git clone https://github.com/c86amik/spring-data-neo4j-masterclass.git</code>
* Open either <strong>STS</strong> or <strong>Eclipse</strong> and import the application as <strong>Maven</strong> project
* After the application got successfully imported in either <strong>STS</strong> or <strong>Eclipse</strong>
* Right Click on the application, select the <strong>Run As</strong> option, and then select <strong>Spring Boot App</strong>
* The application will start in the port <strong>7112</strong>
* Open the Postman and test the REST Endpoints

### Testing using Postman
<ol>
<li><strong>Get All Persons</strong> - localhost:7112/allPerson</li>
<li><strong>Find a Specific Person details</strong> - localhost:7112/findSpecificPerson/{name}</li>
<li><strong>Get All Movies</strong> - localhost:7112/allMovies</li>
<li><strong>Find a Specific Movie Details</strong> - localhost:7112/findSpecificMovie/{title}</li>
<li><strong>Find a List of Specific Movies matching the title</strong> - localhost:7112/findMovies/{title}. It will return the list of Movies matching the input as provided. Ex - If you pass the title as `The Matrix` it will return all the movies which is matching `The Matrix` content as `The Matrix`, `The Matrix Revolutions`, etc.</li>
<li><strong>Save a Movie along with the Persons who acted in that Movie</strong> - localhost:7112/saveMovie</li>
<li><strong>Get user by mobileNo</strong> - localhost:7112/findMovieActors/{title}. It will return the List of actors who has acted in that specific movie</li>
</ol>

#### Dummy JSON object
* Body for the <strong>POST</strong> Method   
<code>{
	"personName" : ${personName},
	"movieName" : ${movieName},
	"releasedYear" : ${releasedYear},
	"roleName" : "${roleName}",
    "tagLine" : "${tagLine}"
}</code>

	