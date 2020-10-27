from flask import Flask, jsonify
import json

app = Flask(__name__)

@app.route('/create') 
def createSkill():
    #[[12.22148023 13.23422849 13.19760843 21.47686775  8.63947671 11.65970656 9.47472986 10.09590196]]
    jsonFile = {"result1": 12.123,
                "result2": 12.123,
                "result3": 12.123,
                "result4": 12.123,
                "result5": 12.123,
                "result6": 12.123,
                "result7": 12.123,
                "result8": 12.123,
    
                }
    return jsonify(jsonFile)
