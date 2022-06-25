import encodings
import json
f = open('한국어불용어100.txt', 'r', encoding="utf-8")

data = {}
data["list"] = []
cnt = 0
for l in f:
    cnt +=1
    data["list"].append(l.split("\t")[0])
f2 = open('불용어2.txt', 'r', encoding="utf-8")
for l in f2:
    print(l)
    cnt +=1
    data["list"].append(l.replace("\n",""))

print(cnt)
w = open("parsing_data.json",'w',encoding="utf-8")
w.write(json.dumps(data,ensure_ascii=False))

