import os
import pandas as pd
import pickle
from flask import Flask, request, jsonify
import joblib

app = Flask(__name__)

# Load the preprocessor and model
model_path = 'xgb_model.pkl'
preprocessor_path = 'preprocessor.pkl'

# Check if files exist
if not os.path.exists(model_path) or not os.path.exists(preprocessor_path):
    raise FileNotFoundError("Model or preprocessor file not found.")

with open(model_path, 'rb') as model_file:
    model = pickle.load(model_file)

with open(preprocessor_path, 'rb') as preprocessor_file:
    preprocessor = joblib.load(preprocessor_file)
    


# Data validation function
def validate_data(data):
    # Check if the data is a DataFrame
    if not isinstance(data, pd.DataFrame):
        return False
    
    # Check for NaN values
    if data.isnull().values.any():
        return False
    
    # Check for the correct number of features (adjust according to your model's input)
    expected_features = 20  # Replace with the actual number of features
    if data.shape[1] != expected_features:
        return False
    
    # Additional checks can be added based on the data characteristics
    return True

@app.route('/predict', methods=['POST'])
def predict():
    # Get JSON data from request
    input_data = request.get_json()

    # Convert to DataFrame
    input_df = pd.DataFrame([input_data])


    # Validate the input data
    if not validate_data(input_df):
        return jsonify({'error': 'Invalid input data.'}), 400

    # Transform the input data

    transformed_data = preprocessor.transform(input_df)
    

    # Perform prediction
    predictions = model.predict(transformed_data)


    # Return predictions as JSON

    return jsonify(int(predictions[0]))

if __name__ == '__main__':
    app.run(debug=True)
