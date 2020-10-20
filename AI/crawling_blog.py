from selenium import webdriver
from selenium.common.exceptions import NoSuchElementException
from datetime import datetime
import csv

category = ['글로벌', '도전', '성실', '의사소통', '성취지향', '주인의식', '창의']  # 10개
keyword = [
    "IT 문화", "글로벌", "외국어",
    "도전", "IT 신기술", "트렌드",
    "성실한", "IT 근면한", "열심히",
    "IT 소통", "팀워크", "협동",
    "성취", "IT 달성", "목표",
    "IT 책임", "의무", "자기주도",
    "창의", "IT 창조", "독창"
]

#오늘, 일년전 날짜 계산
today = datetime.today()
past = datetime(today.year-1, today.month, today.day)
today = today.strftime("%Y.%m.%d")
past = past.strftime("%Y.%m.%d")

baseUrl = "https://section.blog.naver.com/Search/Post.nhn"
baseUrl = baseUrl + "?startDate="+past+"&endDate="+today + "&rangeType=PERIOD"


driver = webdriver.Chrome("C:\ssafy\chromedriver.exe")
driver.get(baseUrl)

for i in range(len(keyword)):
    label = (i // 3) + 1
    filename = category[label - 1]

    savePath = "C:\ssafy\project3\s03p31a101\AI\csv\\blog" + filename + ".csv"
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

    ###############300페이지 블로그 끌어오기
    for page in range(1, 201):
        blogs = driver.find_elements_by_class_name("list_search_post")
        for blog in blogs:
            try:
                #제목
                title = blog.find_element_by_class_name("title").text.replace(",", "").replace(";", "")
                print(title)
                csv_writer.writerow([label, title])

                #본문
                body = blog.find_element_by_class_name("text").text.replace("\n", " ").replace(",", "").replace(";", "")
                csv_writer.writerow([label, body])

            except NoSuchElementException:
                print("Error")
                continue

        driver.get(baseUrl + "&pageNo=" + str(page+1)+"&keyword="+keyword[i])
    ###############

    saveFile.close()
