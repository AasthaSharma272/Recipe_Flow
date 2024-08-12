**Recipe Flow**

Created By: Aastha, Dua, Farah, Maleeha, Seedak

**Project Overview**

Recipe Flow is a recipe organization app developed in Java. It allows users to discover, upload, and save recipes with ease. Utilizing the Edamam Recipe Search API, the platform offers a wide array of recipes and features that make managing personal recipes simple and efficient.

**Features**

Recipe Browsing: Explore a variety of recipes generated from the Edamam Recipe Search API.

Search & Filters: Use filters (e.g., vegan, peanut-free) or search by recipe names to narrow down results.

Personal Recipe Uploads: Users can upload their own recipes, including a name, ingredients, instructions, and an optional image.

Saved Recipes: Save your favorite recipes to your profile for quick access later.

**Design Principles**

Recipe Flow adheres to several design patterns and principles to ensure a robust and maintainable codebase:

Observer Design Pattern: Facilitates efficient communication between components, especially when updating the user interface with new data.

Simple Factory Design Pattern: Streamlines the creation of recipe objects, keeping the codebase clean and maintainable.

SOLID Principles:

Open/Closed Principle: The software is designed to be open for extension but closed for modification, making it easy to add new features without altering existing code.

Interface Segregation Principle: Interfaces are tailored to their specific needs, avoiding the implementation of unnecessary methods.

Single Responsibility Principle: Each class and module has a single responsibility, ensuring clarity and simplicity in the code structure.

**Getting Started**

Clone the Repository:

```bash
git clone https://github.com/aasthasharma272/Recipe_Flow.git
```

Set Up the Project:

Import the project into your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).

Add Dependencies:

Make sure all necessary libraries and dependencies are included in your project settings or pom.xml if using Maven.

Set Up Environment Variables:

Add your Edamam API key to the environment or as a configuration file:

```bash
EDAMAM_API_KEY=your-api-key-here
```

Run the Application:

Run the main class to start the application.
