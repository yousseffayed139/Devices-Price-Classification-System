# Device Price Prediction Application

## Description

This project implements a machine learning model to predict the price of devices based on their specifications. It features a Flask API for the model and a Spring Boot application for device management, providing a RESTful interface for predicting prices and handling device data.

## Table of Contents

1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Modeling Steps](#modeling-steps)
4. [Installation Instructions](#installation-instructions)
5. [Usage Instructions](#usage-instructions)
6. [API Endpoints](#api-endpoints)
7. [Testing](#testing)
8. [Contributing](#contributing)
9. [Contact Information](#contact-information)

## Features

- Data preparation and engineering for machine learning.
- Exploratory Data Analysis (EDA) to extract insights from the dataset.
- Machine learning model to predict device prices based on specifications.
- RESTful API endpoints for device management and price prediction.

## Technologies Used

- Flask (Python)
- Spring Boot (Java)
- scikit-learn (for modeling)
- Pandas (data manipulation)
- Matplotlib/Seaborn (for visualization)
- H2 Database
- RESTful API architecture

## Modeling Steps

1.Data Preparation:
  Conduct thorough data cleaning and preprocessing.
  Perform feature engineering to enhance model performance.

2.Exploratory Data Analysis (EDA):
  Provide insights into the dataset, highlighting important patterns or correlations.
  Use visualizations (charts) to illustrate findings.
  
3.Model Training:
  Select an appropriate machine learning algorithm for price prediction.
 
4.Model Evaluation:
  Utilize metrics such as confusion matrix, accuracy, precision, and recall to evaluate model     performance.
  Provide commentary on the results and potential areas for improvement.
5.Model Optimization:
  Experiment with different algorithms or hyperparameters to enhance predictive accuracy.
  Justify the chosen optimization techniques.

## Installation Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/yousseffayed139/Devices-Price-Classification-System.git

2. Navigate to the Flask directory and install dependencies:
   ```bash
   cd flask_app
   pip install -r requirements.txt
3. Navigate to the Spring Boot directory and build the project:
    ```bash
    cd spring_boot_app
    ./mvnw clean install

## Usage Instructions

1. Run the Flask app:
    ```bash
    python app.py
2. Run the Spring Boot application:
     ```bash
     ./mvnw spring-boot:run

## API Endpoints

POST /api/devices: Retrieve a list of all devices.
GET /api/devices/{id}: Retrieve details of a specific device by ID.
POST /api/devices: Add a new device.
POST /api/predict/{deviceId}: Call the Python API to predict the price based on device specifications, and save the result in the device entity.

## Testing 
## Postman Collection

A Postman collection is available for testing the API endpoints. You can download it from the [postman_collection](postman_collection/my_collection.json) directory. 

To use the collection:
1. Download the collection file.
2. Open Postman and click on "Import".
3. Select the downloaded JSON file.
4. You can now see bunch of test cases.
5. Also you can use the requests in the collection to test the API.

## Contributing 
Contributions are welcome! Please feel free to submit a pull request or open an issue for suggestions.

## Contact Information 
Youssef Fayed - yousseffayed139@gmail.com



