from flask import Flask, request 
from bs4 import BeautifulSoup
from selenium import webdriver 
from PortMisCrawling import PortMisCrawling
from flask import jsonify


app = Flask(__name__)
@app.route('/')
def home():

    return "home"

@app.route('/ulsan')
def ulsan():
    resultDict = crawler.UlsanVesselsCrawling()

    return jsonify(resultDict)

@app.route('/imo')
def imo():
    params = request.get_json()
    if len(params) == 0:
        return 'No parameter'
    name = params['marine']
    print(name)
    IMO_num = crawler.ImoCrawling(name)
    
    return jsonify(IMO_num)



if __name__ == '__main__':
    crawler = PortMisCrawling()
    app.run()
 