Title: Movie Watchlist App using OMDB API

![cfmdb](https://user-images.githubusercontent.com/110426010/232858174-94e58a51-3da4-4245-a7e0-e47e3b6155b1.PNG)

Description:
This is a web application that allows users to search for movies by title using the OMDB API and add them to their watchlist. Users can also log in using their credentials, add or remove movies from their watchlist, and view their watchlist in their profile. The application is built using Java EE 8, JAX-RS for RESTful web services, Hibernate for database persistence, MySQL as the database management system, JavaScript for front-end interactivity, and jQuery for making AJAX calls.

Technologies used:

    Java EE 8
    JAX-RS
    Hibernate
    MySQL
    JavaScript
    jQuery
    HTML
    CSS

Key Features:

    Movie search: Users can search for movies by title using the OMDB API.
    User authentication: Users can log in using their credentials.
    Watchlist management: Users can add movies to their watchlist, or remove them from their watchlist.
    Profile view: Users can view their profile, which displays their watchlist.

This project showcases the use of Java EE 8, JAX-RS, Hibernate, MySQL, JavaScript, and jQuery to build a web application that provides movie search functionality, user authentication, and watchlist management.

Instructions on how to use:

    Obtain OMDB API key: To use the movie search functionality, you will need to obtain an API key from https://www.omdbapi.com by navigating to their API Key page.
    Then, copy your API Key and insert it inside the OmdbApiService class, in the relevant field.
    
    Set up MySQL database: Create an empty MySQL database and configure the database name, and the username and password in the persistence.xml file inside the Java    project, which uses Hibernate for database persistence. Also, inside the persistence.xml, set the hibernate.hbm2ddl.auto value to "create" before running the Main method for the first time, then change it to update.
    
    Creating the Database: Make sure to run the MySQL80 server by navigating to services.msc and starting the server. Then run the Main class inside the Java project, this will use the EntityManagerFactory along with the persistence unit, to automatically create the required database tables based on the entity classes defined in the project.
    
    Insert a user: Use the entity manager to insert a user into the MySQL database with the desired credentials. The Main method already has a transaction ready for your convenience, all you need to do is give your own username and password to the User entity before insertion.
    
    Configuring and running the application: Make sure the MySQL server is running, then run the Java project so the application will be deployed on localhost:8080. Afterwards, navigate into the front end folder and run index.html by clicking the "Go live" button if you have the VS Code live server plug-in installed. The front end will run on localhost: 5500. That's it! Now you can log in with your username and password, search for movies and add them to your watchlist!
