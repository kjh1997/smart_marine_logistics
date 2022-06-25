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
        
        self.driver.quit()
        if ship_name is not None:
            return IMO_num
        else:
            return False



     
crawler = PortMisCrawling()
crawler.startCrawling()