from konlpy.tag import Okt
import pandas as pd
import numpy as np
from sklearn.pipeline import Pipeline
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.multiclass import OneVsRestClassifier
import joblib
import pickle


globall = pd.read_csv('./csv/global.csv', encoding="utf-8", error_bad_lines=False)
challenge = pd.read_csv('./csv/challenge.csv', encoding="utf-8", error_bad_lines=False)
sincerity = pd.read_csv('./csv/sincerity.csv', encoding="utf-8", error_bad_lines=False)
communication = pd.read_csv('./csv/communication.csv', encoding="utf-8", error_bad_lines=False)
responsibility = pd.read_csv('./csv/responsibility.csv', encoding="utf-8", error_bad_lines=False)
creativity = pd.read_csv('./csv/creativity.csv', encoding="utf-8", error_bad_lines=False)
goal = pd.read_csv('./csv/goal.csv', encoding="utf-8", error_bad_lines=False)
honest = pd.read_csv('./csv/honest.csv', encoding="utf-8", error_bad_lines=False)
stopwords = pd.read_csv('./csv/stopwords.csv')


def tokenizer(raw, pos=["Noun", "Verb"]):
    return [
        word for word, tag in okt.pos(
            raw,
            norm=True,
            stem=True
        )
        if len(word) > 1 and tag in pos and word not in stopwords
    ]


okt = Okt()

keyword_names = ['글로벌', '도전', '성실', '의사소통', '성취지향', '주인의식', '창의', '정직']

all_data = globall.append(challenge).append(sincerity).append(communication).append(goal) \
    .append(responsibility).append(creativity).append(honest)

from sklearn.model_selection import train_test_split

train, test = train_test_split(all_data, test_size=0.3, random_state=40) # train : test = 7:3

print(train.head())
print(test.head())


vectorize = TfidfVectorizer( # 단어 빈도 수에 따른 중요도 설정
    ngram_range=(1, 2), # 1-2 단어 묶음
    max_df=0.80, # 80% 이상 나타나는 단어 제외
    min_df=0,
    sublinear_tf=True
)

tdm = vectorize.fit_transform(train["content"].apply(lambda x: np.str_(x)))

############## SVM-LinearSVC
from sklearn.svm import LinearSVC

SVC_pipeline = Pipeline([
    ('tfidf', vectorize),
    ('clf', OneVsRestClassifier(LinearSVC(), n_jobs=1)),
])

classification = SVC_pipeline.fit(train["content"].apply(lambda x: np.str_(x)), train["label"])

joblib.dump(classification, './model/svcModel.pkl')