from selenium import webdriver
from selenium.common.exceptions import NoSuchElementException
from datetime import datetime
import csv
import time

category = ['글로벌', '도전', '성실', '의사소통', '성취지향', '주인의식', '창의', '정직']
keyword = [
    '문화 역량', '글로벌 역량', '외국어 역량',
    "도전 역량", "신기술 역량", "트렌드 역량",
    "성실 역량", "근면 역량", "열심히 역량",
    "소통 역량", "팀워크 역량", "협업 역량",
    "성취 역량", "달성 역량", "목표 역량",
    "책임 역량", "의무 역량", "자기주도 역량",
    "창의 역량", "창조 역량", "독창 역량",
    '청렴 역량', '윤리 역량', '정직 역량'
]

today = datetime.today()
past = datetime(today.year - 1, today.month, today.day)
today = today.strftime("%Y.%m.%d")
past = past.strftime("%Y.%m.%d")

baseUrl = "https://section.blog.naver.com/Search/Post.nhn"
baseUrl = baseUrl + "?startDate=" + past + "&endDate=" + today + "&rangeType=PERIOD"

driver = webdriver.Chrome("C:\ssafy\chromedriver.exe")
driver.get(baseUrl)

for i in range(len(keyword)):
    label = (i // 3) + 1
    filename = category[label - 1]

    savePath = "C:\ssafy\project3\s03p31a101\AI\csv\\blog" + filename + str(i) + ".csv"
    saveFile = open(savePath, 'w', encoding='utf-8', newline='')

    csv_writer = csv.writer(saveFile)
    csv_writer.writerow(["label", "sentence"])

    # 검색창 clear
    driver.find_element_by_name("sectionBlogQuery").clear()

    # 검색창 입력
    elem = driver.find_element_by_name("sectionBlogQuery")
    elem.send_keys(keyword[i])

    # 검색클릭
    elem = driver.find_element_by_xpath('//*[@id="header"]/div[1]/div/div[2]/form/fieldset/a[1]')
    elem.click()

    for page in range(1, 21):
        time.sleep(0.2)
        blogs = driver.find_elements_by_class_name("list_search_post")
        for blog in blogs:
            try:
                # # 제목
                title = blog.find_element_by_class_name("title").text.replace(",", "").replace(";", "")
                csv_writer.writerow([label, title])

                # 본문
                body = blog.find_element_by_class_name("text").text.replace("\n", " ").replace(",", "").replace(";", "")
                csv_writer.writerow([label, body])

            except NoSuchElementException:
                print("Error")
                continue

        driver.get(baseUrl + "&pageNo=" + str(page + 1) + "&keyword=" + keyword[i])
    ###############

    saveFile.close()
