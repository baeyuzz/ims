from flask import Flask,jsonify
from flask_restful import Resource, Api,reqparse
from testSvc import test
from flask_cors import CORS

app = Flask(__name__)
api = Api(app)

class CreateSkill(Resource):
    def post(self):
        try:
            parser = reqparse.RequestParser()
            parser.add_argument('text', type=str)
            args = parser.parse_args()
            result=test(args['text'])
            print("-------")

            jsonFile = {
                "result1": result[0][0], #글로벌
                "result2": result[0][1], #도전
                "result3": result[0][2], #성실
                "result4": result[0][3], #의사소통
                "result5": result[0][4], #성취지향
                "result6": result[0][5], #주인의식/책임감
                "result7": result[0][6], #성취지향
                "result8": result[0][7], #정직

                }
            return jsonify(jsonFile)
        except Exception as e:
            return {'error': str(e)}

api.add_resource(CreateSkill, '/analysis')

if __name__ == '__main__':
    CORS(app)
    app.run(host="0.0.0.0",port="5000",debug=True)
