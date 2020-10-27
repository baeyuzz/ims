#!/usr/bin/env python
# coding: utf-8

# In[1]:


import os
import pandas as pd

ability_1 = pd.read_csv("global.csv", encoding="utf-8")
ability_2 = pd.read_csv("active.csv", encoding="utf-8")
ability_3 = pd.read_csv("challenge.csv", encoding="utf-8")
ability_4 = pd.read_csv("sincerity.csv", encoding="utf-8")
ability_5 = pd.read_csv("communication.csv", encoding="utf-8")
ability_6 = pd.read_csv("patience.csv", encoding="utf-8")
ability_7 = pd.read_csv("honest.csv", encoding="utf-8")
ability_8 = pd.read_csv("owner_spirit.csv", encoding="utf-8")
ability_9 = pd.read_csv("creativity.csv", encoding="utf-8")
ability_10 = pd.read_csv("teamwork.csv", encoding="utf-8")

ability_1.head()


# In[2]:


ability_2.head()


# In[3]:


ability_3.head()


# In[4]:


a1 = pd.DataFrame(ability_1["sentence"])
a1["label"] = 1
a1.head()


# In[5]:


a2 = pd.DataFrame(ability_2["sentence"])
a2["label"] = 2
a2.head()


# In[6]:


a3 = pd.DataFrame(ability_3["sentence"])
a3["label"] = 3
a3.head()


# In[7]:


a4 = pd.DataFrame(ability_4["sentence"])
a4["label"] = 4
a4.head()


# In[8]:


a5 = pd.DataFrame(ability_5["sentence"])
a5["label"] = 5
a5.head()


# In[9]:


a6 = pd.DataFrame(ability_6["sentence"])
a6["label"] = 6
a6.head()


# In[10]:


a7 = pd.DataFrame(ability_7["sentence"])
a7["label"] = 7
a7.head()


# In[11]:


a8 = pd.DataFrame(ability_8["sentence"])
a8["label"] = 8
a8.head()


# In[12]:


a9 = pd.DataFrame(ability_9["sentence"])
a9["label"] = 9
a9.head()


# In[13]:


a10 = pd.DataFrame(ability_10["sentence"])
a10["label"] = 10
a10.head()


# In[14]:


data = a1.append(a2).append(a3).append(a4).append(a5).append(a6).append(a7).append(a8).append(a9).append(a10)


# In[15]:


data.shape


# In[16]:


import numpy as np
from sklearn.model_selection import train_test_split


# In[17]:


train, test = train_test_split(data, test_size=0.2, random_state=42)


# In[18]:


train["label"] = pd.Categorical(train["label"])


# In[19]:


train.groupby("label").count()


# In[20]:


test.groupby("label").count()

from konlpy.tag import Twitter
from sklearn.feature_extraction.text import CountVectorizer


def get_noun(text):
    tokenizer = Twitter()
    nouns = tokenizer.nouns(text)
    return [n for n in nouns]


cv = CountVectorizer(tokenizer=get_noun,stop_words=None, ngram_range=(1, 2))

tdm = cv.fit_transform(train["sentence"])


from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.linear_model import SGDClassifier
from sklearn.pipeline import Pipeline

text_clf_svm = Pipeline([('vect', CountVectorizer(tokenizer=get_noun)),
                         ('tfidf', TfidfTransformer()),
                         ('clf-svm', SGDClassifier(loss='hinge', penalty='l2', alpha=1e-3, random_state=42))])

text_clf_svm = text_clf_svm.fit(train["sentence"], train["label"])


predicted_svm = text_clf_svm.predict(test["sentence"])
np.mean(predicted_svm == test["label"])


print(type(predicted_svm))



text_clf_svm.predict(["[창조는 메모에서 비롯된다] 평소에 이런저런 생각을 메모하는 습관이 있습니다. 이 습관은 인턴활동을 할 때 많은 도움이 되었습니다. 인턴 교육당시에 업무 중에 포스터 기획을 하는 업무도 있다는 말씀을 해 주셨습니다. 그 후에 제품에 대한 교육이 이뤄졌습니다. 제품에 대한 장점들을 기준을 세워 메모를 하였습니다. 그 후 장소에 구애받지 않고 제품의 장점 그리고 시즈널 키워드를 활용하여 아이디어를 메모했습니다. 제품 중에 닭죽이 있었는데, 그 죽의 효능이 수능생들에 매우 많은 도움이 되는 것들이었습니다. 그래서 저는 수능 시즌에 맞춘 아이디어를 제공하여 닭죽을 먹으면 이 죽을 먹으며 죽빵을 날리는 것 처럼 문제를 한방 먹인다는 컨셉으로 포스터를 기획했습니다. 이 포스터로 흥미을 유발하여 고객층의 소비를 이끌었던 적 있습니다."])
print (text_clf_svm.decision_function(["평소에 이런저런 생각을 메모하는 습관이 있습니다. 이 습관은 인턴활동을 할 때 많은 도움이 되었습니다. 인턴 교육당시에 업무 중에 포스터 기획을 하는 업무도 있다는 말씀을 해 주셨습니다. 그 후에 제품에 대한 교육이 이뤄졌습니다. 제품에 대한 장점들을 기준을 세워 메모를 하였습니다. 그 후 장소에 구애받지 않고 제품의 장점 그리고 시즈널 키워드를 활용하여 아이디어를 메모했습니다. 제품 중에 닭죽이 있었는데, 그 죽의 효능이 수능생들에 매우 많은 도움이 되는 것들이었습니다. 그래서 저는 수능 시즌에 맞춘 아이디어를 제공하여 닭죽을 먹으면 이 죽을 먹으며 죽빵을 날리는 것 처럼 문제를 한방 먹인다는 컨셉으로 포스터를 기획했습니다. 이 포스터로 흥미을 유발하여 고객층의 소비를 이끌었던 적 있습니다."]))

#[창조는 메모에서 비롯된다] 포함해서 돌리면 확실히 창의 역량의 값이 커짐
#제외해서 돌려도 제일 크긴함


# In[ ]:


text_clf_svm.predict(["지원동기:지원동기를구체적으로기술해주세요\n[새로운 도전을 통해 성장하는 사람]\n롯데 e커머스에서 보이스커머스 사업을 내년 상반기에 상용화할 것이라는 계획을 접하게 되었습니다. 보이스커머스의 핵심 기술 중 하나는 바로 STT 입니다. 저는 000 프로젝트에서 STT 기술을 이용해 000을 제공하는 앱을 개발하였습니다. 이를 통해 STT 기술에 대한 이해와 중요성을 배울 수 있었습니다. 이때의 경험이 롯데 e커머스의 프로그래밍 직무를 수행하는 데 도움이 될 것이라 생각합니다.\n저의 목표 IT 기술을 통해 사람들의 생활을 보다 편리하게 만드는 것입니다. 롯데 e커머스에서는 보이스커머스 뿐만 아니라 사만다 톡추천, 쇼바시, 스마트픽 등 고객들에게 편리한 서비스 제공을 위해 큰 노력을 하고 있습니다. 고객의 생활을 더욱 편리하게 만든다는 것이 제가 추구하는 바와 같다고 생각합니다. 그리고 SSO 시스템을 통해 새롭게 도약하는 롯데 e커머스와 함께 하고 싶기에 지원하게 되었습니다.\n성장과정:성장과정을구체적으로기술해주세요.[새로운 도전을 통해 성장하는 사람]\n경험을 통해 배움을 얻을 수 있다는 생각을 가지고 있습니다. 그래서 도전할 기회가 생기면 주저하지 않고 참여하였습니 다.\n첫째, IT 관련해서 2회 인턴 경험과 다양한 개발 프로젝트 경험을 가지고 있습니다.\n3학년 재학 중 웹에이전시 기획팀 인턴에 참여할 수 있는 기회가 주어졌습니다. 인턴을 하며 기획, 디자인, 개발 교육을 받았고 다양한 프로젝트를 수행하였습니다. 이를 통해 프로젝트 진행 과정과 협력을 배울 수 있었습니다. 또한, 재학 중 다양한 개발 프로젝트를 진행하였습니다. 참여에 그치는 것이 아니라 공모전 출품, 정보처리학회 추계학술대회 참여 등 도전을 확장해나갔습니다.\n둘째, IT 매니저 업무를 통해 실무를 익히다.\n3개월 동안 진학 컨설팅 센터에서 근무한 경험이 있습니다. 1달도 채 남지 않은 오픈 날짜까지 웹 개편부터 SNS 관리를 요청받았습니다. 하지만 IT 매니저는 저뿐이고, 센터 내에 IT 업무에 대해 아는 분이 없는 상황이었습니다. 그래서 웹 개발 부터 SNS 홍보 방식까지 모두 알아보았습니다. 먼저 외주업체를 선정해 퍼블리싱을 요청하였고, 퍼블리싱된 소스 코드에Javascript 코드를 추가하는 방식으로 웹 개편을 하였습니다. 그리고 블로그, 인스타, 페이스북에 센터 자료를 제작해 업로드 하였고, 페이스북 광고를 진행하는 등 다양한 매체를 통해 홍보하였습니다. 그 결과 오픈 날짜에 맞춰 성공적으로 홈페이지 개편과 홍보를 할 수 있었습니다. 포기하지 않고 도전한 다양한 경험을 바탕으로 롯데 e커머스에서도 적극적인 업무자세를 보여드리겠습니다.\n입사후포부:입사후10년동안의회사생활시나리오와그것을추구하는이유를기술해주세요.\n[오늘보다 내일 더 성장하는 사람]\n먼저 입사 후 롯데 e커머스의 프로젝트 진행 과정을 파악하고 이해하겠습니다. 그리고 이를 토대로 내년 상반기 오픈 예정인 투게더 앱에 대해 고민하는 신입사원이 되겠습니다. 그리고 빅데이터를 잘 다룰 수 있도록 공부하고, DBA 자격증을 취득해 전문성을 갖추겠습니다.\n5년 후, 고객별 맞춤형 추천 시스템을이 도입될 수 있도록 하겠습니다. 유튜브 사용자 맞춤 동영상 추천처럼 맞춤 추천 시스템은 다양한 기업에서 사용하고 있습니다. 롯데 e커머스에도 적용되어 고객마다 다른 추천 상품을 볼 있도록 하고, 이를 통해 편리성과 매출 증대를 이룰 수 있도록 하겠습니다.\n10년 후, 업계를 선도하는 롯데 e커머스의 프로젝트 매니저로 성장하여 인공지능, 빅데이터를 활용해 추천을 넘어 고객의 삶에 필수적인 서비스를 기획하고 개발하겠습니다. 지나온 10년을 설하고, 앞으로의 10년을 준비하겠습니다. 그리고 오늘 보다 내일 더 성장하는 롯데 e커머스의 일원이 되겠습니다."])
print (text_clf_svm.decision_function(["지원동기:지원동기를구체적으로기술해주세요\n[새로운 도전을 통해 성장하는 사람]\n롯데 e커머스에서 보이스커머스 사업을 내년 상반기에 상용화할 것이라는 계획을 접하게 되었습니다. 보이스커머스의 핵심 기술 중 하나는 바로 STT 입니다. 저는 000 프로젝트에서 STT 기술을 이용해 000을 제공하는 앱을 개발하였습니다. 이를 통해 STT 기술에 대한 이해와 중요성을 배울 수 있었습니다. 이때의 경험이 롯데 e커머스의 프로그래밍 직무를 수행하는 데 도움이 될 것이라 생각합니다.\n저의 목표 IT 기술을 통해 사람들의 생활을 보다 편리하게 만드는 것입니다. 롯데 e커머스에서는 보이스커머스 뿐만 아니라 사만다 톡추천, 쇼바시, 스마트픽 등 고객들에게 편리한 서비스 제공을 위해 큰 노력을 하고 있습니다. 고객의 생활을 더욱 편리하게 만든다는 것이 제가 추구하는 바와 같다고 생각합니다. 그리고 SSO 시스템을 통해 새롭게 도약하는 롯데 e커머스와 함께 하고 싶기에 지원하게 되었습니다.\n성장과정:성장과정을구체적으로기술해주세요.[새로운 도전을 통해 성장하는 사람]\n경험을 통해 배움을 얻을 수 있다는 생각을 가지고 있습니다. 그래서 도전할 기회가 생기면 주저하지 않고 참여하였습니 다.\n첫째, IT 관련해서 2회 인턴 경험과 다양한 개발 프로젝트 경험을 가지고 있습니다.\n3학년 재학 중 웹에이전시 기획팀 인턴에 참여할 수 있는 기회가 주어졌습니다. 인턴을 하며 기획, 디자인, 개발 교육을 받았고 다양한 프로젝트를 수행하였습니다. 이를 통해 프로젝트 진행 과정과 협력을 배울 수 있었습니다. 또한, 재학 중 다양한 개발 프로젝트를 진행하였습니다. 참여에 그치는 것이 아니라 공모전 출품, 정보처리학회 추계학술대회 참여 등 도전을 확장해나갔습니다.\n둘째, IT 매니저 업무를 통해 실무를 익히다.\n3개월 동안 진학 컨설팅 센터에서 근무한 경험이 있습니다. 1달도 채 남지 않은 오픈 날짜까지 웹 개편부터 SNS 관리를 요청받았습니다. 하지만 IT 매니저는 저뿐이고, 센터 내에 IT 업무에 대해 아는 분이 없는 상황이었습니다. 그래서 웹 개발 부터 SNS 홍보 방식까지 모두 알아보았습니다. 먼저 외주업체를 선정해 퍼블리싱을 요청하였고, 퍼블리싱된 소스 코드에Javascript 코드를 추가하는 방식으로 웹 개편을 하였습니다. 그리고 블로그, 인스타, 페이스북에 센터 자료를 제작해 업로드 하였고, 페이스북 광고를 진행하는 등 다양한 매체를 통해 홍보하였습니다. 그 결과 오픈 날짜에 맞춰 성공적으로 홈페이지 개편과 홍보를 할 수 있었습니다. 포기하지 않고 도전한 다양한 경험을 바탕으로 롯데 e커머스에서도 적극적인 업무자세를 보여드리겠습니다.\n입사후포부:입사후10년동안의회사생활시나리오와그것을추구하는이유를기술해주세요.\n[오늘보다 내일 더 성장하는 사람]\n먼저 입사 후 롯데 e커머스의 프로젝트 진행 과정을 파악하고 이해하겠습니다. 그리고 이를 토대로 내년 상반기 오픈 예정인 투게더 앱에 대해 고민하는 신입사원이 되겠습니다. 그리고 빅데이터를 잘 다룰 수 있도록 공부하고, DBA 자격증을 취득해 전문성을 갖추겠습니다.\n5년 후, 고객별 맞춤형 추천 시스템을이 도입될 수 있도록 하겠습니다. 유튜브 사용자 맞춤 동영상 추천처럼 맞춤 추천 시스템은 다양한 기업에서 사용하고 있습니다. 롯데 e커머스에도 적용되어 고객마다 다른 추천 상품을 볼 있도록 하고, 이를 통해 편리성과 매출 증대를 이룰 수 있도록 하겠습니다.\n10년 후, 업계를 선도하는 롯데 e커머스의 프로젝트 매니저로 성장하여 인공지능, 빅데이터를 활용해 추천을 넘어 고객의 삶에 필수적인 서비스를 기획하고 개발하겠습니다. 지나온 10년을 설하고, 앞으로의 10년을 준비하겠습니다. 그리고 오늘 보다 내일 더 성장하는 롯데 e커머스의 일원이 되겠습니다."]))


# In[ ]:

#
# #모델 저장하기
# from sklearn.linear_model import LogisticRegression
# from sklearn import datasets
# import pickle
# from sklearn.externals import joblib
#
#
# # In[ ]:
#
#
# joblib.dump(text_clf_svm, 'svm_clf_model.pkl') #피클 파일로 모델 저장
#
#
# # In[ ]:
#
#
# clf_from_joblib = joblib.load('svm_clf_model.pkl')
#
# #모델이 올바르게 저장됐는지 확인해보기
# no_save = text_clf_svm.decision_function(["저는 열정적인 사람입니다"])
# save = clf_from_joblib.decision_function(["저는 열정적인 사람입니다"])
#
# print(no_save == save)

