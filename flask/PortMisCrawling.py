from bs4 import BeautifulSoup
from selenium.webdriver.chrome.options import Options
import time
from selenium import webdriver 
from selenium.webdriver.support.select import Select

class PortMisCrawling:
    def __init__(self, ):
        self.driver_path = "./chromedriver.exe"
        self.chrome_options = Options()
        self.chrome_options.add_argument('window-size=1920,1080')
        self.driver = webdriver.Chrome(self.driver_path, chrome_options=self.chrome_options)

    

    def UlsanVesselsCrawling(self):
        url = "https://new.portmis.go.kr/portmis/websquare/websquare.jsp?w2xPath=/portmis/w2/main/index.xml&page=/portmis/w2/cm/sys/UI-PM-MT-001-021.xml&menuId=0045&menuCd=M4735&menuNm=%BB%E7%C0%CC%C6%AE%B8%CA"


        self.driver.get(url)  # 드라이버에 해당 url의 웹페이지를 띄웁니다.
        self.driver.implicitly_wait(10)  # 페이지가 로딩되는 동안 5초 간 기다립니다.
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

    def ImoCrawling(self, search_key="DM CONDOR"):
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
        data = {}
        data['imo'] = IMO_num
        self.driver.quit()
        print(data)
        if ship_name is not None:
            return data
        else:
            return False

     
# crawler = PortMisCrawling()
# crawler.ImoCrawling()