import requests
import time
from datetime import datetime
import pprint
import codecs,json
import re
from datetime import datetime, timedelta
from elasticsearch import Elasticsearch
import gensim
from gensim import corpora
import pandas as pd
from konlpy.tag import Okt

def stem(word):
    return stemmer.nouns(word)


def stop_words_load():
    with codecs.open("parsing_data.json", 'r', encoding='utf-8') as rf:
        return json.load(rf)


news_data = [] # 뉴스의 요약을 저장
def get_naver_news_search(search_keyword, start=1, display=100):
    """
    네이버 open api를 이용해 뉴스 키워드 검색
    뉴스 링크(네이버링크)들을 파일로 저장 (pickle이용)
    [매개변수]
        keyword: 검색키워드
        start: 검색 결과중 조회할 시작 번째 (1 ~ 1000)
        display: 한번에 가져올 개수 (1 ~ 100)
    """
#     id = 'Z7DcJLvd45bsnC9wdF7m'
#     pwd='EXxuxbALoq'
    base_url = 'https://openapi.naver.com/v1/search/news.json?query={}&start={}&display={}&sort=sim'
    headers = {
        'X-Naver-Client-Id':'agXsz69GSRrBcAIVoLhV',
        'X-Naver-Client-Secret':'y6vqdnVADz'
    }
    
    for keyword in search_keyword:
        while start <= 100:
            
            url = base_url.format(keyword, start, display)
            # 요청
            res = requests.get(url, headers=headers)
            if res.status_code == 200:
                result = res.json()
                items = result['items']
                # 네이버 링크들을 추출
                for item in items:
                    data = {}
                    # link = item['link']
                    # if 'news.naver.com' in link:
                    #     data["link"] = link
                    description = item['description']
                    title = item['title']
                    
                    if description != "":
                        description = re.sub('[a-zA-Z-=+,#/\?:^$.@*\"※~&%ㆍ!』\\‘|\;(\)\[\]\<\>`\'…》]','', description)
                        description = stem(description)
                        temp = ""
                        for i in description:
                            temp += i + " "
                        description = temp
                        data["article_body"] = description
                    if title != "":
                        title = re.sub('[a-zA-Z-=+,#/\?:^$.@*\"※~&%ㆍ!』\\‘;|\(\)\[\]\<\>`\'…》]','', title)
                        title = stem(title)
                        temp = ""
                        for i in title:
                            temp += i + " "
                        title = temp
                        data["title"] = title
                    # data["collect_time"] = str(datetime.utcnow().replace(microsecond=0) + timedelta(hours=9))[:16]

                    news_data.append(data)

                start = start + display

                time.sleep(0.2) 
                
                # 네이버 open api 요청은 초당 10회까지 호출가능하기 때문에 약간에 시간차를 둔다.
            else:
                raise Exception('검색 실패: {}'.format(res.status_code))
    return news_data


def gen_topics(df):
    df['title'] = df['title'].str.replace("[^ㄱ-하-ㅣ가-힣]"," ") # 한글이 아니면 전부 제거
    df['article_body'] = df['article_body'].str.replace("[^ㄱ-하-ㅣ가-힣]"," ") # 한글이 아니면 전부 제거
    tokenized_doc = df['title'].apply(lambda x: x.split())
    tokenized_doc = tokenized_doc.apply(lambda x: [item for item in x if item not in stop_words_load()])

    dictionary = corpora.Dictionary(tokenized_doc) # tokenized 데이터를 통해 dictionary로 변환
    corpus = [dictionary.doc2bow(text) for text in tokenized_doc] # 코퍼스 구성

    NUM_TOPICS = 40 # 20개의 토픽, k=20
    ldamodel = gensim.models.ldamodel.LdaModel(corpus, num_topics = NUM_TOPICS, id2word=dictionary, passes=15)
    topics = ldamodel.print_topics(num_words=4) # TOPIC별 단어를 4개만
    print(topics)
    return topics

def get_words(topics):
    result = []
    for topic in topics:
        word = topic[1].split("+")[0].split("*")[1].replace('"',"").replace(" ","")
        if len(word) < 2:
            continue
        result.append(word)

    return set(result)




if __name__ == '__main__':
    stemmer = Okt()
    search_keyword = ["울산항만", "스마트 해상물류", "스마트 항만"]
    data = get_naver_news_search(search_keyword)
    df = pd.DataFrame(data)
    topics = gen_topics(df)
    result = get_words(topics)

    print(result)








# ---------------------
# es = Elasticsearch('http://127.0.0.1:9200')
# def make_index(es, index_name):
#     """인덱스를 신규 생성한다(존재하면 삭제 후 생성) """
#     if es.indices.exists(index=index_name):
#         es.indices.delete(index=index_name)

# def insertData(es):
    
#     cnt=0
#     for d in data:
#         print(d)
#         es.index(index="news", doc_type="string", body=d, id=cnt)
#         cnt+=1

# make_index(es,"news")
# insertData(es)