from bs4 import BeautifulSoup
from selenium.webdriver.chrome.options import Options
import time
from selenium import webdriver 
from selenium.webdriver.support.select import Select
import os
import pandas as pd
import pymysql
from sqlalchemy import create_engine


class PortMisCrawling:
    def __init__(self, ):
        #self.driver_path = "kjh/chromedriver.exe"
        self.driver_path = "chromedriver.exe"
        self.chrome_options = Options()
        self.chrome_options.add_argument('window-size=1920,1080')
        self.driver = webdriver.Chrome(self.driver_path, chrome_options=self.chrome_options)

    

    def ImoCrawling(self, search_key):
        url = "https://new.portmis.go.kr/portmis/websquare/popup.jsp?w2xPath=/portmis/w2/sample/popup/pop/UC_PM_CM_002_07_02.xml&menuCd=M0182&popupID=mf_tacMain_contents_M0182_body_popupSearchVsslInnb&idx=idx10_16361987602659424.307303020947&w2xHome=/portmis/w2/main/&w2xDocumentRoot="

        self.driver.get(url)

        search_key = search_key.lower()


        # search_box = driver.find_element_by_css_selector("input#mf_ipt1")
        search_box = self.driver.find_element_by_xpath('//*[@id="mf_ipt1"]')
        search_box.send_keys(search_key)

        self.driver.implicitly_wait(1)

        search_btn = self.driver.find_element_by_css_selector("div#mf_udcSearch_btnSearch")

        search_btn.click()

        # driver.implicitly_wait(10)
        time.sleep(0.5)

        req = self.driver.page_source

        soup = BeautifulSoup(req, 'html.parser')  # 가져온 정보를 beautifulsoup로 파싱

        ship_name = None
        IMO_num =None

        for i in range(0, 9):
            try:
                ship_name_temp = soup.select_one(f"#mf_grdCallList_cell_{i}_0 > nobr").text
                IMO_num_temp = soup.select_one(f"#mf_grdCallList_cell_{i}_3 > nobr").text
                if search_key == ship_name_temp.lower():
                    ship_name = ship_name_temp
                    IMO_num = IMO_num_temp

            except AttributeError:
                break

        print(ship_name)
        time.sleep(100)
        self.driver.quit()
        if ship_name is not None:
            return IMO_num
        else:
            return False

    def UlsanVesselsCrawling(self):
        url = "https://new.portmis.go.kr/portmis/websquare/websquare.jsp?w2xPath=/portmis/w2/main/index.xml&page=/portmis/w2/cm/sys/UI-PM-MT-001-021.xml&menuId=0045&menuCd=M4735&menuNm=%BB%E7%C0%CC%C6%AE%B8%CA"


        self.driver.get(url)  # 드라이버에 해당 url의 웹페이지를 띄웁니다.
        self.driver.implicitly_wait(5)  # 페이지가 로딩되는 동안 5초 간 기다립니다.

        a = self.driver.find_element_by_xpath('//*[@id="mf_tacMain_contents_M4735_body_genMenuLevel1_1_genMenuLevel2_4_genMenuLevel3_1_btnMenuLevel3"]')
        a.click()
        time.sleep(1)
        harborcode_box = self.driver.find_element_by_xpath('//*[@id="mf_tacMain_contents_M0225_body_prtAgCd"]')
        harborcode_box.send_keys('820') #울산항코드
        req = self.driver.page_source
        soup = BeautifulSoup(req, 'html.parser')  # 가져온 정보를 beautifulsoup으로 파싱해줍니다.

        type_button = self.driver.find_element_by_xpath('//*[@id="mf_tacMain_contents_M0225_body_schFcltyType_input_2"]')
        type_button.click()

        search_button = self.driver.find_element_by_xpath('//*[@id="mf_tacMain_contents_M0225_body_udcSearch_btnSearch"]')
        search_button.click()
        time.sleep(1)

        select = Select(self.driver.find_element_by_id("mf_tacMain_contents_M0225_body_udcGridPageView_sbxRecordCount_input_0"))
        select.select_by_visible_text('10개씩 보기')
        time.sleep(1)

        
        cnt = 1
        save_dict={}
        temp_num = 0
        for num in range(15):
            req = self.driver.page_source
            soup = BeautifulSoup(req, 'html.parser')  # 가져온 정보를 beautifulsoup로 파싱
            temp_dict={}
            superBreak = False
            for i in range(0, 10):
                for j in range(0, 19):
                    try:
                        if soup.select_one(f"#mf_tacMain_contents_M0225_body_grdVsslStuList_cell_{i}_{j} > nobr").text != '':
                            temp_dict[j] = soup.select_one(f"#mf_tacMain_contents_M0225_body_grdVsslStuList_cell_{i}_{j} > nobr").text
                        else:
                            
                            temp_dict[j] = None
                    except AttributeError:
                        break
                if cnt == int(temp_dict[0]): #한번에 들어가도록 수정
                    save_dict[cnt-1] = temp_dict
                else:
                    superBreak = True
                    break
                cnt += 1
            if superBreak:
                break
            btn = self.driver.find_element_by_xpath('/html/body/div[2]/div[1]/div[3]/div/div[1]/div[2]/div[2]/div/div[2]/div[4]/div[2]/div[3]/div/ul/li[8]')
            btn.click()
            print("클릭")
            time.sleep(1)
        
        print("끝")
        return save_dict
    
    def boatIO(self):
        url = "https://new.portmis.go.kr/portmis/websquare/websquare.jsp?w2xPath=/portmis/w2/main/index.xml&page=/portmis/w2/cm/sys/UI-PM-MT-001-021.xml&menuId=0045&menuCd=M4735&menuNm=%BB%E7%C0%CC%C6%AE%B8%CA"


        self.driver.get(url)  # 드라이버에 해당 url의 웹페이지를 띄웁니다.
        self.driver.implicitly_wait(5)  # 페이지가 로딩되는 동안 5초 간 기다립니다.

        a = self.driver.find_element_by_xpath('//*[@id="mf_tacMain_contents_M4735_body_genMenuLevel1_1_genMenuLevel2_0_genMenuLevel3_1_btnMenuLevel3"]')
        a.click()
        time.sleep(1)
        
        # combobox 클릭
        a= self.driver.find_element_by_css_selector("#mf_tacMain_contents_M0182_body_chkSrchListPrtAgCd_main_tbody > tr > td.w2checkcombobox_col_label")
        a.click()

        # 820 울산항 선택
        a = self.driver.find_element_by_css_selector("#mf_tacMain_contents_M0182_body_chkSrchListPrtAgCd_itemTable_58")
        a.click()
        time.sleep(1)

        # 821 온산항 선택
        b = self.driver.find_element_by_css_selector("#mf_tacMain_contents_M0182_body_chkSrchListPrtAgCd_itemTable_59")
        b.click()
        time.sleep(1)

        # 822 미포항 선택
        c = self.driver.find_element_by_css_selector("#mf_tacMain_contents_M0182_body_chkSrchListPrtAgCd_itemTable_60")
        c.click()
        time.sleep(1)

        # 검색 버튼 누르기!
        a= self.driver.find_element_by_css_selector("#mf_tacMain_contents_M0182_body_btnSrch_btnSearch > a")
        a.click()
        time.sleep(1)
        
        # # 최대한 많이 보이게 하기 
        # select = Select(self.driver.find_element_by_id("mf_tacMain_contents_M0182_body_udcGridPageView2_sbxRecordCount_input_0"))
        # # select.select_by_visible_text('10개씩 보기')
        # select.select_by_visible_text('50000개씩 보기')
        # time.sleep(1)
        
        # req = self.driver.page_source
        # soup = BeautifulSoup(req, 'html.parser')  # 가져온 정보를 beautifulsoup으로 파싱해줍니다.

        
        # # 울산항 검색에 대한 세팅이 끝난 후 바로 내용을 확인한다~ 스키마가 될 부분 
        # schema = soup.select_one("#mf_tacMain_contents_M0182_body_gridList2_head_table > tr")
        # schema = schema.find_all("th")

        # for sch in schema:
        #     tmp = sch.nobr.text
        #     print(tmp)

        # print("total count ",len(schema))

        # xlsx를 통한 데이터 크롤링 시도
        contents =self.driver.find_element_by_css_selector("#mf_tacMain_contents_M0182_body_btnUdcCommon2_btnDownloadExcel")
        contents.click()
        time.sleep(1)


        # data = pd.read_excel("C:\\Users\\tkddn\\Downloads\\download.xlsx")

        
        # data.columns= [i for i in range(len(data)+1)]
        # data=data[11:]
        # data = data.drop(
        #         [data.columns[0],data.columns[3],data.columns[4] ,data.columns[9],data.columns[10]
        #         ,data.columns[13],data.columns[14],data.columns[15], data.columns[16]
        #         , data.columns[17],data.columns[19],data.columns[20],data.columns[30],data.columns[31]]
        #         , axis=1) # axis = 0 행 , axis = 1 은 열
        # data= data.fillna(0)
        # print(data.columns[18])
        
        # idx = [data.columns[25],data.columns[26],data.columns[27]]
        # data[idx] = data[idx].apply(pd.to_numeric)
        # data[data.columns[25]] = data[data.columns[25]]+data[data.columns[26]]+data[data.columns[27]]
        # data = data.drop(
        #     [data.columns[3],data.columns[4] ,data.columns[9],data.columns[10]
        #     ,data.columns[13],data.columns[14],data.columns[15], data.columns[16]
        #     , data.columns[17],data.columns[19],data.columns[20],data.columns[26],data.columns[27],data.columns[30],data.columns[31]]
        #     , axis=1) # axis = 0 행 , axis = 1 은 열
        # columns=["port","call_sign", "ship_name", "sortation", "out_in_ward_port", "entry_departure_port", "total_ton", "entry_Datetime" , "departure_datetime", 
        #         "nationality", "moorint_site", "next_port", "before_port", "ship_use","crew", "preliminary", "sail"]
        # data.columns = columns
        # print(data)
        # pd.read_csv("")

        # 직접 데이터를 크롤링 해보려는 시도 1
        # contents =soup.select_one("#mf_tacMain_contents_M0182_body_gridList2_body_tbody")
        # contents = contents.find_all("tr")
        # time.sleep(1)
        
        # for content in contents:
        #     tmp= content.find_all("td")
        #     # print(tmp, end="\n\n\n\n")
        #     cnt=0
        #     print(content.td.text) # 안에로 들어가지는 못하는 특징때문인가 봄....
        #     for t in tmp[1:]:
        #         if t.nobr:
        #             cnt+=1
        #             # print(t.nobr.text)
        #     # print("cnt == ", cnt)

        # v페이지 옮기는 방법~~
        # tm  =soup.select_one("#mf_tacMain_contents_M0182_body_udcGridPageList2_pglGridView > ul")
        # lis =tm.find_all("li")
        
        # for li in lis:
        #     tmp =li.a.id
        #     # tmp =soup.select_one(li)
        #     print(tmp,  type(tmp))
        #     # pass
        
        time.sleep(1)
        pass


     
# crawler = PortMisCrawling()
# print(crawler.boatIO())

# data = pd.read_excel("C:\\Users\\tkddn\\Downloads\\download.xlsx")
# # columns = data.iloc[10] # 제대로 된 columns 


# data.columns= [i for i in range(len(data)+1)]
# data=data[11:]
# data= data.fillna(0)
# print(data.columns[18])

# idx = [data.columns[25],data.columns[26],data.columns[27]]
# data[idx] = data[idx].apply(pd.to_numeric)

# data[data.columns[25]] = data[data.columns[25]]+data[data.columns[26]]+data[data.columns[27]]
# data = data.drop(
#     [data.columns[0],data.columns[3],data.columns[4] ,data.columns[9],data.columns[10]
#     ,data.columns[13],data.columns[14],data.columns[15], data.columns[16]
#     , data.columns[17],data.columns[19],data.columns[20],data.columns[26],data.columns[27],data.columns[30],data.columns[31]]
#     , axis=1) # axis = 0 행 , axis = 1 은 열

# columns=["call_sign", "ship_name", "sortation", "out_in_ward_port", "entry_departure_port", "total_ton", "entry_Datetime" , "departure_datetime", 
#                 "nationality", "moorint_site", "next_port", "before_port", "ship_use","crew", "preliminary", "sail"]
# data.columns = columns
# print(data)

# db_connection_str = 'mysql+pymysql://root:1234@localhost:3306/bigdata'
# db_connection = create_engine(db_connection_str)
# conn = db_connection.connect()

# data.to_sql(name="test", con=db_connection, if_exists='append',index=False)
# conn.close()
