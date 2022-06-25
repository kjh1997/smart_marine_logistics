# import requests
# import time
# from datetime import datetime
# import pprint
# import codecs,json
# import re
# def load_json():
#     with codecs.open("parsing_data.json", 'r', encoding='utf-8') as rf:
#         return json.load(rf)

# stop_word = load_json()

# data = '''이는 본격적인 여름 휴가철인 7월에 <b>해상</b>교통량이 증가할 것으로 예상, 선제적 해양사고 예방을 위한... 1일부터 <b>해상</b>교통관제센터와 상황실, 경비함정, 파출소 등 현장부서가 정보를 
# 교환해 집중 단속을 펼친다. 특히...'''

# re.sub('[-=+,#/\?:^$.@*\"※~&%ㆍ!』\\‘|\(\)\[\]\<\>`\'…》]','', data)
# text = re.sub('[a-zA-Z-=+,#/\?:^$.@*\"※~&%ㆍ!』\\‘|\(\)\[\]\<\>`\'…》]','', data)
# print(text)
print(type(list()))