### This project is a Player Management System built using Java Swing and MySQL.
It allows users to perform CRUD (Create, Read, Update, Delete) operations on player data.
A simple GUI ensures an interactive and user-friendly experience.

### Features
## Welcome Screen: Greets the user and transitions to the login screen.
## Login System: Authenticates users with predefined credentials.

### Crud Operations:
## Insert Player: Add new player data into the database.
## Update Player: Modify details of existing players.
## Delete Player: Remove player records by their ID.
## Show Players: Display all records in a tabular format.
## Exit Option: Safely closes the application.
## Error Handling: Provides meaningful error messages for invalid actions or inputs.

### Programming Language:
## Java
## GUI Framework: Swing
## Database: MySQL
## Connectivity: JDBC (Java Database Connectivity)

### Database Design:
Table Name: player
Fields:
pid (Player ID - Integer, Primary Key)
pname (Player Name - String)
country (Country - String)

### User Flow
## Launch the application to see the welcome screen.
Login using the username and password ("Yogesh", "yogesh123").
Access the main menu for managing player data:
Enter player details and perform Insert, Update, or Delete actions.
View all player records using the Show button.
Exit the system using the Exit button.

### Future Enhancements:
# Add a registration module for dynamic user management.
# Include additional player attributes like age, team, and statistics.
# I mplement search functionality for specific player details.
# Strengthen security by encrypting passwords and using database role-based access.

